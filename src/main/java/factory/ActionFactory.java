package factory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface  ActionFactory {
public Action getAction(HttpServletRequest request) throws SQLException,ClassNotFoundException;
}
