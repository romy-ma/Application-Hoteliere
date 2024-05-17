package org.example.model;

//import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Reservation {
    private int reservationNumber;
    private int roomToReserve;
    private HotelDate beginDate;
    private HotelDate endDate;
    private String username ;

    // Constructeur avec tous les paramètres
    public Reservation(int reservationNumber, int roomToReserve, HotelDate beginDate, HotelDate endDate,String username) {
        this.reservationNumber = reservationNumber;
        this.roomToReserve = roomToReserve;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.username =username;
    }
    public Reservation(int roomToReserve,HotelDate beginDate,HotelDate endDate,String username)
    {
        this.roomToReserve = roomToReserve;
        this.username = username;
        this.beginDate =beginDate;
        this.endDate = endDate;
    }
    public Reservation(int roomToReserve, HotelDate beginDate, HotelDate endDate) {
        this.roomToReserve = roomToReserve;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getRoomToReserve() {
        return roomToReserve;
    }

    public void setRoomToReserve(int roomToReserve) {
        this.roomToReserve = roomToReserve;
    }

    public HotelDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(HotelDate beginDate) {
        this.beginDate = beginDate;
    }

    public HotelDate getEndDate() {
        return endDate;
    }

    public void setEndDate(HotelDate endDate) {
        this.endDate = endDate;
    }
    
     public static List<Integer> getReservationsByUsername(String username) {
        List<Integer> reservationNumbers = new ArrayList<>();

        // Connexion à la base de données
        try (Connection connection = DriverManager.getConnection("url_de_connexion", "utilisateur", "mot_de_passe")) {
            // Requête SQL pour récupérer les réservations d'un utilisateur
            String sql = "SELECT reservation_number FROM clients WHERE username = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                
                // Ajouter tous les numéros de réservation à la liste
                while (resultSet.next()) {
                    reservationNumbers.add(resultSet.getInt("reservation_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationNumbers;
    }
}
