package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection util = new Util().getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = util.prepareStatement("create table users (id INT auto_increment primary key not null , name NVARCHAR(20) not null , lastName NVARCHAR(20) not null, age INT not null)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement = util.prepareStatement("DROP TABLE users");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = util.prepareStatement("insert into users (name, lastName, age) values (?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " был(а) добавлен(а) в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = util.prepareStatement("delete from users where id=?");
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = util.createStatement();
            ResultSet userFromBD = statement.executeQuery("select * from users");

            while(userFromBD.next()) {
                User user = new User();
                user.setId(userFromBD.getLong("id"));
                user.setName(userFromBD.getString("name"));
                user.setLastName(userFromBD.getString("lastName"));
                user.setAge(userFromBD.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        users.stream().toList().forEach(System.out :: println);
        return users;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement = util.prepareStatement("truncate table users");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
