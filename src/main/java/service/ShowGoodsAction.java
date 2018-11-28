package service;
import exeption.ConnectionExecption;
import factory.Action;
import dao.GoodsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import static utill.KinoGroupConst.*;

public class  ShowGoodsAction implements Action {
    private final int ALBUM_CATEGORY_ID = 1;
    private GoodsDAO goodsDAO = new GoodsDAO();

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        String view;
        int categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID));
        if (categoryId == ALBUM_CATEGORY_ID) {
            view = SHOW_ALBUM_JSP;
            request.setAttribute("Good", goodsDAO.showAlbum());
        } else {
            request.setAttribute("Good", goodsDAO.showClothesByCategory(categoryId));
            view = SHOW_CLOTHES_JSP;
        }
        return view;
    }
}
