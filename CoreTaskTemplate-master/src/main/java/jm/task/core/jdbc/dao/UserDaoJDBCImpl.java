package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getMysqlConnection();

//    public UserDaoJDBCImpl() {
//
//    }

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists user " +
                    "(id bigint auto_increment, name varchar(256), last_name varchar(256), age bigint, primary key (id))");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT user (name, last_name, age) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM user");

            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement();) {
            stmt.executeUpdate("DELETE FROM user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
