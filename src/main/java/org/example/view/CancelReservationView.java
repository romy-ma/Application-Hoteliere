package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CancelReservationView extends JFrame {
    private JTextField reservationNumberField;
    private JButton cancelButton;

    public CancelReservationView(ActionListener listener) {
        setTitle("Cancel Reservation");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        reservationNumberField = new JTextField();
        panel.add(new JLabel("Reservation Number:"));
        panel.add(reservationNumberField);

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.addActionListener(listener); // Ajout de l'ActionListener
        panel.add(new JLabel()); // Placeholder for spacing
        panel.add(cancelButton);

        getContentPane().add(panel);
    }

    // Méthode pour obtenir le numéro de réservation entré par l'utilisateur
    public String getReservationNumber() {
        return reservationNumberField.getText();
    }
}
