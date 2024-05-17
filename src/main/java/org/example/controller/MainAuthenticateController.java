package org.example.controller;



import org.example.view.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAuthenticateController {
    MainPage mainAuthenticate;
    public MainAuthenticateController(MainPage mainAuthenticate)
    {
        this.mainAuthenticate = mainAuthenticate;
        mainAuthenticate.AdminButtonAddActionListener(new AdminButtonBehaviour());
        mainAuthenticate.ClientButtonActionListener(new ClientButtonBehaviour());
        mainAuthenticate.setVisible(true);
    }



    class AdminButtonBehaviour implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminAuthenticate adminAuthenticate = new AdminAuthenticate();
            AdminController adminController = new AdminController(adminAuthenticate,mainAuthenticate);
            mainAuthenticate.dispose();
            adminAuthenticate.setVisible(true);
        }
    }
    class ClientButtonBehaviour implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginUser loginUser = new LoginUser();
            UserController userController = new UserController(loginUser,mainAuthenticate);
            mainAuthenticate.dispose();
            loginUser.setVisible(true);
        }
    }

}
