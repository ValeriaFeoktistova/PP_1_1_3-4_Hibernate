package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;

public class Main {
    Connection connection;

    public static void main(String[] args) {
        // Добавление класса User
        UserDao userDaoHiber = new UserDaoHibernateImpl();

        try (Session session = Util.getSessionFactory().openSession()) {
            // Начало транзакции
            Transaction transaction = session.beginTransaction();

            userDaoHiber.createUsersTable();//1
            userDaoHiber.saveUser("Pit", "Pitlast", (byte) 7);//2
            userDaoHiber.getAllUsers();//3
            userDaoHiber.removeUserById(7L);//4
            userDaoHiber.cleanUsersTable();//5
            userDaoHiber.dropUsersTable();//6

            // Фиксация транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие SessionFactory
            Util.shutdown();
        }
    }
}

/*
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        *//*UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();


        userDaoJDBC.createUsersTable();//1
        userDaoJDBC.saveUser("Pit", "Pitlast", (byte) 7);//2
        userDaoJDBC.getAllUsers();//3
        userDaoJDBC.removeUserById(1);//4
        userDaoJDBC.cleanUsersTable();//5
        userDaoJDBC.dropUsersTable();//6
        userDaoJDBC.closeConnection();//70*//*

}*/



