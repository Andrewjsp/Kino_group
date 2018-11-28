package service;


import exeption.ConnectionExecption;
import factory.Action;
import dao.UserDAO;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import static utill.KinoGroupConst.*;

public class  ShowUserAction implements Action {
    private UserDAO userDAO = new UserDAO();

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        request.setAttribute(SHOW_ALL_USERS, userDAO.showAllUsers());
        return SHOW_ALL_USERS_JSP;
    }
}
