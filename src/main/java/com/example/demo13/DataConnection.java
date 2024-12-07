package com.example.demo13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataConnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/final";
        private static final String USER = "postgres";
        private static final String PASSWORD = "75554446";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
}


