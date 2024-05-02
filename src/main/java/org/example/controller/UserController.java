package org.example.controller;


import org.example.model.DataBaseConnexion;

import org.example.model.User;
import org.example.view.ClientView;
import org.example.view.UserView;

import javax.swing.*;
import java.sql.*;
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
                    new ClientController(new ClientView(),user);

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

            String username = userView.getUsername();
            String password = userView.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (DataBaseControler.isUserNameExist(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            User newUser = new User(username, password);
            DataBaseControler.insertUserToDatBase(newUser);
            DataBaseControler.updateUsers();
            JOptionPane.showMessageDialog(null, "User signed up successfully!");
        }
        private boolean isUsernameExists(String username) {
            return DataBaseConnexion.usersMap.containsKey(username);
        }
    }

}

