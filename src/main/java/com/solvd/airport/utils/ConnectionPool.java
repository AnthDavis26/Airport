package com.solvd.airport.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.*;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static ConcurrentLinkedQueue<Connection> pool;
    private static final int MAX_POOL_CAPACITY = 10;
    private static int usedConnectionsCount = 0;
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String url = "52.59.193.212:3306";
    private static final String user = "root";
    private static final String password = "devintern";

    private ConnectionPool(){} // Prevent construction

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();

        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        if (usedConnectionsCount == 0)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);
                pool.add(con);
            } catch (Exception e) {
                logger.error(e);
            }
        }

        while (usedConnectionsCount == MAX_POOL_CAPACITY)
            pool.wait();

        usedConnectionsCount++;
        return pool.poll();
    }

    public void releaseConnection(Connection con) {
        pool.add(con);
        usedConnectionsCount--;
    }
}