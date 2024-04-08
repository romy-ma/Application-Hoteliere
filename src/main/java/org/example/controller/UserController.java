package org.example.controller;

import org.example.model.User;
import org.example.view.UserView;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserController {
    private UserView userView;
    private User user = null;
    private HashMap<String,User> mapUsers;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public UserController(UserView userView)  {
        this.userView = userView;
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DataBaseConnexion.getConnection();
            mapUsers = getUsersFromDataBase();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
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
            if(mapUsers.containsKey(username) && mapUsers.get(username).getPassword().equals(password))
            {
                    user = mapUsers.get(username);
                    JOptionPane.showMessageDialog(null,"Login succecful");
            }
            else
            {
                    JOptionPane.showMessageDialog(null,"user not found wrong username or password");
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
                insertUsersIntoDatabase(newUser);
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
                return;
            }catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"error in data base");
            }




        }
    }

    private boolean isUsernameExists(String username) {
           if(mapUsers.containsKey(username))
           {
               return true;
           }

        return false;
    }
    public HashMap<String,User> getUsersFromDataBase() throws SQLException {
        String query = "SELECT * FROM Clients";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        HashMap<String,User> map = new HashMap<>();
        User user = null;
        while(resultSet.next())
        {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            user = new User(username,password);
            map.put(user.getUsername(),user);
        }
        return map;


    }
    public void insertUsersIntoDatabase(User user) throws SQLException {
        String query = "INSERT INTO Clients (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();


    }

}
