package service;

import entity.User;
import exeption.ConnectionExecption;
import factory.Action;
import dao.BasketDAO;
import dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ResourceBundle;


import static utill.KinoGroupConst.*;

public class BuyGoodAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private String MESSAGE;
    private BasketDAO basketDAO = new BasketDAO();
    private UserDAO userDAO = new UserDAO();



    private boolean checkUserBalance(int totalSum, int userId) throws SQLException, ConnectionExecption {
        int userBalance = userDAO.getUserBalance(userId);
        boolean checkBalance = true;
        if (totalSum > userBalance) {
            checkBalance = false;
        } else {
            userBalance -= totalSum;
            basketDAO.buyProduct(userBalance,userId);
        }
        return checkBalance;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);
        User user = (User) httpSession.getAttribute(USER);
        ChangeUserBalanceAction changeUserBalanceAction = new ChangeUserBalanceAction();
        int totalSum = Integer.parseInt(request.getParameter(TOTAL_SUM));
        int userId = user.getUserId();
        if (checkUserBalance(totalSum, userId)) {
            int newUserBalance = changeUserBalanceAction.newUserBalanceBeforeChange(request);
            user.setBalance(newUserBalance);
            httpSession.setAttribute(USER, user);
            totalSum = 0;
            httpSession.setAttribute(TOTAL_SUM, totalSum);
            logger.info("Product buy successfully,user id : " + userId);
        } else {
            MESSAGE = resourceBundle.getString("dontEnoughMoney");
            request.setAttribute("message", MESSAGE);
        }
        return SHOW_PRODUCTS_IN_BASKET;
    }
}
