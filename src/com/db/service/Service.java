package com.db.service;

import com.db.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Service {
    
    private Connection connection;
    
    Service() {
        connection = DatabaseConnection.getInstance().getConnection();
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
    
    public boolean isConnectionValid() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
}
