package filter;


import entity.User;
import service.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import static utill.KinoGroupConst.*;

public class FilterRole implements javax.servlet.Filter {
    private final int ROLE_PAGE_FOR_FREE_ACCESS = -1;
    private final int ROLE_PAGE_ADMIN = 1;
    private final int ROLE_PAGE_USER = 0;
    private Map<String, Integer> map = new HashMap<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        map.put(CHANGE_USER_ROLE_JSP, ROLE_PAGE_ADMIN);
        map.put(DELETE_PRODUCT_JSP, ROLE_PAGE_ADMIN);
        map.put(PERSONAL_AREA_JSP,ROLE_PAGE_USER);
        map.put(SHOW_ALL_USERS_JSP, ROLE_PAGE_ADMIN);
        map.put(SHOW_PRODUCTS_IN_BASKET_JSP, ROLE_PAGE_USER);
        map.put(SHOW_CLOTHES_JSP, ROLE_PAGE_FOR_FREE_ACCESS);
        map.put(ADD_GOOD_JSP, ROLE_PAGE_ADMIN);
        map.put(WELCOME_JSP,ROLE_PAGE_FOR_FREE_ACCESS);
        map.put(AUTORISATION_JSP,ROLE_PAGE_FOR_FREE_ACCESS);
        map.put(REGISTRATON_JSP,ROLE_PAGE_FOR_FREE_ACCESS);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (!Validator.checkFieldsOnNull(user)) {
            user=new User();
            user.setUserRole(ROLE_PAGE_FOR_FREE_ACCESS);
            request.getRequestDispatcher(checkUserRole(request.getRequestURI(),user.getUserRole())).forward(servletRequest, servletResponse);
        }
        else {
                request.getRequestDispatcher(checkUserRole(request.getRequestURI(),user.getUserRole())).forward(servletRequest, servletResponse);
        }


    }
    private String checkUserRole(String pathURI, int userRole) {
        String viewPath=null;
        for (Map.Entry<String, Integer> mapa : map.entrySet()) {
            if (pathURI.equals(mapa.getKey())) {
                if (userRole >= mapa.getValue()) {
                    viewPath = mapa.getKey();
                }
                else {
                    viewPath=ERROR_JSP;
                }
            }
        }
        if (!Validator.checkFieldsOnNull(viewPath)){
            viewPath=NOT_PAGE_JSP;

        }
        return viewPath;
    }
    @Override
    public void destroy() {

    }

}

