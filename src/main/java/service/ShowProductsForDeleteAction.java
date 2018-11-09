package service;


import factory.Action;
import dao.GoodsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import static utill.KinoGroupConst.*;

public class  ShowProductsForDeleteAction implements Action {
    private GoodsDAO goodsDAO = new GoodsDAO();

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute(SHOW_ALL_PRODUCTS, goodsDAO.showAllProducts());
        return DELETE_PRODUCT_JSP;
    }
}
