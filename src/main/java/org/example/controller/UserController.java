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
    private User user;

    public UserController(UserView userView)  {
        this.userView = userView;
        userView.onLogInButtonClicked(new LogInButtonListener());
        userView.onSignUpButtonClicked(new SignUpButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername().replace(" ","");
            String password = userView.getPassword();
            if(DataBaseConnexion.usersMap.containsKey(username) && DataBaseConnexion.usersMap.get(username).getPassword().equals(password))
            {
                    user = DataBaseConnexion.usersMap.get(username);
                    JOptionPane.showMessageDialog(null,"Login succecful");
                    return;
            }
            else
            {
                if(!DataBaseConnexion.usersMap.containsKey(username))
                {
                    JOptionPane.showMessageDialog(null,"wrong username,Try again");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"wrong password,Try again");
                }
            }
        }
    }

    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername().replace(" ","");
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
                DataBaseConnexion.insertUsersIntoDatabase(newUser);
                DataBaseConnexion.updateUsers();
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
                return;
            }catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"error in data base");
            }
        }
    }

    private boolean isUsernameExists(String username) {
        return DataBaseConnexion.usersMap.containsKey(username);
    }



}
