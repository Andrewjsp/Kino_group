package connectionPool;

import exeption.ConnectionExecption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectonPooll {
    private Logger logger = LogManager.getRootLogger();
    private BlockingQueue<Connection> blockingQueue = new ArrayBlockingQueue<>(100);
    private static ConnectonPooll ourInstance = new ConnectonPooll(15);
    private ResourceBundle resourseBundle = ResourceBundle.getBundle("db");

    public static ConnectonPooll getInstance() {
        return ourInstance;
    }

    private ConnectonPooll(int initConnCnt) {
        try {
            Class.forName(resourseBundle.getString("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < initConnCnt; i++) {
            try {
                blockingQueue.put(getConnection());
            } catch (InterruptedException e) {
                logger.info(e);
            }
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(resourseBundle.getString("url"), resourseBundle.getString("login"), resourseBundle.getString("password"));
        } catch (Exception e) {
           logger.info(e);
        }
        return conn;
    }

    public Connection retrieve() {
        Connection connection = null;
        try {
            connection = blockingQueue.take();
        } catch (InterruptedException e) {
            logger.info(e);
        }
        return connection;
    }

    public void putback(Connection connection) throws ConnectionExecption {
        if (connection != null) {
            try {
                blockingQueue.put(connection);
            } catch (InterruptedException e) {
                logger.info(e);
            }
        } else {
            throw new ConnectionExecption();
        }
    }


}

