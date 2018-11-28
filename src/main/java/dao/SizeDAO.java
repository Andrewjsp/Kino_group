package dao;

import connectionPool.ConnectonPooll;
import entity.Size;
import exeption.ConnectionExecption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SizeDAO {
    private ConnectonPooll connectonPooll = ConnectonPooll.getInstance();
    private final String SHOW_ALL_SIZE = "SELECT * FROM size";

    public List<Size> showAllSize() throws SQLException, ConnectionExecption {
        Connection connection = connectonPooll.retrieve();
        List<Size> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SHOW_ALL_SIZE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Size size = new Size();
                size.setSizeId(resultSet.getInt(1));
                size.setSizeName(resultSet.getString(2));
                list.add(size);
            }
        } finally {

            connectonPooll.putback(connection);
        }
        return list;
    }
}

