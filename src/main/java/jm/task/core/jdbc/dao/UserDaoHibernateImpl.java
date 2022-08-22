package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.buildSessionFactory();
    Session session = sessionFactory.openSession();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement("create table user (id INT auto_increment primary key not null , name NVARCHAR(20) not null , lastName NVARCHAR(20) not null, age INT not null)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    @Override
    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement("DROP TABLE user");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.beginTransaction();

//        User user = new User("Andrew", "Latyshev", (byte) 35);
        session.save(User.class);
        session.getTransaction().commit();

        sessionFactory.close();

    }

    @Override
    public void removeUserById(long id) {
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();

        sessionFactory.close();

    }

    @Override
    public List<User> getAllUsers() {

        session.beginTransaction();


        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        cq.select(user);

        Query query = session.createQuery(cq);

        List<User> users = query.getResultList();
        session.getTransaction().commit();

        sessionFactory.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
    }
}
