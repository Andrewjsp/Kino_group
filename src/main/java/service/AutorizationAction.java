package service;


import encryption.Encryption;
import entity.User;
import exeption.ConnectionExecption;
import factory.Action;
import dao.UserDAO;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static utill.KinoGroupConst.*;

public class AutorizationAction implements Action {
    private String MESSAGE;
    private  UserDAO userDAO = new UserDAO();

    private User getAllInform(String login) throws SQLException, ConnectionExecption {
        User user = userDAO.getAllInformAboutUser(login);
        return user;
    }

    private boolean checkLoginAndPasswordUser(String login, String password) throws SQLException, NoSuchAlgorithmException, ConnectionExecption {
        Encryption encryption = new Encryption();
        String hashPassword = encryption.getHashPassword(password);

        User user = userDAO.checkLoginAndPasswordUser(login, hashPassword);
        boolean check =Validator.checkFieldsOnNull(user.getUserLogin());
        return check;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, NoSuchAlgorithmException, ConnectionExecption {
        String userLogin = request.getParameter(LOGIN);
        String userPassword = request.getParameter(PASSWORD);
        String view;
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);

        if (checkLoginAndPasswordUser(userLogin, userPassword)) {
            User user = getAllInform(userLogin);
            httpSession.setAttribute(USER, user);
            view = PERSONAL_AREA_JSP;
        } else {
            MESSAGE = resourceBundle.getString("incorrectLoginOrPassword");
            request.setAttribute("message", MESSAGE);
            view = AUTORISATION_JSP;
        }
        return view;
    }
}
