package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
//        Util util = new Util();
//        util.getConnection();

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User();
        user.setName("Andrew");
        user.setLastName("Latyshev");
        user.setAge((byte) 35);

        user.setName("Svetlana");
        user.setLastName("Latysheva");
        user.setAge((byte) 29);

        user.setName("Leonid");
        user.setLastName("Podsohin");
        user.setAge((byte) 32);

        user.setName("Mikhail");
        user.setLastName("Karasev");
        user.setAge((byte) 35);

        session.getTransaction().commit();
        Util.shutdown();
//
//        user.createUsersTable();
//        user.saveUser("Andrew", "Latyshev", (byte) 35);
//        user.saveUser("Svetlana", "Latysheva", (byte) 29);
//        user.saveUser("Leonid", "Podsohin", (byte) 32);
//        user.saveUser("Mikhail", "Karasev", (byte) 34);

    }

}
