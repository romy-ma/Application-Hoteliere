package org.example.model;

public class Room {
    private int roomnumber;
    private int roomprice;
    private boolean isreserved;
    private Building roomtype;
    public Room(int roomnumber)
    {
        this.roomnumber = roomnumber;
    }
    public Room(int roomprice,Building roomtype)
    {
        this.roomprice = roomprice;
        this.isreserved = false;
        this.roomtype = roomtype;
    }
    public Room(int roomprice,int roomnumber,boolean isreserved,Building roomtype)
    {
        this.roomtype =roomtype;
        this.isreserved = isreserved;
        this.roomprice = roomprice;
        this.roomnumber =roomnumber;
    }
    public Building getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(Building roomtype) {
        this.roomtype = roomtype;
    }

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

    public boolean isReserved() {
        return isreserved;
    }

    public void setIsreserved(boolean isreserved) {
        this.isreserved = isreserved;
    }
}
