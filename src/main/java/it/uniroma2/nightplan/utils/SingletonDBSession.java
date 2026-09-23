package it.uniroma2.nightplan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import static it.uniroma2.nightplan.view.EssentialGUI.logger;


/**
 * Singleton that hands out JDBC connections to the DAO layer.
 * Connection parameters are read from {@link AppConfig}.
 */
public class SingletonDBSession {
    private static SingletonDBSession instance = null;
    private final String url;
    private final String username;
    private final String password;
    protected Connection connection = null;

    private SingletonDBSession() {
        this.url = AppConfig.dbUrl();
        this.username = AppConfig.dbUsername();
        this.password = AppConfig.dbPassword();
    }

    public Connection getConnection() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            return this.connection;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Connection to DB failed!", e);
            return null;
        }
    }

    public static synchronized SingletonDBSession getInstance() {
        //singleton method
        if (SingletonDBSession.instance == null) {
            SingletonDBSession.instance = new SingletonDBSession();
        }
        return SingletonDBSession.instance;
    }

    public void closeConn() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
