package org.example.controller;

import org.example.model.Reservation;
import org.example.model.Room;
import org.example.model.User;
import org.example.view.UserView;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserController {
    private UserView userView;
    private User user;

    public UserController(UserView userView)  {
        this.userView = userView;
        userView.onLogInButtonClicked(new LogInButtonListener());
        userView.onSignUpButtonClicked(new SignUpButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername().replace(" ","");
            String password = userView.getPassword();
            if(DataBaseConnexion.usersMap.containsKey(username) && DataBaseConnexion.usersMap.get(username).getPassword().equals(password))
            {
                    user = DataBaseConnexion.usersMap.get(username);
                    JOptionPane.showMessageDialog(null,"Login succecful");
                    return;
            }
            else
            {
                if(!DataBaseConnexion.usersMap.containsKey(username))
                {
                    JOptionPane.showMessageDialog(null,"wrong username,Try again");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"wrong password,Try again");
                }
            }
        }
    }

    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername().replace(" ","");
            String password = userView.getPassword();

            // Perform sign-up logic
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Check if username already exists in database
            if (isUsernameExists(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            // Create a new user object
            User newUser = new User(username, password);
            try {
                DataBaseConnexion.insertUsersIntoDatabase(newUser);
                DataBaseConnexion.updateUsers();
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
                return;
            }catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"error in data base");
            }
        }
        private boolean isUsernameExists(String username) {
            return DataBaseConnexion.usersMap.containsKey(username);
        }
    }
    class ReserveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Trouver la première chambre disponible
            Room availableRoom = findAvailableRoom();

            if (availableRoom != null) {
                // Récupérer les dates de début et de fin de la réservation à partir de la vue
                Date beginDate = userView.getBeginDate();
                Date endDate = userView.getEndDate();

                // Créer une nouvelle réservation
                Reservation newReservation = new Reservation(availableRoom.getRoomnumber(),beginDate, endDate);

                try {
                    // Insérer la réservation dans la base de données
                    DataBaseConnexion.insertReservationIntoDatabase(newReservation);

                    // Modifier l'état de réservation de l'utilisateur
                    User currentUser = getCurrentUser();
                    currentUser.setReservationStatus(true);

                    // Informer l'utilisateur que la réservation a été effectuée avec succès
                    JOptionPane.showMessageDialog(null, "Reservation successful!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error occurred while making reservation.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No available rooms found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Méthode pour trouver la première chambre disponible
        private Room findAvailableRoom() {
            for (Room room : DataBaseConnexion.roomsMap.values()) {
                if (!room.isIsreserved()) {
                    return room;
                }
            }
            return null; // Retourner null s'il n'y a pas de chambre disponible
        }


    }

    // récupérer l'utilisateur actuellement connecté session mommentanee
    public User getCurrentUser() {
        return user;
    }

}

