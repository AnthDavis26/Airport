package com.solvd.airport.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static BlockingQueue<Connection> pool;
    private static final int MAX_POOL_CAPACITY = 10;
    private static Integer poolCount = 0;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static String url;
    private static String user;
    private static String password;
    private static final String DB_PROPERTIES_DIRECTORY = "src/main/resources";

    private ConnectionPool(){} // Prevent construction

    public static ConnectionPool getInstance() {
        if (instance == null)
        {
            instance = new ConnectionPool();
            pool = new ArrayBlockingQueue<>(MAX_POOL_CAPACITY);
            LOGGER.info("No connection pool initialized. Creating now.");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                InputStream input = new FileInputStream(DB_PROPERTIES_DIRECTORY + "/db.properties");
                Properties prop = new Properties();
                LOGGER.info("Creating database server credentials from properties file in " +
                        DB_PROPERTIES_DIRECTORY + ".");
                prop.load(input);
                url = prop.getProperty("url");
                user = prop.getProperty("username");
                password = prop.getProperty("password");
            } catch (Exception e) {
                LOGGER.error(e);
            } finally {
                LOGGER.info("Connection pool initialized.");
            }
        }

        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        LOGGER.info("Connection pool capacity: " + poolCount + "/" + MAX_POOL_CAPACITY + ".");

        synchronized (poolCount) {
            if (poolCount < MAX_POOL_CAPACITY) {
                LOGGER.info("Creating new connection.");

                try {
                    return DriverManager.getConnection(url, user, password);
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }

        poolCount--;
        LOGGER.info("Retrieved connection from pool.");
        return pool.take();
    }

    public void releaseConnection(Connection con) {
        synchronized (poolCount) {
            if (pool.offer(con)) {
                LOGGER.info("Connection pooled.");
                poolCount++;
            } else
                LOGGER.error("Connection pooling failed: pool is full.");

        }
    }
}