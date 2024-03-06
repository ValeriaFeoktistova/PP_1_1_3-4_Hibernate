package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
    }

    private final UserDao userDaoHiber = new UserDaoHibernateImpl();

@Override
    public void createUsersTable() {
        userDaoHiber.createUsersTable();
    }
@Override
    public void dropUsersTable() {
        userDaoHiber.dropUsersTable();
    }
@Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHiber.saveUser(name,lastName,age);
    }
@Override
    public void removeUserById(long id) {
        userDaoHiber.removeUserById(id);

    }
@Override
    public List<User> getAllUsers() throws SQLException {
    return userDaoHiber.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
       userDaoHiber.cleanUsersTable();
    }
}

