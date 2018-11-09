package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

public class ConnectonPooll {

    private Vector<Connection> availableConns = new Vector<Connection>();
    private Vector<Connection> usedConns = new Vector<Connection>();
    private static ConnectonPooll ourInstance = new ConnectonPooll(15);
    private ResourceBundle resourseBundle= ResourceBundle.getBundle("db");
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
            availableConns.addElement(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(resourseBundle.getString("url"),resourseBundle.getString("login"),resourseBundle.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized Connection retrieve() {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c){
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }
}