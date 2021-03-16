package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/user?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String userName = "root";
    private static final String password = "mmm333";

    public static Connection getMysqlConnection() {
        try {
            return DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

}
