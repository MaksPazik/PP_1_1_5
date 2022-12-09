package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    Session session;
    Query query;
    Transaction transaction;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sqlCommand  ="create table if not exists User(id bigint not null auto_increment," +
                " name VARCHAR(30) not null ," +
                " lastName VARCHAR(30) not null," +
                " age tinyint, PRIMARY KEY (id))";
        try {
            session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createSQLQuery(sqlCommand);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS  User";
        try {
            session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createSQLQuery(sqlCommand);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO user (name, lastName, age) VALUES (?, ?,?)";
        try {
            session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createSQLQuery(sqlCommand);
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных." );
            System.out.println("____________________________________");
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        String sqlCommand = "DELETE  FROM  user WHERE id = ?";
        try {
            session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createSQLQuery(sqlCommand);
            query.setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sqlCommand = "SELECT id FROM User id";
        List<User> users;
        try {
            session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            users = session.createQuery(sqlCommand, User.class).getResultList();
            System.out.println(users);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sqlCommand = "DELETE  FROM user";
        try {
            session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            query = session.createSQLQuery(sqlCommand);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
