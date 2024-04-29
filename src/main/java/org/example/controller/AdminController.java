package org.example.controller;

import org.example.model.Admin;
import org.example.model.Building;
import org.example.model.Room;
import org.example.view.AdminAuthenticate;
import org.example.view.CreateRoomView;
import org.example.view.RoomModifiersChoosers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminController {
    Admin admin;
    AdminAuthenticate adminAuthenticate;
    RoomModifiersChoosers adminView;
    public AdminController(AdminAuthenticate adminAuthenticate) {
        this.adminAuthenticate = adminAuthenticate;
        adminAuthenticate.onLoginButtonClicked(new LogInButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminAuthenticate.getUsername().replace(" ","");
            String password = adminAuthenticate.getpassword();
            try {
                Admin test = DataBaseConnexion.getAdminFromDataBase();
                if (username.equals(test.getUsername()) && password.equals(test.getPassword())) {
                    admin = test;
                    JOptionPane.showMessageDialog(null, "Login Succesful\n welcome back Admin");
                    adminView = new RoomModifiersChoosers();

                    adminView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "wrong username or password");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "could not get admin");
            }
        }
    }





}