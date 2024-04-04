package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JTextField userNameField;
    private JButton reservationButton;

    public ClientView() {
        setTitle("Quartz Hotel - Réservation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal avec un layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Création du champ de texte pour le nom d'utilisateur
        userNameField = new JTextField(20);
        JPanel userNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userNamePanel.add(new JLabel("Nom d'utilisateur :"));
        userNamePanel.add(userNameField);

        // Ajout du panneau du champ de texte au panneau principal
        mainPanel.add(userNamePanel, BorderLayout.NORTH);

        // Création du bouton de réservation
        reservationButton = new JButton("Réserver une chambre");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(reservationButton);

        // Ajout du panneau du bouton au panneau principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Ajout du panneau principal à la fenêtre
        getContentPane().add(mainPanel);
    }

    // Méthode pour récupérer le nom d'utilisateur
    public String getUserName() {
        return userNameField.getText();
    }

    // Méthode pour ajouter un écouteur de clic au bouton de réservation
    public void onReservationButtonClicked(ActionListener listener) {
        reservationButton.addActionListener(listener);
    }
}


