package org.example.controller;


import org.example.model.DataBaseConnexion;

import org.example.model.User;
import org.example.view.*;
import org.example.view.LoginUser;

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserController {
    private LoginUser loginUser;
    private SigneUpUser signeUpUser;
    MainPage mainPage;
    private User user;

    public UserController(LoginUser loginUser, MainPage mainPage)  {
        this.loginUser = loginUser;
        loginUser.onLogInButtonClicked(new LogInButtonListener());
        loginUser.onSignUpButtonClicked(new SigneUpToPageButton());
        this.mainPage = mainPage;
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = loginUser.getUsername().replace(" ","");
            String password = loginUser.getPassword();
            if(DataBaseConnexion.usersMap.containsKey(username) && DataBaseConnexion.usersMap.get(username).getPassword().equals(password))
            {
                    user = DataBaseConnexion.usersMap.get(username);
                    JOptionPane.showMessageDialog(null,"Login succecful");
                    new ClientController(new ClientView(),user);
                    loginUser.dispose();
                    if(signeUpUser !=null)
                    {
                        signeUpUser.dispose();
                    }
                    mainPage.dispose();
                    
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
    class SigneUpToPageButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            signeUpUser = new SigneUpUser();
            signeUpUser.onSignUpButtonClicked(new SignUpButtonListener());
            signeUpUser.setVisible(true);
            loginUser.setVisible(false);
        }
    }
    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = signeUpUser.getUsername();
            String password = signeUpUser.getPassword();

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
            loginUser.setVisible(true);
            signeUpUser.dispose();
        }
    }



}

