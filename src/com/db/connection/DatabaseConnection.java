package com.db.connection;

import com.db.service.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.List;

public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Connection connection;
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    private DatabaseConnection() {}
    
    public void reconnect(final Service service) {
        try {
            if (!isConnectionValid()) {
                connectToDatabase();
            }
            service.setConnection(connection);
        } catch (SQLException e) { 
            System.err.println("Reconnection failed");
        }
    }
    
    public void reconnect(final List<Service> services) {
        try {
            if (!isConnectionValid()) {
                connectToDatabase();
            }
            for (final Service service : services) {
                service.setConnection(connection);
            }
        } catch (SQLException e) { 
            System.err.println("Reconnection failed");
        }
    }
    
    public boolean isConnectionValid() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean tryConnectionIfValid() {
        try {
            connectToDatabase();
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void connectToDatabase() throws SQLException {
        final String database = "dama";
        final String userName = "camar";
        final String password = "camar123456";
        final String url = "jdbc:mysql://localhost:3306/" + database;
        connection = DriverManager.getConnection(url, userName, password);
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
}
