package org.example;

import java.sql.*;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.view.UserView;



public class Main {
    public static void main(String[] args) {
        // Database URL, username, and password
        String url = "jdbc:postgresql://pg-1b316499-grabachakib008-e4aa.a.aivencloud.com:10488/defaultdb";
        String user = "avnadmin";
        String password = "Put Data Base Password here";

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Clients");

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");

                // Do something with the data
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
