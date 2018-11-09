package dao;

import connectionPool.ConnectonPooll;
import entity.Category;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    final private String SHOW_DEFAULT_CATEGORY = "SELECT category_id,category_name FROM category WHERE language_id=1";
    final private String SHOW_CATEGORY_BY_LANGUAGE_ID = "SELECT category_id,category_name FROM category WHERE language_id=?";
    final private String SHOW_CLOTHES_CATEGORY = "SELECT * FROM category WHERE category_id>1 AND language_id=?";
    private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();

    public List<Category> showClothesByLanguageId(int languageId) throws SQLException {
        return getCategoryByLanguageId(SHOW_CLOTHES_CATEGORY, languageId);
    }

    public List<Category> showCategoryByIdLanguage(int languageId) throws SQLException {

        return getCategoryByLanguageId(SHOW_CATEGORY_BY_LANGUAGE_ID, languageId);
    }

    public List<Category> showDefaultCategory() throws SQLException {
        Connection connection = connectonPooll.retrieve();
        List<Category> list;
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_DEFAULT_CATEGORY);
            ResultSet resultSet = statement.executeQuery();
            list = getCategory(resultSet);
        }finally {

            connectonPooll.putback(connection);
        }
       return list;
    }

    private List<Category> getCategoryByLanguageId(String query, int languageId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        List<Category> list;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, languageId);
            ResultSet resultSet = statement.executeQuery();
          list = getCategory(resultSet);
        }finally {
            connectonPooll.putback(connection);
        }
        return list;
    }

    private List<Category> getCategory(ResultSet resultSet) throws SQLException {
        List<Category> list = new ArrayList<>();
        while (resultSet.next()) {
            Category category = new Category();
            category.setCategoryId(resultSet.getInt(1));
            category.setCategoryName(resultSet.getNString(2));
            list.add(category);
        }
        return list;
    }
}
