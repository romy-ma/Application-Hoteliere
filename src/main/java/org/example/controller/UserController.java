package org.example.controller;

import org.example.model.User;
import org.example.view.UserView;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private UserView userView;
    private User user = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;


    public UserController(UserView userView) {
        this.userView = userView;
        String url = "jdbc:postgresql://pg-1b316499-grabachakib008-e4aa.a.aivencloud.com:10488/defaultdb";
        String username_data = "avnadmin";
        String password_database = null;
        final int Password_Line =4;
        String File_path = "src\\main\\java\\org\\example\\Data_Base_Info.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(File_path));
            int currentline = 0;
            while ((password_database = bufferedReader.readLine()) != null )
            {
                if(Password_Line == currentline)
                {
                    break;
                }
                currentline++;
            }

        }catch( IOException exp)
        {
            JOptionPane.showMessageDialog(null,"problem with file");
        }

        // Connect to MongoDB
        try
        {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, username_data, password_database);

        }
        catch (ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,"could not connect to database");
        }

        userView.onLogInButtonClicked(new LogInButtonListener());
        userView.onSignUpButtonClicked(new SignUpButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername();
            String password = userView.getPassword();

            // Perform sign-in logic
            try {
                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM Clients");
                boolean found = false;
                while (resultSet1.next() && !found) {
                    if (username.equals(resultSet1.getString("username")) && password.equals(resultSet1.getString("password"))) {
                        JOptionPane.showMessageDialog(null, "login succeful");
                        user = new User(username,password);
                        found = true;
                    }

                }
                if(!found)
                {
                    JOptionPane.showMessageDialog(null,"User Not Found");
                }
            }
            catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"eroor data base");
            }

        }
    }

    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername();
            String password = userView.getPassword();

            // Perform sign-up logic
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if username already exists in database
            if (isUsernameExists(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create a new user object
            User newUser = new User(username, password);
            try {
                String insert_querry = "INSERT INTO Clients(username,password) VALUES(?,?)";
                PreparedStatement statement1 = connection.prepareStatement(insert_querry);
                statement1.setString(1,username);
                statement1.setString(2,password);
                int rows = statement1.executeUpdate();
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
            }catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"error in data base");
            }

            // Save new user to database


        }
    }

    private boolean isUsernameExists(String username) {
        boolean found =false;
        try{
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM Clients");

            while (resultSet1.next() && !found)
            {
                if(username.equals(resultSet1.getString("username")))
                {
                    found = true;
                }
            }

        }
        catch(SQLException exp)
        {
            JOptionPane.showMessageDialog(null,"error in data base");
        }
        return found;
    }


}
