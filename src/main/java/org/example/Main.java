package org.example;
import org.example.controller.DataBaseConnexion;
import org.example.view.MainAuthenticate;
import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    DataBaseConnexion.getConnection();
                    DataBaseConnexion.getUsers();
                    DataBaseConnexion.getRooms();
                }catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,"Data Base error");
                }
                MainAuthenticate mainAuthenticate = new MainAuthenticate();
                mainAuthenticate.setVisible(true);
            }
        });

    }
}
