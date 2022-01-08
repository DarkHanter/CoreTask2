package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoJDBCImpl();
    private UserDao newUserDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        newUserDao.createUsersTable();
    }

    public void dropUsersTable() {
        newUserDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        newUserDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        newUserDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return newUserDao.getAllUsers();
    }

    public void cleanUsersTable() {
        newUserDao.cleanUsersTable();
    }
}
