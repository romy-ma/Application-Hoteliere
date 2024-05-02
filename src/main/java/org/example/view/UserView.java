package org.example.view;

import javax.swing.*;
        import java.awt.event.ActionListener;
import java.sql.Date;

public class UserView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signUpButton;
    private JTextField beginDateField; // Champ de texte pour la date de début
    private JTextField endDateField;   // Champ de texte pour la date de fin
    private JButton reserveButton;      // Bouton pour réserver une chambre

    public UserView() {
        setTitle("User Authentication");
        setSize(300, 250); // Augmenté la taille pour inclure les champs de date
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        panel.add(passwordField);


        logInButton = new JButton("Log In"); // Modification du libellé du bouton "log In" à "Log In"
        panel.add(logInButton);

        signUpButton = new JButton("Sign Up");
        panel.add(signUpButton);


    }

    public String getUsername() {
        return usernameField.getText().replace(" ","");
    }

    public String getPassword() {
        // Retrieve password from password field
        char[] passwordChars = passwordField.getPassword();
        return new String(passwordChars);
    }

    public void onLogInButtonClicked(ActionListener listener) {
        logInButton.addActionListener(listener);
    }

    public void onSignUpButtonClicked(ActionListener listener) {
        signUpButton.addActionListener(listener);
    }



}

