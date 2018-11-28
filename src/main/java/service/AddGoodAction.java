package service;

import dao.CategoryDAO;
import dao.ColorDAO;
import dao.SizeDAO;
import entity.Good;
import exeption.ConnectionExecption;
import factory.Action;
import dao.GoodsDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.*;


import static utill.KinoGroupConst.*;


public class AddGoodAction implements Action {
    private Logger logger = LogManager.getRootLogger();
    private String MESSAGE;
    private GoodsDAO goodsDAO = new GoodsDAO();
    private Good good = new Good();
    private ColorDAO colorDAO = new ColorDAO();
    private SizeDAO sizeDAO = new SizeDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    private int parser(String id) {
        Integer parser = Integer.parseInt(id);
        return parser;
    }

    private void addGood(String nameGood, String descriptionGood, String priceGood) throws SQLException, ConnectionExecption {
        good.setProductName(nameGood);
        good.setDescription(descriptionGood);
        good.setProductPrice(parser(priceGood));
        goodsDAO.addAlbum(good);
    }

    private void addGood(String nameGood, String albumId, String descriptionGood, String priceGood) throws SQLException, ConnectionExecption {
        good.setProductName(nameGood);
        good.setParentId(parser(albumId));
        good.setDescription(descriptionGood);
        good.setProductPrice(parser(priceGood));
        goodsDAO.addMusic(good);
    }

    private void addGood(String nameGood, String categoryId, String descriptionGood, String priceGood, String sizeId, String colorId) throws SQLException, ConnectionExecption {
        good.setProductName(nameGood);
        good.setCategoryId(parser(categoryId));
        good.setDescription(descriptionGood);
        good.setProductPrice(parser(priceGood));
        good.setSizeId(parser(sizeId));
        good.setColorId(parser(colorId));
        goodsDAO.addClothes(good);
    }


    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        HttpSession httpSession = request.getSession();
        ServletContext servletContext = request.getServletContext();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);
        String productName = request.getParameter(PRODUCT_NAME);
        String discription = request.getParameter(DESCRIPTION);
        String price = request.getParameter(PRODUCT_PRICE);
        String keyProduct = request.getParameter(KEY_PRODUCT);
        int languageId = (int) servletContext.getAttribute(LANGUAGE_ID);
        switch (keyProduct) {
            case ALBUM:
                if (Validator.checkFieldsOnIsEmpty(productName, discription, price) && Validator.checkPrice(price)) {
                    addGood(productName, discription, price);
                    MESSAGE = resourceBundle.getString("saveProduct");
                    logger.info("Album : " + productName + " add successfully");
                } else {
                    MESSAGE = resourceBundle.getString("incorrectData");
                    logger.info("Album : " + productName + " not add");
                }
                break;
            case MUSIC:
                String albumId = request.getParameter(ALBUM_ID);
                if (Validator.checkFieldsOnIsEmpty(productName, albumId, discription, price) && Validator.checkPrice(price)) {
                    addGood(productName, albumId, discription, price);
                    MESSAGE = resourceBundle.getString("saveProduct");
                    logger.info("Music : " + productName + " add successfully");
                } else {
                    MESSAGE = resourceBundle.getString("incorrectData");
                    logger.info("Music : " + productName + " not add");
                }
                break;
            case CLOTHES:
                String sizeId = request.getParameter(SIZE_ID);
                String colorId = request.getParameter(COLOR_ID);
                String categoryId = request.getParameter(CATEGORY_ID);
                if (Validator.checkFieldsOnIsEmpty(productName, categoryId, discription, price, sizeId, colorId) && Validator.checkPrice(price)) {
                    addGood(productName, categoryId, discription, price, sizeId, colorId);
                    MESSAGE = resourceBundle.getString("saveProduct");
                    logger.info("Thing : " + productName + "add successfully");
                } else {
                    MESSAGE = resourceBundle.getString("incorrectData");
                    logger.info("Thing : " + productName + "not add");
                }
                break;
        }
        request.setAttribute(ALBUM, goodsDAO.showAlbum());
        request.setAttribute(COLOR, colorDAO.showAllColor());
        request.setAttribute(SIZE, sizeDAO.showAllSize());
        request.setAttribute(CATEGORY, categoryDAO.showClothesByLanguageId(languageId));
        request.setAttribute("message", MESSAGE);
        return ADD_GOOD_JSP;
    }
}
