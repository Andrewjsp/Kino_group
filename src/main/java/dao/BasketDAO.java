package dao;


import entity.Basket;
import connectionPool.*;
import entity.User;
import exeption.ConnectionExecption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDAO {
    private final String ADD_GOOD_IN_BASKET = "INSERT INTO basket(good_id,good_name,good_price,user_id)VALUES(?,?,?,?)";
    private final String SHOW_ALL_GOODS_IN_BASKET = "SELECT order_id,good_id,good_name,good_price FROM basket WHERE user_id=?";
    private final String REMOVE_GOOD_FROM_BASKET = "DELETE basket FROM basket WHERE order_id=?";
    private final String REMOVE_ALL_GOODS_FROM_BASKET = "DELETE basket FROM basket WHERE user_id=?";
    private final String REMOVE_GOOD_FOR_ADMIN = "DELETE basket FROM basket WHERE good_id=?";
    private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();
    private UserDAO userDAO = new UserDAO();
    private GoodsDAO goodsDAO = new GoodsDAO();

    public void addGood(Basket basket, int userId) throws SQLException, ConnectionExecption {
        Connection connection = connectonPooll.retrieve();
        try (PreparedStatement statement = connection.prepareStatement(ADD_GOOD_IN_BASKET)) {
            statement.setInt(1, basket.getGoodId());
            statement.setString(2, basket.getGoodName());
            statement.setInt(3, basket.getGoodPrice());
            statement.setInt(4, userId);
            statement.execute();
        } finally {
            connectonPooll.putback(connection);
        }
    }

    public List<Basket> showAllGoodsInBasket(int userId) throws SQLException, ConnectionExecption {
        Connection connection = connectonPooll.retrieve();
        List<Basket> list = new ArrayList();
        try (PreparedStatement statement = connection.prepareStatement(SHOW_ALL_GOODS_IN_BASKET)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Basket basket = new Basket();
                basket.setOrderId(resultSet.getInt(1));
                basket.setGoodId(resultSet.getInt(2));
                basket.setGoodName(resultSet.getNString(3));
                basket.setGoodPrice(resultSet.getInt(4));
                list.add(basket);
            }
        } finally {
            connectonPooll.putback(connection);
        }
        return list;
    }

    public void buyProduct(int userBalance, int userId) throws ConnectionExecption, SQLException {
        Connection connection = connectonPooll.retrieve();
        connection.setAutoCommit(false);
        try {
            userDAO.updateUserBalance(userBalance, userId, connection);
            removeAllGoodsFromBasket(userId,connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connectonPooll.putback(connection);
        }
    }

    public void removeGoodAdmin(int goodId) throws SQLException, ConnectionExecption {
        Connection connection = connectonPooll.retrieve();
        try {
            connection.setAutoCommit(false);
            removeGoodForAdmin(goodId,connection);
            goodsDAO.deleteGood(goodId,connection);
            connection.commit();
        } catch (SQLException | ConnectionExecption e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connectonPooll.putback(connection);
        }
    }

    public void removeGoodsIfUserDelete(int userId) throws SQLException, ConnectionExecption {
        Connection connection = connectonPooll.retrieve();
        try {
            connection.setAutoCommit(false);
            removeAllGoodsFromBasket(userId,connection);
            userDAO.deleteUser(userId,connection);
            connection.commit();
        } catch (SQLException | ConnectionExecption e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connectonPooll.putback(connection);
        }
    }
    public void removeGoodFromBasket(int orderId) throws SQLException, ConnectionExecption {
        userDAO.statementMethod(REMOVE_GOOD_FROM_BASKET, orderId);
    }

    private void removeAllGoodsFromBasket(int userId,Connection connection) throws SQLException, ConnectionExecption {
        userDAO.statementMethod(REMOVE_ALL_GOODS_FROM_BASKET, userId,connection);
    }

    private void removeGoodForAdmin(int goodId,Connection connection) throws SQLException, ConnectionExecption {
        userDAO.statementMethod(REMOVE_GOOD_FOR_ADMIN, goodId,connection);
    }
}
