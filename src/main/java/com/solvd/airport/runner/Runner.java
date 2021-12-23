package com.solvd.airport.runner;

import com.solvd.airport.utils.ConnectionPool;

import java.sql.Connection;

public class Runner {
    public static void main(String args[]) throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con1 = connectionPool.getConnection();
        Connection con2 = connectionPool.getConnection();
        Connection con3 = connectionPool.getConnection();
        connectionPool.releaseConnection(con1);
        connectionPool.releaseConnection(con2);
        connectionPool.releaseConnection(con3);
        Connection con4 = connectionPool.getConnection();
        connectionPool.releaseConnection(con4);
    }
}
