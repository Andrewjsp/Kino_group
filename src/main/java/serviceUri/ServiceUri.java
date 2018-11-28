package serviceUri;

import factory.*;
import service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static utill.KinoGroupConst.*;

public class ServiceUri implements ActionFactory {
    private static final ServiceUri serviceUser = new ServiceUri();

    private ServiceUri() {
        getUri();
    }

    public static ServiceUri getInstance() {
        return serviceUser;
    }

    private Map<String, Action> getUri() {
        Map<String, Action> map = new HashMap<>();
        map.put(SHOW_ALL_GOODS, new ShowProductsForDeleteAction());
        map.put(SHOW_INFORM_ABOUT_USER, new ShowInformForChangeRoleAction());
        map.put(CHANGE_ROLE, new ChangeRoleAction());
        map.put(SHOW_PRODUCTS_IN_BASKET, new ShowGoodsForBasketAction());
        map.put(DELETE_PRODUCT, new DeleteGoodsAction());
        map.put(SHOW_PRODUCTS, new ShowGoodsAction());
        map.put(REGISTRATION, new RegistrationAction());
        map.put(SHOW_SUBPRODUCT, new ShowGoodsForAddAction());
        map.put(ADD_PRODUCT_IN_BASKET, new AddGoodBasketAction());
        map.put(SHOW_MUSIC, new ShowMusicAction());
        map.put(ADD_PRODUCT, new AddGoodAction());
        map.put(AUTORISATION, new AutorizationAction());
        map.put(EXIT, new ExitAction());
        map.put(SHOW_USERS, new ShowUserAction());
        map.put(DELETE_USER, new DeleteUserAction());
        map.put(BUY_PRODUCT, new BuyGoodAction());
        map.put(CHANGE_LOCALE, new ChangeLocaleAction());
        map.put(DELETE_PRODUCT_IN_BASKET, new DeleteGoodFromBasketAction());
        return map;
    }

    @Override
    public Action getAction(HttpServletRequest request) {
        Action action=null;
        String uri=request.getRequestURI();
        for(Map.Entry<String,Action>map:getUri().entrySet()){
            if (uri.equals(map.getKey())){
                action=map.getValue();
            }
        }
        if (action==null){
            action=new ErrorAction();
        }
        return action;
    }


}
