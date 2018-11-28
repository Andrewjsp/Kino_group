package service;

import exeption.ConnectionExecption;
import factory.Action;
import dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static utill.KinoGroupConst.*;

public class ChangeRoleAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private String MESSAGE;
    private UserDAO userDAO = new UserDAO();

    private void changeRole(int userId, int userRole) throws SQLException, ConnectionExecption {
        switch (userRole) {
            case ADMIN_ROLE:
                userDAO.changeRoleToUser(userId);
                break;
            case DEFAULT_ROLE:
                userDAO.changeRoleToAdmin(userId);
                break;
        }
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        String view;
        String access = request.getParameter(CHECKBOX_WITH_INDICATION_ACCESS);
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);
        Integer userId = Integer.parseInt(request.getParameter(USER_ID));
        Integer role = Integer.parseInt(request.getParameter(USER_ROLE));
        if (Validator.checkFieldsOnNull(access)) {
            changeRole(userId, role);
            logger.info("User role changed");
            view = WELCOME_JSP;
        } else {
            MESSAGE = resourceBundle.getString("specifyRole");
            request.setAttribute("message", MESSAGE);
            logger.info("User role not changed,userId" + userId);
            view = CHANGE_USER_ROLE_JSP;
        }
        return view;
    }
}
