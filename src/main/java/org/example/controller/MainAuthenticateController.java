package org.example.controller;



import org.example.view.AdminAuthenticate;
import org.example.view.MainAuthenticate;
import org.example.view.UserView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAuthenticateController {
    MainAuthenticate mainAuthenticate;
    public MainAuthenticateController(MainAuthenticate mainAuthenticate)
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
            AdminController adminController = new AdminController(adminAuthenticate);
            adminAuthenticate.setVisible(true);
        }
    }
    class ClientButtonBehaviour implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserView userView = new UserView();
            UserController userController = new UserController(userView);
            userView.setVisible(true);
        }
    }

}
