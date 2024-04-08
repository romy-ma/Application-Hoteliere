package org.example.model;

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
    }
}
