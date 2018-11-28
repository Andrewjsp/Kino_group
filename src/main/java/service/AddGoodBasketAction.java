package service;

import entity.Basket;
import entity.User;
import exeption.ConnectionExecption;
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
    private Basket basket = new Basket();

    private void addGoodInBasket(String name, int id, int price, int userId) throws SQLException, ConnectionExecption {
        basket.setGoodName(name);
        basket.setGoodId(id);
        basket.setGoodPrice(price);
        BasketDAO basketDAO = new BasketDAO();
        basketDAO.addGood(basket, userId);
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String view;
        String goodName = request.getParameter(PRODUCT_NAME);
        int goodId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        int goodPrice = Integer.parseInt(request.getParameter(PRODUCT_PRICE));
        int userId = user.getUserId();
        addGoodInBasket(goodName, goodId, goodPrice, userId);
        view = WELCOME_JSP;
        logger.info("Product : " + goodName + " add in basket successfully");
        return view;
    }
}