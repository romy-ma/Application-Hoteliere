package org.example.model;

public class Room {
    private int roomnumber;
    private int roomprice;
    private boolean isreserved;

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public int getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(int roomprice) {
        this.roomprice = roomprice;
    }

    public boolean isIsreserved() {
        return isreserved;
    }

    public void setIsreserved(boolean isreserved) {
        this.isreserved = isreserved;
    }
}
