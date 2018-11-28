package service;
import dao.ColorDAO;
import dao.SizeDAO;
import exeption.ConnectionExecption;
import factory.Action;
import dao.CategoryDAO;
import dao.GoodsDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static utill.KinoGroupConst.*;

public class  ShowGoodsForAddAction implements Action {
    private GoodsDAO goodsDAO = new GoodsDAO();
    private ColorDAO colorDAO = new ColorDAO();
    private SizeDAO sizeDAO = new SizeDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();


    @Override
    public String execute(HttpServletRequest request) throws SQLException,ConnectionExecption {
        ServletContext servletContext=request.getServletContext();
        int languageId= (int) servletContext.getAttribute(LANGUAGE_ID);
        request.setAttribute(ALBUM, goodsDAO.showAlbum());
        request.setAttribute(COLOR, colorDAO.showAllColor());
        request.setAttribute(SIZE, sizeDAO.showAllSize());
        request.setAttribute(CATEGORY,categoryDAO.showClothesByLanguageId(languageId));
        return ADD_GOOD_JSP;
    }
}
