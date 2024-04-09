package com.yourcompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    static {
        try {
            // Ensure the Oracle JDBC driver is registered
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@ElectroWin11:1521:xe";
        // Replace 'your_username' and 'your_password' with your database credentials
        return DriverManager.getConnection(url, "SYS as SYSDBA", "wes1");
    }
}