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
import java.util.List;


import static utill.KinoGroupConst.*;

public class DeleteGoodFromBasketAction implements Action {
    private Logger logger= LogManager.getRootLogger();
    private  BasketDAO basketDAO=new BasketDAO();
private List<Basket> removeGoodForBasket(int userId,int orderId) throws SQLException {
    basketDAO.removeGoodFromBasket(orderId);
    List<Basket>list=basketDAO.showAllGoodsInBasket(userId);
    return list;
}
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute(USER);
        int orderId=Integer.parseInt(request.getParameter(ORDER_ID));
        int totalSum=(Integer)session.getAttribute(TOTAL_SUM);
        int goodPrice=Integer.parseInt(request.getParameter(PRODUCT_PRICE));
        totalSum-=goodPrice;
        int userId=user.getUserId();
        request.setAttribute(PRODUCT_FOR_BASKET,removeGoodForBasket(userId,orderId));
        session.setAttribute(TOTAL_SUM,totalSum);
        logger.info("OrderId : "+orderId+"remove from basket");
return SHOW_PRODUCTS_IN_BASKET_JSP;
}
}
