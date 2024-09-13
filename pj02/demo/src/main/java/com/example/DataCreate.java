package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataCreate {
    public static void main(String[] args) {
        String query = "INSERT INTO users (username, email) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password");
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "JohnDoe");
            stmt.setString(2, "johndoe@example.com");
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
