package service;

import dao.CategoryDAO;
import entity.Category;
import exeption.ConnectionExecption;
import factory.Action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.sql.SQLException;
import java.util.List;

import static utill.KinoGroupConst.ALL_CATEGORY;
import static utill.KinoGroupConst.WELCOME_JSP;

public class  ExitAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        ServletContext servletContext=request.getServletContext();
        HttpSession httpSession=request.getSession();
        httpSession.invalidate();
        List<Category> categoryList=new CategoryDAO().showDefaultCategory();
        servletContext.setAttribute(ALL_CATEGORY,categoryList);
        return WELCOME_JSP;
    }
}
