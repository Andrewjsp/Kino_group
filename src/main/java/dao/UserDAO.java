package dao;

import connectionPool.ConnectonPooll;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final int DEFAULT_USER_ROLE = 0;
    private final int DEFAULT_USER_BALANCE = 5000;

    private final String SHOW_ALL_USERS = "SELECT user.user_id,user.user_login,user.user_role,user.balance FROM user";
    private final String DELETE_USER_FOR_ID = "DELETE FROM user WHERE user.user_id=?";
    private final String GET_ALL_INFORM_ABOUT_USER = "SELECT user.user_name,user.user_login,user.user_role,user.balance,user.user_id FROM user WHERE user.user_login=?";
    private final String ADD_USER = "INSERT INTO user (user.user_name,user.user_login,user.user_password,user.user_role,user.Balance)VALUES(?,?,?,?,?)";
    private final String CHECK_USER_LOGIN = "SELECT user.user_login FROM user WHERE user.user_login=?";
    private final String CHANGE_USER_ROLE_TO_ADMIN = "UPDATE user SET user.user_role=1 WHERE user.user_id=?";
    private final String CHANGE_ADMIN_ROLE_TO_USER = "UPDATE user SET user.user_role=0 WHERE user.user_id=?";
    private final String GET_USER_BALANCE = "SELECT user.balance FROM user WHERE user.user_id=?";
    private final String UPDATE_USER_BALANCE = "UPDATE user SET balance=? WHERE user_id=?";
    private final String CHECK_USER_LOGIN_AND_PASSWORD = "SELECT user.user_login FROM user WHERE user.user_login=? AND user.user_password=?";
    private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();

    public void statementMethod(String query, int id) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } finally {

            connectonPooll.putback(connection);
        }
    }


    public void changeRoleToAdmin(int userId) throws SQLException {
        statementMethod(CHANGE_USER_ROLE_TO_ADMIN, userId);
    }

    public void changeRoleToUser(int userId) throws SQLException {
        statementMethod(CHANGE_ADMIN_ROLE_TO_USER, userId);
    }

    public void deleteUser(int userId) throws SQLException {
        statementMethod(DELETE_USER_FOR_ID, userId);
    }

    public List<User> showAllUsers() throws SQLException {
        Connection connection = connectonPooll.retrieve();
        List<User> list = new ArrayList();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUserLogin(resultSet.getNString(2));
                user.setUserRole(resultSet.getInt(3));
                user.setBalance(resultSet.getInt(4));
                list.add(user);
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return list;
    }

    public User getAllInformAboutUser(String login) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_INFORM_ABOUT_USER);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserName(resultSet.getNString(1));
                user.setUserLogin(resultSet.getNString(2));
                user.setUserRole(resultSet.getInt(3));
                user.setBalance(resultSet.getInt(4));
                user.setUserId(resultSet.getInt(5));
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return user;
    }

    public User checkUserLogin(String login) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_USER_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserLogin(resultSet.getNString(1));
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return user;
    }

    public User checkLoginAndPasswordUser(String login, String password) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_USER_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserLogin(resultSet.getNString(1));
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return user;
    }

    public void addUser(User user) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserLogin());
            statement.setString(3, user.getUserPassword());
            statement.setInt(4, DEFAULT_USER_ROLE);
            statement.setInt(5, DEFAULT_USER_BALANCE);
            statement.execute();
        } finally {

            connectonPooll.putback(connection);
        }
    }

    public int getUserBalance(int userId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        int userBalance = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BALANCE);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userBalance = resultSet.getInt(1);
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return userBalance;
    }

    public void updateUserBalance(int newBalanceUser, int userId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BALANCE);
            statement.setInt(1, newBalanceUser);
            statement.setInt(2, userId);
            statement.execute();
        } finally {

            connectonPooll.putback(connection);
        }
    }
}
