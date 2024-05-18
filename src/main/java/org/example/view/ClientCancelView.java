package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientCancelView {
    private JFrame frame;
    private JPanel panel;
    private JButton cancelButton;
    private JLabel reservationLabel;

    public ClientCancelView() {
        frame = new JFrame("Annuler la réservation");
        panel = new JPanel(new GridLayout(2, 1));

        // Ajouter un label pour afficher le numéro de réservation
        reservationLabel = new JLabel("Réservation n° non spécifiée");
        panel.add(reservationLabel);

        // Ajouter le bouton d'annulation
        cancelButton = new JButton("Annuler la réservation");
        panel.add(cancelButton);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setReservationNumber(int reservationNumber) {
        reservationLabel.setText("Réservation n°" + reservationNumber);
    }

    public void onCancelButtonClicked(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}


