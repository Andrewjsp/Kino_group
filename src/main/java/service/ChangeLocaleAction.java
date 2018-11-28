package service;

import dao.CategoryDAO;
import dao.LanguageDAO;
import exeption.ConnectionExecption;
import factory.Action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static utill.KinoGroupConst.*;

public class ChangeLocaleAction implements Action {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private LanguageDAO languageDAO = new LanguageDAO();

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ConnectionExecption {
        ServletContext servletContext = request.getServletContext();
        int languageId = Integer.parseInt(request.getParameter(LANGUAGE_LIST));
        servletContext.setAttribute(LANGUAGE_ID, languageId);
        HttpSession session = request.getSession();
        servletContext.setAttribute(ALL_CATEGORY, categoryDAO.showCategoryByIdLanguage(languageId));
        session.setAttribute(LOCAL, languageDAO.getLocale(languageId));
        return WELCOME_JSP;
    }
}
