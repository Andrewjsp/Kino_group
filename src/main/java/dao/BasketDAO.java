package dao;


import entity.Basket;
import connectionPool.*;
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
    private final String REMOVE_GOOD_FOR_ADMIN= "DELETE basket FROM basket WHERE good_id=?";
    private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();
    private Logger logger = LogManager.getRootLogger();

    public void addGood(Basket basket, int userId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_GOOD_IN_BASKET);
            statement.setInt(1, basket.getGoodId());
            statement.setString(2, basket.getGoodName());
            statement.setInt(3, basket.getGoodPrice());
            statement.setInt(4, userId);
            statement.execute();
        } finally {
            connectonPooll.putback(connection);
        }
    }

    public List<Basket> showAllGoodsInBasket(int userId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        List<Basket> list = new ArrayList();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_ALL_GOODS_IN_BASKET);
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

    public void removeGoodFromBasket(int orderId) throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.statementMethod(REMOVE_GOOD_FROM_BASKET, orderId);
    }

    public void removeAllGoodsFromBasket(int userId) throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.statementMethod(REMOVE_ALL_GOODS_FROM_BASKET, userId);
    }

public void removeGoodForAdmin(int goodId) throws SQLException {
        UserDAO userDAO=new UserDAO();
        userDAO.statementMethod(REMOVE_GOOD_FOR_ADMIN,goodId);
}
}
