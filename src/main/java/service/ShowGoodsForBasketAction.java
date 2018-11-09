package service;

import entity.Basket;
import entity.User;
import factory.Action;
import dao.BasketDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


import static utill.KinoGroupConst.*;

public class ShowGoodsForBasketAction implements Action {
    private BasketDAO basketDAO = new BasketDAO();

    private List<Basket> showGoodsforBasket(int userId) throws SQLException{
        List<Basket> list = basketDAO.showAllGoodsInBasket(userId);
        return list;
    }

    private int totalSummGoods(int userId) throws SQLException{
        BasketDAO basketDAO = new BasketDAO();
        List<Basket> list = basketDAO.showAllGoodsInBasket(userId);
        int totalSum = 0;
        for (Basket basket : list) {
            totalSum += basket.getGoodPrice();
        }
        return totalSum;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String view;
        if (Validator.checkFieldsOnNull(user)) {
            int userId = user.getUserId();
            session.setAttribute(PRODUCT_FOR_BASKET, showGoodsforBasket(userId));
            session.setAttribute(TOTAL_SUM, totalSummGoods(userId));
            view = SHOW_PRODUCTS_IN_BASKET_JSP;
        } else {
            view = AUTORISATION_JSP;
        }
        return view;
    }
}
