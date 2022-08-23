package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.buildSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    Session session;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("create table if not exists user(id BIGINT primary key auto_increment, name VARCHAR(20) not null, lastName VARCHAR(20) DEFAULT not null, age INT not null)");

            session.getTransaction().commit();

        } catch (HibernateException h) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("drop table if exists user")
                    .executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException h) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();

        } catch(Exception sqlException) {
            if (null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();

            User user = session.load(User.class, id);
            session.delete(user);

            session.getTransaction().commit();

        } catch(Exception sqlException) {
            if (null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();

            userList = session.createQuery("from User ", User.class).getResultList();

        } catch(Exception sqlException) {
            if (null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = buildSessionFactory().openSession();
            session.beginTransaction();

            Query queryObj = session.createQuery("DELETE FROM User");
            queryObj.executeUpdate();

            session.getTransaction().commit();

        } catch(Exception sqlException) {
            if (null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
