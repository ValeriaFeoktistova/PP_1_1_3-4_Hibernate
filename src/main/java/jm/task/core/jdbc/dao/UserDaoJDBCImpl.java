/*
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String CREATE_TABLE = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(70),
                    lastName VARCHAR(70),
                    age TINYINT
                )
                """;
        var utilcon = Util.getInstance().connection;
        try (PreparedStatement preparedStatement = utilcon.prepareStatement(CREATE_TABLE)) {
            preparedStatement.execute();
            System.out.println("Таблица users создана успешно.");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при создании таблицы users.");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String DROP_TABLE = "DROP TABLE IF EXISTS users";
        var utilcon = Util.getInstance().connection;
        try (PreparedStatement preparedStatement = utilcon.prepareStatement(DROP_TABLE)) {
            preparedStatement.execute();
            System.out.println("Таблица users удалена успешно.");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при удалении таблицы users.");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SAVE_USER = """
                INSERT INTO users (name, lastName, age)
                VALUES (?, ?, ?)
                """;
        var utilcon = Util.getInstance().connection;
        try {
            utilcon.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement preparedStatement = utilcon.prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при добавлении пользователя.");
            throw new RuntimeException(e);
        }
        try {
            utilcon.commit();
            utilcon.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String REMOVE_USER_BY_ID = """
                DELETE FROM users
                WHERE id = ?
                """;
        var utilcon = Util.getInstance().connection;
        try (PreparedStatement preparedStatement = utilcon.prepareStatement(REMOVE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Удалено " + rowsAffected + " пользователей с id = " + id);
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при удалении пользователя.");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> listusers = new ArrayList<>();
        String GET_ALL_USERS = "SELECT id, name, lastName, age FROM users";
        var utilcon = Util.getInstance().connection;

        try (PreparedStatement preparedStatement = utilcon.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listusers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при получении пользователей.");
            throw new RuntimeException(e);
        }
        System.out.println(listusers.toString());
        return listusers;
    }

    public void cleanUsersTable() {
        String CLEAN_TABLE = "TRUNCATE TABLE users";
        var utilcon = Util.getInstance().connection;
        try (PreparedStatement preparedStatement = utilcon.prepareStatement(CLEAN_TABLE)) {
            preparedStatement.execute();
            System.out.println("Таблица users очищена успешно.");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при очистке таблицы users.");
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        var utilcon = Util.getInstance().connection;
        try {
            utilcon.close();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при закрытии соединения.");
            throw new RuntimeException(e);
        }
    }
}*/
