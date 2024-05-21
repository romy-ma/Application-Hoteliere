package org.example;
import org.example.controller.DataBaseControler;
import org.example.controller.MainAuthenticateController;
import org.example.model.DataBaseConnexion;
import org.example.model.Room;
import org.example.view.AdminView;
import org.example.view.MainPage;
import org.example.view.RoomPanel;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DataBaseControler.getConnexion();
                DataBaseControler.getAdmin();
                DataBaseControler.getUsers();
                DataBaseControler.getRooms();
                MainAuthenticateController mainAuthenticateController = new MainAuthenticateController(new MainPage());

//                JPanel container = new JPanel();
//                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
//                container.setSize(1000,1000);
//
//                for(Map.Entry<Integer, Room> entry : DataBaseConnexion.roomsMap.entrySet()) {
//                    RoomPanel roomPanel = new RoomPanel();
//                    roomPanel.addRoomNumber(entry.getValue().getRoomnumber());
//                    System.out.println(entry.getValue().getRoomnumber());
//                    roomPanel.addRoomType(entry.getValue().getRoomtype());
//                    roomPanel.addRoomPrice(entry.getValue().getRoomprice());
//                    container.add(roomPanel);
//                }
//                AdminView adminView = new AdminView();
//                adminView.reservationsPanel.setViewportView(container);
//                adminView.setVisible(true);
            }
        });

    }
}
