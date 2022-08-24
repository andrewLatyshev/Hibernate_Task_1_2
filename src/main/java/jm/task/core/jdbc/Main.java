package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Util util = new Util();
//        util.getConnection();
        UserDao userService = new UserDaoHibernateImpl();

        userService.dropUsersTable();

        userService.createUsersTable();

        userService.saveUser("Andrew", "Latyshev", (byte) 35);
        userService.saveUser("Svetlana", "Latysheva", (byte) 29);
        userService.saveUser("Leonid", "Podsohin", (byte) 32);
        userService.saveUser("Mikhail", "Karasev", (byte) 34);

        userService.getAllUsers();

        userService.removeUserById(2);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }

}
