package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.createSessionFactory();

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("create table if not exists user (id bigint auto_increment, name varchar(256), last_name varchar(256), age bigint, primary key (id))");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("drop table if exists user");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User user where user.id =:id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            List<User> users = query.list();
            transaction.commit();
            session.close();
            return users;
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}