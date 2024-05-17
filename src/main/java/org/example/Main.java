package org.example;
import org.example.controller.DataBaseControler;
import org.example.controller.MainAuthenticateController;
import org.example.view.MainPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DataBaseControler.getConnexion();
                DataBaseControler.getAdmin();
                DataBaseControler.getUsers();
                DataBaseControler.getRooms();
                MainAuthenticateController mainAuthenticateController = new MainAuthenticateController(new MainPage());
            }
        });

    }
}
