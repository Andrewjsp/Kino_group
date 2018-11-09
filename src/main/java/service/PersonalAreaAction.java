package service;


import factory.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import static utill.KinoGroupConst.*;

public class  PersonalAreaAction implements Action {

    @Override
    public String execute(HttpServletRequest request){
        String view;
        HttpSession httpSession = request.getSession();

        if (Validator.checkFieldsOnNull(httpSession.getAttribute(USER))) {
            view = PERSONAL_AREA_JSP;
        } else {
            view = AUTORISATION_JSP;
        }
        return view;
    }
}
