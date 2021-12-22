package com.solvd.airport.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.*;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static LinkedBlockingQueue<Connection> pool;
    private static final int MAX_POOL_CAPACITY = 10;
    private static int existingConnectionsCount = 0;
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static String url;
    private static String user;
    private static String password;

    private ConnectionPool(){} // Prevent construction

    public static ConnectionPool getInstance() {
        if (instance == null)
        {
            instance = new ConnectionPool();

            try {
                InputStream input = new FileInputStream("src/main/resources/db.properties");
                Properties prop = new Properties();
                prop.load(input);
                url = prop.getProperty("url");
                user = prop.getProperty("username");
                password = prop.getProperty("password");
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        if (existingConnectionsCount < MAX_POOL_CAPACITY) {
            try {
                existingConnectionsCount++;
                return DriverManager.getConnection(url, user, password);
            }
            catch (SQLException e) {
                logger.error(e);
            }
        }

        return pool.take();
    }

    public void releaseConnection(Connection con) {
        pool.add(con);
    }
}