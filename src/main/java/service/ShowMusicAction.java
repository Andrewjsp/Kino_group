package service;

import factory.Action;
import dao.GoodsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static utill.KinoGroupConst.*;

public class  ShowMusicAction implements Action {
    private GoodsDAO goodsDAO = new GoodsDAO();

    @Override
    public String execute(HttpServletRequest request) throws SQLException, InterruptedException {
        int albumId = Integer.parseInt(request.getParameter(ALBUM_ID));
        request.setAttribute("music", goodsDAO.showMusicForId(albumId));
        return SHOW_MUSIC_JSP;
    }
}
