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
        AdminButtonClick(new AdminButton());
        panel.add(adminButton);

        clientButton = new JButton("ClientButton");
        ClientButtonClick(new ClientButton());
        panel.add(clientButton);
    }
    public void AdminButtonClick(ActionListener listener){adminButton.addActionListener(listener);}
    public void ClientButtonClick(ActionListener listener){clientButton.addActionListener(listener);}
    class ClientButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserView userView = new UserView();
            UserController userController = new UserController(userView);
            userView.setVisible(true);
        }
    }
    class AdminButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminAuthenticate adminAuthenticate = new AdminAuthenticate();
            AdminController adminController = new AdminController(adminAuthenticate);
            adminAuthenticate.setVisible(true);
        }
    }
}
