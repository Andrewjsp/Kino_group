package listener;


import entity.Category;
import entity.Language;
import dao.CategoryDAO;
import dao.LanguageDAO;
import exeption.ConnectionExecption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.List;

import static utill.KinoGroupConst.*;

public class ContextListener implements ServletContextListener {
    private Logger logger = LogManager.getRootLogger();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        try {
            List<Language> languageList = new LanguageDAO().showAllLanguage();
            List<Category> categoryList = new CategoryDAO().showDefaultCategory();
            servletContext.setAttribute(ALL_CATEGORY, categoryList);
            servletContext.setAttribute(LANGUAGES, languageList);
            servletContext.setAttribute(LANGUAGE_ID, DEFAULT_LANGUAGE_ID);

        } catch (SQLException|ConnectionExecption e) {
            logger.info(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
