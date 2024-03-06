package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            // Начало транзакции
            Transaction transaction = session.beginTransaction();

            // Выполнение SQL-запроса для создания таблицы
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age TINYINT )";
            session.createNativeQuery(createTableQuery).executeUpdate();

            // Фиксация транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            // Начало транзакции
            Transaction transaction = session.beginTransaction();

            // Выполнение SQL-запроса для удаления таблицы
            String dropTableQuery = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(dropTableQuery).executeUpdate();

            // Фиксация транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            // Фиксация транзакции
            transaction.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            // Начало транзакции
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            // Фиксация транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        Transaction transaction = null;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            // Начало транзакции
            transaction = session.beginTransaction();
            // Выполнение запроса
            userList = session.createQuery("FROM User", User.class).getResultList();
            // Фиксация транзакции
            transaction.commit();
            System.out.println(userList.toString());
            //System.out.println(userList.stream().map(User::toString).collect(Collectors.joining(", ")));

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            // Начало транзакции
            Transaction transaction = session.beginTransaction();

            // Выполнение SQL-запроса для очистки таблицы users
            String truncateQuery = "TRUNCATE TABLE users";
            session.createNativeQuery(truncateQuery).executeUpdate();

            // Фиксация транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }



