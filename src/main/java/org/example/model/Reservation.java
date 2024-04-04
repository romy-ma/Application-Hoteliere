package org.example.model;


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
    }
}
