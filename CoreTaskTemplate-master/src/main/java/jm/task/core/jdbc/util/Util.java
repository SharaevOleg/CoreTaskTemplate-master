package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getMysqlConnection() {
        String connectionUrl = "jdbc:mysql://localhost:3306/user?useUnicode=true&serverTimezone=UTC&useSSL=false";
        String userName = "root";
        String password = "mmm333";
//        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            System.out.println("УРА!!!!!!");

            return DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

}
