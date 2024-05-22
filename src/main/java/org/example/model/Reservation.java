package org.example.model;

import java.sql.Date;

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
  public Reservation(int roomToReserve,String username){
        this.roomToReserve = roomToReserve;
        this.username = username;
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
}
