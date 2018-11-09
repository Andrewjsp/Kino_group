package service;

import entity.User;
import factory.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static utill.KinoGroupConst.*;

public class  ShowInformForChangeRoleAction implements Action {
    @Override
    public String execute(HttpServletRequest request){
        String userLogin=request.getParameter(USER_LOGIN);
        int userId=Integer.parseInt(request.getParameter(USER_ID));
        int userRole=Integer.parseInt(request.getParameter(USER_ROLE));
        HttpSession session=request.getSession();
        User user=new User();
        user.setUserLogin(userLogin);
        user.setUserId(userId);
        user.setUserRole(userRole);
        session.setAttribute(EDIT_USER,user);
        return CHANGE_USER_ROLE_JSP;

    }
}
