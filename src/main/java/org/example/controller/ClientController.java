package org.example.controller;

import org.example.controller.DataBaseConnexion;
import org.example.model.Reservation;
import org.example.model.Room;
import org.example.model.User;
import org.example.view.ClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class ClientController {
    private ClientView clientView;
    private User user;

    public ClientController(ClientView clientView, User user) {
        this.clientView = clientView;
        this.user = user;

        clientView.onReserveButtonClicked(new ReserveButtonListener());
    }

    class ReserveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Trouver la première chambre disponible
            Room availableRoom = findAvailableRoom();
           System.out.println("rooms shown");
            if (availableRoom != null) {
                // Récupérer les dates de début et de fin de la réservation à partir de la vue
                Date beginDate =(Date) clientView.getBeginDate();
                Date endDate = (Date) clientView.getEndDate();

                // Créer une nouvelle réservation
                Reservation newReservation = new Reservation(availableRoom.getRoomnumber(),beginDate, endDate,user.getUsername());
                try {
                    // Insérer la réservation dans la base de données
                    DataBaseConnexion.insertReservationIntoDatabase(newReservation);

                    // Modifier l'état de réservation de l'utilisateur
                    user.setReservationStatus(true);

                    // Informer l'utilisateur que la réservation a été effectuée avec succès
                    JOptionPane.showMessageDialog(null, "Reservation successful!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error occurred while making reservation.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No available rooms found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        // Méthode pour trouver la première chambre disponible
        private Room findAvailableRoom() {
            for (Room room : DataBaseConnexion.roomsMap.values()) {
                if (!room.isReserved()) {
                    return room;
                }
            }
            return null; // Retourner null s'il n'y a pas de chambre disponible
        }
    }
}
