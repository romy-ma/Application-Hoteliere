package org.example.model;

<<<<<<< HEAD

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int roomId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(int reservationId, int roomId, int userId, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Réservation{" +
                "identifiant=" + reservationId +
                ", numéro de chambre=" + roomId +
                ", identifiant utilisateur=" + userId +
                ", date de début=" + startDate +
                ", date de fin=" + endDate +
                '}';
=======
public class Reservation {
    private int reservationNumber;
    private Room roomToReserve;
    private String clientUsername;
    private HotelDate beginDate;
    private HotelDate endDate;

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Room getRoomToReserve() {
        return roomToReserve;
    }

    public void setRoomToReserve(Room roomToReserve) {
        this.roomToReserve = roomToReserve;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
>>>>>>> 83aa695cef892566315f35ea02f2766d7f25c427
    }
}
