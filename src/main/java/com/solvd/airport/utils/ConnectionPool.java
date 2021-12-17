package com.solvd.airport.utils;

import java.sql.Connection;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connections = new ArrayList<>();

    public static ConnectionPool getInstance(){
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        // TODO: Implement getConnection()
        return null;
    }

    public void releaseConnection(){}
}