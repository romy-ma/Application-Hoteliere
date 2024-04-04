package org.example.controller;


import org.example.model.Reservation;

import org.example.view.ClientView;
import org.example.Service.ReservationService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;




public class ClientController {
    private ClientView clientView;
    private ReservationService reservationService;

    public ClientController(ClientView clientView, ReservationService reservationService) {
        this.clientView = clientView;
        this.reservationService = reservationService;

        clientView.onReservationButtonClicked(new ReservationButtonListener());
    }

    class ReservationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Ouvrir une fenêtre de réservation lorsque le bouton est cliqué
            openReservationFrame();
        }
    }

    private void openReservationFrame() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 1);
        // Afficher une boîte de dialogue de confirmation pour la réservation
        int option = JOptionPane.showConfirmDialog(clientView, "Confirmez-vous votre réservation ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Si l'utilisateur confirme, collectez les détails de la réservation
            String userName = clientView.getUserName(); // Supposant que vous récupérez le nom de l'utilisateur de la vue
            // Collectez d'autres détails de la réservation selon vos besoins
            // Créez une instance de réservation
            Reservation reservation = new Reservation(12,13,14,startDate,endDate);
            // Ajoutez la réservation à la base de données
            reservationService.addReservation(userName, reservation); // Ajoutez également le nom de l'utilisateur
            // Affichez un message de confirmation
            JOptionPane.showMessageDialog(clientView, "Votre réservation a été effectuée avec succès !");
        }
    }
}

