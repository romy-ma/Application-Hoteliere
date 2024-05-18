package org.example.controller;



import org.example.model.User;
import org.example.view.ClientCancelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ClientCancelController {
    private ClientCancelView view;
    private User user;
    private int reservationNumber;

    public ClientCancelController(ClientCancelView view, User user) {
        this.view = view;
        this.user = user;

        // recuperer num client
        reservationNumber = DataBaseControler.getReservationByUsername(user.getUsername());

        // maj la vue avec le numéro de réservation
        view.setReservationNumber(reservationNumber);

        // Associer le listener du bouton d'annulation
        view.onCancelButtonClicked(new CancelListener());
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Annuler la réservation
            if (cancelReservation()) {
                view.showMessage("La réservation a été annulée avec succès !");
            } else {
                view.showMessage("Erreur lors de l'annulation de la réservation.");
            }
        }
    }

    private boolean cancelReservation() {
        // Supprimer men la table reservations
        String deleteReservationQuery = "DELETE FROM reservations WHERE reservation_number = ?";

        try (Connection connection = DriverManager.getConnection("url_de_connexion", "utilisateur", "mot_de_passe");
             PreparedStatement statement = connection.prepareStatement(deleteReservationQuery)) {
            statement.setInt(1, reservationNumber);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                // Supprimer men tab clients numero
                String updateClientQuery = "UPDATE clients SET reservation_number = NULL WHERE username = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateClientQuery)) {
                    updateStatement.setString(1, user.getUsername());
                    updateStatement.executeUpdate();
                }
                user.setReservationNumber(0);
                user.setReservationStatus(false);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

