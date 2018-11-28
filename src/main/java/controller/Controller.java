package controller;

import exeption.ConnectionExecption;
import factory.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serviceUri.ServiceUri;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static utill.KinoGroupConst.*;

public class Controller extends HttpServlet {
    private Logger logger = LogManager.getRootLogger();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void factory(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ServiceUri serviceUri = ServiceUri.getInstance();
        Action action = serviceUri.getAction(request);
        String view;
        try {
            view = action.execute(request);
            request.getRequestDispatcher(view).forward(request, response);
        } catch (IOException | ServletException | SQLException | ConnectionExecption | ClassNotFoundException | NoSuchAlgorithmException | InterruptedException e) {
            logger.info(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getMethod();
        if (method.equals(GET)) {
            doGet(req, resp);
        } else if (method.equals(POST)) {
            doPost(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        factory(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        factory(req, resp);
    }

}
