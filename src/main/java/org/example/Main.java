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
import org.example.view.UserView;
import org.example.Service.ReservationService;

=======
import org.example.controller.DataBaseConnexion;
import org.example.view.MainAuthenticate;
>>>>>>> 83aa695cef892566315f35ea02f2766d7f25c427
import javax.swing.*;
import java.sql.SQLException;

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
                try {
                    DataBaseConnexion.getConnection();
                    DataBaseConnexion.getUsers();
                    DataBaseConnexion.getRooms();
                }catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,"Data Base error");
                }
                MainAuthenticate mainAuthenticate = new MainAuthenticate();
                mainAuthenticate.setVisible(true);
>>>>>>> 83aa695cef892566315f35ea02f2766d7f25c427
            }
        });

    }
}
