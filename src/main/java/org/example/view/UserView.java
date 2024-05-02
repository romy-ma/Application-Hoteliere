package org.example.view;
import javax.swing.*;
import java.awt.event.ActionListener;



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
