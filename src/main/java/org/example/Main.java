package org.example;
import org.example.controller.DataBaseControler;
import org.example.controller.MainAuthenticateController;
import org.example.model.Building;
import org.example.view.ClientView;
import org.example.view.MainPage;
import org.example.view.ReservationFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                DataBaseControler.getConnexion();
                DataBaseControler.getRservationsFromDataBase();
                DataBaseControler.getAdmin();
                DataBaseControler.getUsers();
                DataBaseControler.getRooms();
                MainAuthenticateController mainAuthenticateController = new MainAuthenticateController(new MainPage());
//


            }

        });





    }

}
