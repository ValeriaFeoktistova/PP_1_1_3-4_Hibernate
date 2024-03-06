package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class Util {
    private static Util INSTANCE = new Util();

    private Util() {
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Настройка Hibernate для использования соединения с базой данных
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/wednesday");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "admin");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.show_sql", "true");
            //configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

/////////////////////////////////////////////JDBC
    public static Util getInstance() {

        Util locaInstance = INSTANCE;
        if (locaInstance == null) {
            synchronized (Util.class) {
                locaInstance = INSTANCE;
                if (locaInstance == null) {
                    INSTANCE = locaInstance = new Util();
                }
            }
        }
        return INSTANCE;
    }

    public final String URL = "jdbc:mysql://localhost:3306/wednesday";
    public final String USERNAME = "root";
    public final String PASSWORD = "admin";

    public static Connection connection;

    {
        try {
            connection = getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement statement;

}






