package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        util.getConnection();
        UserDao userService = new UserDaoHibernateImpl();
        User user1 = new User("Andrew", "Latyshev", (byte) 35);
        User user2 = new User("Svetlana", "Latysheva", (byte) 29);
        User user3 = new User("Leonid", "Podsohin", (byte) 32);
        User user4 = new User("Mikhail", "Karasev", (byte) 34);

        Session session = Util.buildSessionFactory().openSession();
        session.beginTransaction();

        userService.dropUsersTable();
        System.out.println("Mark");
        userService.createUsersTable();

        session.saveOrUpdate(user1);
        session.saveOrUpdate(user2);
        session.saveOrUpdate(user3);
        session.saveOrUpdate(user4);
        System.out.println("User с именем " + user1.getName() + " был(а) добавлен(а) в базу данных");
        System.out.println("User с именем " + user2.getName() + " был(а) добавлен(а) в базу данных");
        System.out.println("User с именем " + user3.getName() + " был(а) добавлен(а) в базу данных");
        System.out.println("User с именем " + user4.getName() + " был(а) добавлен(а) в базу данных");


        session.close();


    }

}
