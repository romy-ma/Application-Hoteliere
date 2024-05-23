package org.example.controller;

import org.example.model.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.*;

public class DataBaseControler {
    public static void getRooms()
    {
        try
        {
            DataBaseConnexion.getRooms();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"error while getting rooms");
        }
    }
    public static void getUsers()
    {
        try {
            DataBaseConnexion.getUsers();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error data base while getting users");
        }
    }

    public static Admin getAdmin()
    {
        try {
            return DataBaseConnexion.getAdminFromDataBase();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error while getting the admin");
        }
        return null;
    }
    public static void getConnexion()
    {
        try {
            DataBaseConnexion.getConnection();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error While connecting");
        }
    }

    public static void insertUserToDatBase(User user)
    {
        try {
            DataBaseConnexion.insertUsersIntoDatabase(user);
        }
        catch (SQLException sqlep)
        {
            JOptionPane.showMessageDialog(null,"Error While inserting user");
        }
    }

    public static void updateUsers()
    {
        try
        {
            DataBaseConnexion.updateUsers();
        }
        catch(SQLException ep)
        {
            JOptionPane.showMessageDialog(null,"Error While Updating Users");
        }
    }

    public static boolean isUserNameExist(String userName)
    {
       return DataBaseConnexion.usersMap.containsKey(userName);
    }
    public static void insertReservationIntoDataBase(Reservation reservation)
    {
        try
        {
            DataBaseConnexion.insertReservationIntoDatabase(reservation);
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error While Making Reservation");
            e.printStackTrace();
        }
    }
    public static void getRservationsFromDataBase()
    {
        try {
            DataBaseConnexion.getReservationsFromdataBase();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error While Getting Reservations");
            e.printStackTrace();
        } catch (DateException e) {
            JOptionPane.showMessageDialog(null,"Error While Getting Reservations in Date");
            e.printStackTrace();
        }
    }

    public static void CancelReservationFromDataBase(String userName) {
        try {
            DataBaseConnexion.CancelReservationFromDataBase(userName);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error While Canceling Reservation");
            e.printStackTrace();
        }
    }
    public static void updateRooms()
    {
        try {
            DataBaseConnexion.updateRooms();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error While Updating Rooms");
        }
    }
    public static void updateReservations()
    {
        try {
            DataBaseConnexion.updateReservations();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error While Updating Reservations");
        } catch (DateException e) {
            JOptionPane.showMessageDialog(null,"Error While Updating Reservations in Date");
        }
    }
    public static int getReservationByUsername(String username) {
        int reservationNumber = 0;

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection("url_de_connexion", "utilisateur", "mot_de_passe")) {
            // Requête SQL pour récupérer le numéro de réservation de l'utilisateur
            String selectSql = "SELECT reservation_number FROM clients WHERE username = ?";
            String deleteSql = "UPDATE clients SET reservation_number = NULL WHERE username = ?";

            try (PreparedStatement selectStatement = connection.prepareStatement(selectSql);
                 PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                selectStatement.setString(1, username);
                ResultSet resultSet = selectStatement.executeQuery();

                // Récupérer le numéro de réservation s'il existe
                if (resultSet.next()) {
                    reservationNumber = resultSet.getInt("reservation_number");
                    // Supprimer le numéro de réservation de la table clients
                    deleteStatement.setString(1, username);
                    deleteStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationNumber;
    }

}
