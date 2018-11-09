package service;

import entity.Basket;
import entity.User;
import factory.Action;
import dao.BasketDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static utill.KinoGroupConst.*;


public class AddGoodBasketAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private String MESSAGE;

    private Basket addGoodInBasket(String name, int id, int price, int userId) throws SQLException {
        Basket basket = new Basket();
        basket.setGoodName(name);
        basket.setGoodId(id);
        basket.setGoodPrice(price);
        BasketDAO basketDAO = new BasketDAO();
        basketDAO.addGood(basket, userId);
        return basket;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute("local"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);
        User user = (User) httpSession.getAttribute(USER);
        String view;
        String goodName = request.getParameter(PRODUCT_NAME);
        int goodId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        int goodPrice = Integer.parseInt(request.getParameter(PRODUCT_PRICE));
        if (Validator.checkFieldsOnNull(user)) {
            int userId = user.getUserId();
            addGoodInBasket(goodName, goodId, goodPrice, userId);
            view = WELCOME_JSP;
            logger.info("Product : " + goodName + " add in basket successfully");
        } else {
            MESSAGE = resourceBundle.getString("needAutorisation");
            request.setAttribute("message", MESSAGE);
            logger.info("Product : " + goodName + " not add in basket");
            view = AUTORISATION_JSP;
        }
        return view;
    }
}