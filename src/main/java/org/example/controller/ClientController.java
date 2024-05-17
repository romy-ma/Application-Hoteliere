package org.example.controller;


import org.example.model.DataBaseConnexion;
import org.example.model.Reservation;
import org.example.model.Room;
import org.example.model.User;
import org.example.view.CancelReservationView;
import org.example.view.ClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.model.HotelDate;

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

            if (availableRoom != null) {
                // Récupérer les dates de début et de fin de la réservation à partir de la vue
                HotelDate beginDate =(HotelDate) clientView.getBeginDate();
                HotelDate endDate = (HotelDate) clientView.getEndDate();

                // Créer une nouvelle réservation
                Reservation newReservation = new Reservation(availableRoom.getRoomnumber(),beginDate, endDate,user.getUsername());



                // Insérer la réservation dans la base de données
                DataBaseControler.insertReservationIntoDataBase(newReservation);

                // Modifier l'état de réservation de l'utilisateur
                user.setReservationStatus(true);

                // Informer l'utilisateur que la réservation a été effectuée avec succès
                JOptionPane.showMessageDialog(null, "Reservation successful!");
 
                   // mettre dans resevationnumber le integer qui a ete fait
//                    user.setReservationNumber(newReservation.getReservationNumber());

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

