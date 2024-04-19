package org.example.view;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;


public class UserView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signUpButton;

    public UserView() {
        setTitle("User Authentication");
        setSize(300, 200);
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

        logInButton = new JButton("log In");
        panel.add(logInButton);

        signUpButton = new JButton("Sign Up");
        panel.add(signUpButton);
    }

    public String getUsername() {
        return usernameField.getText();
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

    public Date getBeginDate() {
        return null ;
    }

    public Date getEndDate() {
        return null;
    }
}

//
//import javax.swing.*;
//        import java.awt.event.ActionListener;
//import java.sql.Date;
//
//public class UserView extends JFrame {
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JButton logInButton;
//    private JButton signUpButton;
//    private JTextField beginDateField; // Champ de texte pour la date de début
//    private JTextField endDateField;   // Champ de texte pour la date de fin
//    private JButton reserveButton;      // Bouton pour réserver une chambre
//
//    public UserView() {
//        setTitle("User Authentication");
//        setSize(300, 250); // Augmenté la taille pour inclure les champs de date
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        getContentPane().add(panel);
//
//        JLabel usernameLabel = new JLabel("Username:");
//        panel.add(usernameLabel);
//
//        usernameField = new JTextField(20);
//        panel.add(usernameField);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        panel.add(passwordLabel);
//
//        passwordField = new JPasswordField(20);
//        panel.add(passwordField);
//
//        JLabel beginDateLabel = new JLabel("Begin Date:"); // Ajout d'une étiquette pour la date de début
//        panel.add(beginDateLabel);
//
//        beginDateField = new JTextField(20); // Champ de texte pour la date de début
//        panel.add(beginDateField);
//
//        JLabel endDateLabel = new JLabel("End Date:"); // Ajout d'une étiquette pour la date de fin
//        panel.add(endDateLabel);
//
//        endDateField = new JTextField(20); // Champ de texte pour la date de fin
//        panel.add(endDateField);
//
//        logInButton = new JButton("Log In"); // Modification du libellé du bouton "log In" à "Log In"
//        panel.add(logInButton);
//
//        signUpButton = new JButton("Sign Up");
//        panel.add(signUpButton);
//
//        reserveButton = new JButton("Réserver"); // Ajout du bouton "Réserver"
//        panel.add(reserveButton);
//    }
//
//    public String getUsername() {
//        return usernameField.getText();
//    }
//
//    public String getPassword() {
//        // Retrieve password from password field
//        char[] passwordChars = passwordField.getPassword();
//        return new String(passwordChars);
//    }
//
//    public void onLogInButtonClicked(ActionListener listener) {
//        logInButton.addActionListener(listener);
//    }
//
//    public void onSignUpButtonClicked(ActionListener listener) {
//        signUpButton.addActionListener(listener);
//    }
//
//    public void onReserveButtonClicked(ActionListener listener) {
//        reserveButton.addActionListener(listener);
//    }
//
//    // Méthode pour obtenir la date de début à partir du champ de texte
//    public Date getBeginDate() {
//        // Parse la date à partir du champ de texte beginDateField
//        String beginDateStr = beginDateField.getText();
//        // Implémentez votre logique pour convertir la chaîne de date en objet Date et retournez-la
//        return null; // Pour l'instant, retourne null
//    }
//
//    // Méthode pour obtenir la date de fin à partir du champ de texte
//    public Date getEndDate() {
//        // Parse la date à partir du champ de texte endDateField
//        String endDateStr = endDateField.getText();
//        // Implémentez votre logique pour convertir la chaîne de date en objet Date et retournez-la
//        return null; // Pour l'instant, retourne null
//    }
//}

