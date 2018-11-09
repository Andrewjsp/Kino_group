package service;


import dao.BasketDAO;
import entity.Good;
import factory.Action;
import dao.GoodsDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static utill.KinoGroupConst.*;

public class DeleteGoodsAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private String MESSAGE;
    private GoodsDAO goodsDAO = new GoodsDAO();
    private Good good;
    private BasketDAO basketDAO = new BasketDAO();

    private boolean checkSubGoods(int goodId) throws SQLException {
        boolean flag = true;
        good = goodsDAO.checkAlbumIsEmpry(goodId);
        if (good.getProductName() == null) {
            flag = false;
        }
        return flag;
    }

    private List<Good> deleteGood(int goodId) throws SQLException {
        basketDAO.removeGoodForAdmin(goodId);
        goodsDAO.deleteGood(goodId);
        List<Good> goods = goodsDAO.showAllProducts();
        return goods;
    }

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);
        MESSAGE = resourceBundle.getString("productDelete");
        int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));
        if (!checkSubGoods(productId)) {
            request.setAttribute(SHOW_ALL_PRODUCTS, deleteGood(productId));
            request.setAttribute("message", MESSAGE);
        } else {
            request.setAttribute("message", resourceBundle.getString("notDelete"));
            request.setAttribute(SHOW_ALL_PRODUCTS, goodsDAO.showAllProducts());
        }
        logger.info("Product id delete from data base : " + productId);
        return DELETE_PRODUCT_JSP;
    }
}
