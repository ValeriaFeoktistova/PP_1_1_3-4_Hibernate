package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;

public class Main {
    Connection connection;

    public static void main(String[] args) {
        UserDao userDaoHiber = new UserDaoHibernateImpl();

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            UserService userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Pit", "Pitlast", (byte) 7);
            userService.getAllUsers();
            userService.removeUserById(1);
            userService.cleanUsersTable();
            userService.dropUsersTable();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.shutdown();
        }
    }
}




