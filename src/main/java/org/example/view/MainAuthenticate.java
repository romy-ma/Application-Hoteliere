package org.example.view;

import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.model.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainAuthenticate extends JFrame {
    JButton adminButton;
    JButton clientButton;
    public MainAuthenticate()
    {
        setTitle("choose Type To enter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        adminButton = new JButton("adminButton");
        panel.add(adminButton);

        clientButton = new JButton("ClientButton");
        panel.add(clientButton);
    }
    public void AdminButtonAddActionListener(ActionListener listener){adminButton.addActionListener(listener);}
    public void ClientButtonActionListener(ActionListener listener){clientButton.addActionListener(listener);}


}
