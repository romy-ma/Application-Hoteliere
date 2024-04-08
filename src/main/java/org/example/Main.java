package org.example;
import java.sql.*;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.view.UserView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserView userView = new UserView();
                UserController controller = new UserController(userView);
                userView.setVisible(true);
            }
        });

    }
}
