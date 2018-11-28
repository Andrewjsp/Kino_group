package dao;

import connectionPool.ConnectonPooll;
import entity.Color;
import exeption.ConnectionExecption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColorDAO {
    ConnectonPooll connectonPooll = ConnectonPooll.getInstance();
    private final String SHOW_ALL_COLOR = "SELECT * FROM color";

    public List<Color> showAllColor() throws SQLException, ConnectionExecption {
        Connection connection = connectonPooll.retrieve();
        List<Color> list = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SHOW_ALL_COLOR)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Color color = new Color();
                color.setColorId(resultSet.getInt(1));
                color.setColorName(resultSet.getString(2));
                list.add(color);
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return list;
    }
}
