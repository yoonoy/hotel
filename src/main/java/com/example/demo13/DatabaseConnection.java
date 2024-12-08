package com.example.demo13;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {
    Connection getConnection() throws SQLException;
}
