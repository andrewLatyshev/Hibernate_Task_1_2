package jm.task.core.jdbc.dao;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement("create table users (id INT auto_increment primary key not null , name NVARCHAR(20) not null , lastName NVARCHAR(20) not null, age INT not null)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    @Override
    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = new Util().getConnection().prepareStatement("DROP TABLE users");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        sessionFactory.openSession();
        Session session = sessionFactory.getCurrentSession();

    }

    @Override
    public void removeUserById(long id) {
    }

    @Override
    public List<User> getAllUsers() {

        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        cq.select(user);

        Query query = session.createQuery(cq);
        List<User> users = query.getResultList();

        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
    }
}
