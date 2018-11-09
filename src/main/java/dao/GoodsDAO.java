package dao;

import connectionPool.ConnectonPooll;
import entity.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
   private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();

    final private int ALBUM_CATEGORY_ID = 1;
    final private String SHOW_CLOTHES_BY_CATEGORY = "SELECT goods.good_id,goods.good_name,goods.description,price,goods.category_id,color.color_name,size.size_name\n" +
            "FROM goods,color,size WHERE goods.category_id=? AND goods.color_id=color.color_id AND goods.size_id=size.size_id";
    final private String DELETE_PRODUCT = "DELETE  FROM goods WHERE goods.good_id=?";
    final private String SHOW_MUSIC_FOR_ID_ALBUM = "SELECT good2.good_id,good2.good_name,good2.description,good2.price FROM goods AS good1,goods AS good2 WHERE good1.good_id=good2.parent_id AND good1.good_id=?";
    final private String ADD_ALBUM = "INSERT INTO goods(good_name,category_id,description,price)VALUES(?,?,?,?)";
    final private String SHOW_ALL_PRODUCTS = "SELECT goods.good_id,goods.good_name,goods.price,goods.description,goods.parent_id FROM goods";
    final private String SHOW_ALBUMS = "SELECT goods.good_id,goods.good_name,goods.description ,goods.price FROM goods WHERE category_id=1";
    final private String ADD_MUSIC = "INSERT INTO goods(good_name,parent_id,description,price)VALUES(?,?,?,?)";
    final private String ADD_CLOTHES = "INSERT INTO goods(good_name,category_id,description,price,size_id,color_id)VALUES(?,?,?,?,?,?)";
    final private String CHECK_IS_EMPTY_ALBUM = "SELECT good2.good_name FROM goods AS good1,goods AS good2 WHERE good1.good_id=good2.parent_id AND good1.good_id=?";

    public void deleteGood(int goodId) throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.statementMethod(DELETE_PRODUCT, goodId);
    }

    public List<Good> showAlbum() throws SQLException   {
        Connection connection = connectonPooll.retrieve();
        List<Good> list;
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_ALBUMS);
            ResultSet resultSet = statement.executeQuery();
            list = showGood(resultSet);
        } finally {
            connectonPooll.putback(connection);
        }

        return list;
    }

    public Good checkAlbumIsEmpry(int albumId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        Good good = new Good();
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_IS_EMPTY_ALBUM);
            statement.setInt(1, albumId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                good.setProductName(resultSet.getNString(1));
            }
        } finally {
            return good;
        }

    }

    public List<Good> showMusicForId(int albumId) throws SQLException, InterruptedException {
        Connection connection = connectonPooll.retrieve();
        List<Good> list;
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_MUSIC_FOR_ID_ALBUM);
            statement.setInt(1, albumId);
            ResultSet resultSet = statement.executeQuery();
            list = showGood(resultSet);
        } finally {
            connectonPooll.putback(connection);
        }
        return list;
    }

    private List<Good> showGood(ResultSet resultSet) throws SQLException {
        List<Good> list = new ArrayList<>();
        while (resultSet.next()) {
            Good good = new Good();
            good.setProductId(resultSet.getInt(1));
            good.setProductName(resultSet.getNString(2));
            good.setDescription(resultSet.getNString(3));
            good.setProductPrice(resultSet.getInt(4));
            list.add(good);
        }
        return list;
    }

    public List<Good> showClothesByCategory(int categoryId) throws SQLException, InterruptedException {
        Connection connection = connectonPooll.retrieve();
        List<Good> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_CLOTHES_BY_CATEGORY);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Good good = new Good();
                good.setProductId(resultSet.getInt(1));
                good.setProductName(resultSet.getNString(2));
                good.setDescription(resultSet.getNString(3));
                good.setProductPrice(resultSet.getInt(4));
                good.setCategoryId(resultSet.getInt(5));
                good.setColorName(resultSet.getString(6));
                good.setSizeName(resultSet.getString(7));
                list.add(good);
            }
        } finally {
            connectonPooll.putback(connection);
        }

        return list;
    }

    public void addAlbum(Good good) throws SQLException, InterruptedException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_ALBUM);
            statement.setString(1, good.getProductName());
            statement.setInt(2, ALBUM_CATEGORY_ID);
            statement.setString(3, good.getDescription());
            statement.setInt(4, good.getProductPrice());
            statement.execute();
        } finally {
            connectonPooll.putback(connection);
        }

    }

    public List<Good> showAllProducts() throws SQLException {
        Connection connecton = connectonPooll.retrieve();
        List<Good> list = new ArrayList<>();
        try {
            PreparedStatement statement = connecton.prepareStatement(SHOW_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Good good = new Good();
                good.setProductId(resultSet.getInt(1));
                good.setProductName(resultSet.getNString(2));
                good.setProductPrice(resultSet.getInt(3));
                good.setDescription(resultSet.getNString(4));
                good.setParentId(resultSet.getInt(5));
                list.add(good);
            }
        } finally {
            connectonPooll.putback(connecton);
        }
        return list;
    }

    public void addClothes(Good good) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_CLOTHES);
            statement.setString(1, good.getProductName());
            statement.setInt(2, good.getCategoryId());
            statement.setString(3, good.getDescription());
            statement.setInt(4, good.getProductPrice());
            statement.setInt(5, good.getSizeId());
            statement.setInt(6, good.getColorId());
            statement.execute();
            statement.close();
        } finally {
            connectonPooll.putback(connection);
        }

    }

    public void addMusic(Good good) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_MUSIC);
            statement.setString(1, good.getProductName());
            statement.setInt(2, good.getParentId());
            statement.setString(3, good.getDescription());
            statement.setInt(4, good.getProductPrice());
            statement.execute();
        } finally {
            connectonPooll.putback(connection);
        }

    }
}
