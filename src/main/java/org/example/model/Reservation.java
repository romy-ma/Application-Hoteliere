package org.example.model;

import java.sql.Date;

public class Reservation {
    private int reservationNumber;
    private int roomToReserve;
    private Date beginDate;
    private Date endDate;

    // Constructeur avec tous les paramètres
    public Reservation(int reservationNumber, int roomToReserve, Date beginDate, Date endDate) {
        this.reservationNumber = reservationNumber;
        this.roomToReserve = roomToReserve;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Reservation(int roomToReserve, Date beginDate, Date endDate) {
        this.roomToReserve = roomToReserve;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
