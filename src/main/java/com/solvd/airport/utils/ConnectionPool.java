package com.solvd.airport.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static LinkedBlockingQueue<Connection> pool;
    private static final int MAX_POOL_CAPACITY = 3;
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
            pool = new LinkedBlockingQueue<>();
            logger.info("No ConnectionPool instance. Creating now.");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                InputStream input = new FileInputStream("src/main/resources/db.properties");
                Properties prop = new Properties();
                logger.info("Creating database server credentials from properties file.");
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
        if (existingConnectionsCount < MAX_POOL_CAPACITY)
        {
            logger.info("No connections pooled. Creating connection.");

            try {
                existingConnectionsCount++;
                return DriverManager.getConnection(url, user, password);
            }
            catch (Exception e) {
                logger.error(e);
            }
        }

        Connection con = null;

        synchronized (pool) {
            logger.info("Retrieving next available connection from pool...");
            con = pool.take();
            logger.info("Connection successfully retrieved from pool.");
        }

        return con;
    }

    public void releaseConnection(Connection con) {
        synchronized (pool)
        {
            if (pool.add(con) && pool.size() <= MAX_POOL_CAPACITY)
                logger.info("Connection successfully pooled.");
            else
                logger.error("Connection pooling failed. An external connection may have been " +
                        "or is currently attempting to be added to the pool.");
        }
    }
}