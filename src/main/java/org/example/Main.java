package org.example;
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
import org.example.view.UserView;
import org.example.Service.ReservationService;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserView userView = new UserView();
                UserController controller = new UserController(userView);
                userView.setVisible(true);

                SwingUtilities.invokeLater(() -> {
                    ClientView clientView = new ClientView();
                    ClientController clientController = new ClientController(clientView,new ReservationService());
                    clientView.setVisible(true);
                });



            }
        });

    }
}
