package org.example;
<<<<<<< HEAD
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.Service.ReservationService;
import org.example.controller.ClientController;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.view.ClientView;
=======
import java.sql.*;

import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.view.AdminAuthenticate;
import org.example.view.MainAuthenticate;
>>>>>>> 127ca837f6bf60903efe496f50f91304cae0896f
import org.example.view.UserView;
import org.example.Service.ReservationService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
<<<<<<< HEAD
                UserView userView = new UserView();
                UserController controller = new UserController(userView);
                userView.setVisible(true);

//
//                // this is for client not yet
//                SwingUtilities.invokeLater(() -> {
//                    ClientView clientView = new ClientView();
//                    ClientController clientController = new ClientController(clientView,new ReservationService());
//                    clientView.setVisible(true);
//                });



=======

                MainAuthenticate mainAuthenticate = new MainAuthenticate();
                mainAuthenticate.setVisible(true);
>>>>>>> 127ca837f6bf60903efe496f50f91304cae0896f
            }
        });

    }
}
