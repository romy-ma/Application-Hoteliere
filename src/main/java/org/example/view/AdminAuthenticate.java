package org.example.view;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Locale;

public class AdminAuthenticate extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public AdminAuthenticate()
    {
        setTitle("Admin Authenticate");
        setSize(300,200);
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

        loginButton =new JButton("logIn");
        panel.add(loginButton);
    }
    public String getUsername(){return usernameField.getText();}
    public String getpassword(){
        char[] passwordchars = passwordField.getPassword();
        return new String(passwordchars);
    }
    public void onLoginButtonClicked(ActionListener listener){loginButton.addActionListener(listener);}
}
