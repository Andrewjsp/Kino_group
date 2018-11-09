package service;


import encryption.Encryption;
import entity.User;
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

    private User getAllInform(String login) throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getAllInformAboutUser(login);
        return user;
    }

    private boolean checkLoginAndPasswordUser(String login, String password) throws SQLException, NoSuchAlgorithmException {
        Encryption encryption = new Encryption();
        boolean check = true;
        String hashPassword = encryption.getHashPassword(password);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.checkLoginAndPasswordUser(login, hashPassword);
        if (user.getUserLogin() == null) {
            check = false;
        }
        return check;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, NoSuchAlgorithmException {
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
