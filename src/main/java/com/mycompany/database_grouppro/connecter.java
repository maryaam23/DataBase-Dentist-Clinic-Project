package com.mycompany.database_grouppro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connecter {
    public Connection getConnection() {
        String databaseName = "smileclinic";
        String databaseUser = "root";
        String databasePassword = "2312003";
        String url = "jdbc:mysql://localhost/" + databaseName + "?useSSL=false&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();  // or use a logging framework
            throw new RuntimeException("Failed to connect to the database", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // or use a logging framework
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
}



