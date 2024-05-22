package org.example.controller;


import org.example.model.DataBaseConnexion;

import org.example.model.User;
import org.example.view.*;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
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
            try
            {
                String username = loginUser.getUsername();
                String password = DataBaseConnexion.hashString(loginUser.getPassword());
                if(DataBaseConnexion.usersMap.containsKey(username) && DataBaseConnexion.usersMap.get(username).getPassword().equals(password))
                {
                    user = DataBaseConnexion.usersMap.get(username);

                    if(DataBaseConnexion.hashString(user.getPassword()).equals(DataBaseConnexion.hashString(password)))
                    {
                        JOptionPane.showMessageDialog(null,"Login succecful");
                    }

                    new ClientController(new ClientView(),user);
                    loginUser.dispose();
                    if(signeUpUser !=null)
                    {
                        signeUpUser.dispose();
                    }
                }
                else
                {
                    if(!DataBaseConnexion.usersMap.containsKey(username))
                    {
                        JOptionPane.showMessageDialog(null,"wrong username,Try again","Wrong Username",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"wrong password,Try again","Wrong Password",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch (NoSuchAlgorithmException ep)
            {
                ep.printStackTrace();
                JOptionPane.showMessageDialog(null,"Failed To Hash Password","Hash Fail",JOptionPane.ERROR_MESSAGE);
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


            try {
                String username = signeUpUser.getUsername();
                String password = DataBaseConnexion.hashString(signeUpUser.getPassword());
                String email = signeUpUser.getEmail();
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!User.isValidEmail(email))
                {
                    JOptionPane.showMessageDialog(null,"Wrong Email Typo ","Email Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (DataBaseControler.isUserNameExist(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User newUser = new User(username, password,email);
                DataBaseControler.insertUserToDatBase(newUser);
                DataBaseControler.updateUsers();
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
                loginUser.setVisible(true);
                signeUpUser.dispose();
            } catch (NoSuchAlgorithmException ex) {
               JOptionPane.showMessageDialog(null,"Error While Hashing","Hashaging Error",JOptionPane.ERROR_MESSAGE);
            }


        }
    }



}

