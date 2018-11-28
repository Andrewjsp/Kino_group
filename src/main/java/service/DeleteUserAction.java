package service;


import dao.BasketDAO;
import entity.User;
import exeption.ConnectionExecption;
import factory.Action;
import dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static utill.KinoGroupConst.*;

public class DeleteUserAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private UserDAO userDAO = new UserDAO();
    private BasketDAO basketDAO=new BasketDAO();

    private List<User> deleteUser(int userId) throws SQLException, ConnectionExecption {
      basketDAO.removeGoodsIfUserDelete(userId);
        List<User> list = userDAO.showAllUsers();
        return list;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        request.setAttribute("showAllUsers", deleteUser(userId));
        logger.info("User id delete from data base : " + userId);
        return SHOW_ALL_USERS_JSP;
    }
}
