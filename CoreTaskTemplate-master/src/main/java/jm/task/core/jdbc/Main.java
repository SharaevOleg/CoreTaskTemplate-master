package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static java.sql.DriverManager.getConnection;

import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
//        User user = new User("Anton", "Tatarin", (byte) 22);
//        System.out.println(user.toString());
//
//        Connection connection = Util.getMysqlConnection();
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate("DROP TABLE IF EXISTS user2");
////            statement.executeUpdate("create table if not exists user " +
////                    "(id bigint auto_increment, name varchar(256), last_name varchar(256), age bigint, primary key (id))");
//
//
//        }
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();

        userService.saveUser("Фёдор", "Рекиш", (byte) 22);
        userService.saveUser("Юлия", "Гурова", (byte) 33);
        userService.saveUser("Алексей", "Оленев", (byte) 44);
        userService.saveUser("Станислав", "Сорокин", (byte) 55);

        List<User> users = userService.getAllUsers();

        for (User u : users) {
            System.out.println(u.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
