package factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface  Action {
public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, IOException, ServletException, InterruptedException;
}
