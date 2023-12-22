package com.company.dao.inter;

import java.sql.*;

public abstract class AbstractDAO {

    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "2000";
        return DriverManager.getConnection(url, username, password);
    }
}