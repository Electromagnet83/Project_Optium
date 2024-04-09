package com.yourcompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailLookupService {

    public static String lookupEmail(String email) {
        String sql = "SELECT * FROM OPT_OUT_ALL_EMAILS WHERE UPPER(email_address) = UPPER(?)";
        String result = "Email not found.";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = "Email found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Error during database operation: " + e.getMessage();
        }
        return result;
    }
    public static void main(String[] args) {
        String testEmail = "example1@email.com"; // Change this to an actual email in your DB
        System.out.println(lookupEmail(testEmail));
    }
    public static String removeEmail(String email) {
        String sqlDelete = "DELETE FROM OPT_OUT_ALL_EMAILS WHERE UPPER(email_address) = UPPER(?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

            pstmt.setString(1, email);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return "Email successfully removed.";
            } else {
                return "Email not found, nothing to remove.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error during database operation: " + e.getMessage();
        }
    }
}