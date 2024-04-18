package org.example.controller;

import org.example.model.Admin;
import org.example.model.Room;
import org.example.view.AdminAuthenticate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminController {
    Admin admin;

    AdminAuthenticate adminAuthenticate;

    Connection connection;
    public AdminController(AdminAuthenticate adminAuthenticate)
    {
        this.adminAuthenticate =adminAuthenticate;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DataBaseConnexion.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"error in data base");
        }
        adminAuthenticate.onLoginButtonClicked(new LogInButtonLitener());
    }

    class LogInButtonLitener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminAuthenticate.getUsername();
            String password = adminAuthenticate.getpassword();
            try {
                Admin test = getAdminFromDataBase();
                if(username.equals(test.getUsername()) && password.equals(test.getPassword()))
                {
                    admin = test;
                    JOptionPane.showMessageDialog(null,"Login Succesful\n welcome back Admin");
                }
                else {
                    JOptionPane.showMessageDialog(null,"wrong username or password");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"could not get admin");
            }
        }
    }
    public Admin getAdminFromDataBase() throws SQLException
    {
        String querry = "SELECT * FROM Admin";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(querry);
        resultSet.next();
        return new Admin(resultSet.getString("name"),resultSet.getString("password"));
    }



    public void createRoom()
    {

    }
}