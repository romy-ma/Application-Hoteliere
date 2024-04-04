package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton signUpButton;

    public UserView() {
        setTitle("Quartz Hotel");
        setSize(600, 400); // Taille plus grande
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal avec un layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 248, 220)); // Couleur beige clair

        // Ajout du message de bienvenue multilingue
        JLabel welcomeLabel = new JLabel("<html><center>Welcome to Quartz Hotel!<br>Bienvenue à l'hôtel Quartz!<br>¡Bienvenido al Hotel Quartz!</center></html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Police et taille de la police
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // Centre le texte horizontalement
        welcomeLabel.setVerticalAlignment(JLabel.CENTER); // Centre le texte verticalement
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Création du panneau pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Utilise un layout FlowLayout
        buttonPanel.setBackground(new Color(143, 188, 143)); // Couleur vert tropical

        // Ajout des boutons de connexion et d'inscription
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");
        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);

        // Ajout des panneaux au panneau principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panneau principal à la fenêtre
        getContentPane().add(mainPanel);

        // Redimensionne la fenêtre pour s'adapter au contenu
        pack();
    }

    // Méthodes pour récupérer les valeurs des champs de texte
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    // Méthodes pour ajouter des écouteurs aux boutons
    public void onSignInButtonClicked(ActionListener listener) {
        signInButton.addActionListener(listener);
    }

    public void onSignUpButtonClicked(ActionListener listener) {
        signUpButton.addActionListener(listener);
    }
}


