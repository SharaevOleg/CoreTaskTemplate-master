//package jm.task.core.jdbc.util;
//
//import jm.task.core.jdbc.model.User;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
//
//public class Util {
//
//    private static final String connectionUrl = "jdbc:mysql://localhost:3306/user?useUnicode=true&serverTimezone=UTC&useSSL=false";
//    private static final String userName = "root";
//    private static final String password = "mmm333";
//
//    public static Connection getMysqlConnection() {
//        try {
//            return DriverManager.getConnection(connectionUrl, userName, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new IllegalStateException();
//        }
//    }
//
//    private static Configuration getMySqlConfiguration() {
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(User.class);
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/user?useUnicode=true&serverTimezone=UTC&useSSL=false");
//        configuration.setProperty("hibernate.connection.username", "root");
//        configuration.setProperty("hibernate.connection.password", "mmm333");
//        configuration.setProperty("hibernate.show_sql", "true");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
//        return configuration;
//    }
//
//    public static SessionFactory createSessionFactory() {
//        Configuration configuration = getMySqlConfiguration();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//        return configuration.buildSessionFactory(builder.build());
//    }
//}
package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMysqlConnection() {
        String userName = "root";
        String password = "mmm333";
        String nameTable = "user";
        String url = "jdbc:mysql://localhost:3306/"+nameTable+"?user="+userName+"&password="+password;

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/user?useUnicode=true&serverTimezone=UTC&useSSL=false");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "mmm333");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
        return configuration;
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }
}