package org.example.controller;

import org.example.model.Admin;
import org.example.model.Building;
import org.example.model.Room;
import org.example.model.Building;
import org.example.view.AdminAuthenticate;
import org.example.view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;

public class AdminController {
    Admin admin;
    AdminAuthenticate adminAuthenticate;
    AdminView adminView;
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
                    adminView = new AdminView(admin);
                    adminView.OnClickCreateRoom(new CreateRoomButton());
                    adminView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "wrong username or password");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "could not get admin");
            }
        }
    }




    class CreateRoomButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer price = adminView.getPrice();
                Building roomType = adminView.getRoomType();
                Room room = new Room(price,roomType);
                DataBaseConnexion.InsertRoomIntoDataBase(room);
                JOptionPane.showMessageDialog(null,"Room inserted succesful");
                return;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"enter a number not special caracters");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error in inserting to dtabase");
            }
        }
    }
}