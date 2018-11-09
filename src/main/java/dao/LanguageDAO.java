package dao;

import connectionPool.ConnectonPooll;
import entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    private final String SHOW_ALL_LANGUAGE = "SELECT * FROM language";
    private final String SHOW_LOCALE_BY_ID = "SELECT language_locale FROM language WHERE language_id=?";
    private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();

    public List<Language> showAllLanguage() throws SQLException {
        Connection connection = connectonPooll.retrieve();
        PreparedStatement statement = connection.prepareStatement(SHOW_ALL_LANGUAGE);
        ResultSet resultSet = statement.executeQuery();
        List<Language> list = new ArrayList<>();
        while (resultSet.next()) {
            Language language = new Language();
            language.setLanguageId(resultSet.getInt(1));
            language.setLanguageLocal(resultSet.getNString(2));
            list.add(language);
        }
        connectonPooll.putback(connection);
        return list;
    }

    public String getLocale(int languageId) throws SQLException {
        Connection connection = connectonPooll.retrieve();
        String locale = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_LOCALE_BY_ID);
            statement.setInt(1, languageId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                locale = resultSet.getNString(1);
            }
        } finally {
            connectonPooll.putback(connection);
        }
        return locale;
    }
}

