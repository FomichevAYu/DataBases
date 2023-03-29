package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.management.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("CREATE TABLE users (id BIGINT auto_increment primary key, name varchar(50), lastname varchar(50), age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            User user = session.find(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        List<User> list = session.createQuery("from User", User.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("DELETE from users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }
}
