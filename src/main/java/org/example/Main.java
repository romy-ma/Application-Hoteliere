package org.example;
import org.example.controller.DataBaseControler;
import org.example.controller.MainAuthenticateController;
import org.example.view.AdminView;
import org.example.view.CreateRoomView;
import org.example.view.MainPage;

import javax.swing.*;

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

            }

        });





    }

}
