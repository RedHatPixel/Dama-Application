package com.db.connection;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLDataException;

public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Connection connection;
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    private DatabaseConnection() {
        
    }
    
    public void connectToDatabase() throws MySQLDataException {
        final String database = "dama";
        final String userName = "camar";
        final String password = "camar123456";
        final String url = "jdbc:mysql://localhost:3305/" + database;
        
//        
//        // Initialize the connection
//        connection = 
//        System.out.println("Database connection established successfully!");
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
}
