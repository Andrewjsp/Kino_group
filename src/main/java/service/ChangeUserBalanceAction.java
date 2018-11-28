package service;

import entity.User;
import dao.UserDAO;
import exeption.ConnectionExecption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static utill.KinoGroupConst.USER;

public class   ChangeUserBalanceAction {
    private Logger logger= LogManager.getRootLogger();
    private   UserDAO userDAO=new UserDAO();
 public int newUserBalanceBeforeChange(HttpServletRequest request) throws SQLException, ConnectionExecption {
    HttpSession session=request.getSession();
    User user=(User)session.getAttribute(USER);
    int balance=userDAO.getUserBalance(user.getUserId());
    logger.info("Balance changed,user id : "+user.getUserId());
    return balance;
}
}
