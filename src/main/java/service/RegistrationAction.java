package service;


import encryption.Encryption;
import entity.User;
import factory.Action;
import dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;


import static utill.KinoGroupConst.*;

public class RegistrationAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private String MESSAGE;
    private   UserDAO userDAO = new UserDAO();

    private void registrationUser(String name, String login, String password) throws SQLException, NoSuchAlgorithmException{
        Encryption encryption = new Encryption();
        String hashPassword = encryption.getHashPassword(password);
        User user = new User();
        user.setUserName(name);
        user.setUserLogin(login);
        user.setUserPassword(hashPassword);
        userDAO.addUser(user);
    }

    private boolean checkUserLoginDataBase(String login) throws SQLException {
        User user = userDAO.checkUserLogin(login);
        boolean checkLogin = false;
        if (user.getUserLogin() == null) {
            checkLogin = true;
        }
        return checkLogin;
    }

    private User getInform(String login) throws SQLException{
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getAllInformAboutUser(login);
        return user;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, NoSuchAlgorithmException {
        String view = null;
        String userLogin = request.getParameter(LOGIN);
        String userPassword = request.getParameter(PASSWORD);
        String userName = request.getParameter(USER_NAME);
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);

        if (Validator.checkInformAboutUser(userLogin, userPassword)) {
            if (checkUserLoginDataBase(userLogin)) {
                registrationUser(userName, userLogin, userPassword);
                httpSession.setAttribute(USER, getInform(userLogin));
                MESSAGE = resourceBundle.getString("registrationSuccess");
                request.setAttribute("message", MESSAGE);
                logger.info("User login: " + userLogin + " add data base");
                view = WELCOME_JSP;
            } else {
                MESSAGE = resourceBundle.getString(" suchLoginDBthere");
                request.setAttribute("message", MESSAGE);
                view = REGISTRATON_JSP;
                request.setAttribute("Error", "/view/Registration.jsp");

            }
        } else {
            MESSAGE = resourceBundle.getString("incorrectLoginOrPassword");
            request.setAttribute("message", MESSAGE);
            view = REGISTRATON_JSP;
            logger.info("User login: " + userLogin + "not add data base");
        }
        return view;
    }
}