package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users (id SERIAL NOT NULL," +
                "name character varying(25)," +
                "lastName character varying(25)," +
                "age smallint NOT NULL," +
                "PRIMARY KEY (id))";
        Connection(create);
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        Connection(drop);
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            connection = Util.PSQL();
            preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id = id";
        Connection(remove);
    }

    public List<User> getAllUsers() {
        List<User> array = new ArrayList<>();
        String getUsers = "SELECT * FROM users";
        try {
            connection = Util.PSQL();
            preparedStatement = connection.prepareStatement(getUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                array.add(user);
                connection.setAutoCommit(false);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return array;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE users";
        Connection(clean);
    }

    private void Connection(String str) {
        try {
            connection = Util.PSQL();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}