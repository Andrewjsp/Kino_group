package service;

import factory.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static utill.KinoGroupConst.LOCAL;
import static utill.KinoGroupConst.NOT_PAGE_JSP;

public class  ErrorAction implements Action {
    private String MESSAGE;
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String nameBundle = Validator.getNameBundle((String) httpSession.getAttribute(LOCAL));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(nameBundle);
        MESSAGE = resourceBundle.getString("notPage");
        request.setAttribute("message", MESSAGE);
        return NOT_PAGE_JSP;
    }
}
