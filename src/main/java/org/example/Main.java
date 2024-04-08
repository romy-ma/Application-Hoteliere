package org.example;
import java.sql.*;

import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.view.AdminAuthenticate;
import org.example.view.MainAuthenticate;
import org.example.view.UserView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                MainAuthenticate mainAuthenticate = new MainAuthenticate();
                mainAuthenticate.setVisible(true);
            }
        });

    }
}
