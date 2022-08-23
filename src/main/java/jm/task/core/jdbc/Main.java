package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
//        Util util = new Util();
//        util.getConnection();
        UserServiceImpl user = new UserServiceImpl();

        user.dropUsersTable();

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();

        user.createUsersTable();
        session.save(new User("Andrew", "Latyshev", (byte) 35));
        session.save(new User("Svetlana", "Latysheva", (byte) 29));
        session.save(new User("Leonid", "Podsohin", (byte) 32));
        session.save(new User("Mikhail", "Karasev", (byte) 34));


//        session.remove(user);


//        session.delete(User.class);

        user.removeUserById(2);
//        user.getAllUsers();
//        user.cleanUsersTable();
//        user.dropUsersTable();

        System.out.println(user);

        session.close();



//        User user = new User();
//        user.setName("Andrew");
//        user.setLastName("Latyshev");
//        user.setAge((byte) 35);
//
//        user.setName("Svetlana");
//        user.setLastName("Latysheva");
//        user.setAge((byte) 29);
//
//        user.setName("Leonid");
//        user.setLastName("Podsohin");
//        user.setAge((byte) 32);
//
//        user.setName("Mikhail");
//        user.setLastName("Karasev");
//        user.setAge((byte) 35);
//
//        session.getTransaction().commit();
//        Util.shutdown();
//
//        user.createUsersTable();
//        user.saveUser("Andrew", "Latyshev", (byte) 35);
//        user.saveUser("Svetlana", "Latysheva", (byte) 29);
//        user.saveUser("Leonid", "Podsohin", (byte) 32);
//        user.saveUser("Mikhail", "Karasev", (byte) 34);

    }

}
