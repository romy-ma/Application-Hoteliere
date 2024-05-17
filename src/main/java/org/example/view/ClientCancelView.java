package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientCancelView {
    private JFrame frame;
    private JPanel panel;

    public ClientCancelView(List<Integer> reservationNumbers, ActionListener cancelListener) {
        frame = new JFrame("Liste des réservations");
        panel = new JPanel(new GridLayout(reservationNumbers.size(), 2));

        for (Integer reservationNumber : reservationNumbers) {
            JLabel reservationLabel = new JLabel("Réservation n°" + reservationNumber);
            JButton cancelButton = new JButton("Annuler la réservation");
            cancelButton.addActionListener(cancelListener);
            cancelButton.setActionCommand(String.valueOf(reservationNumber)); // Utilisez le numéro de réservation comme actionCommand pour identifier quelle réservation annuler
            panel.add(reservationLabel);
            panel.add(cancelButton);
        }

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
