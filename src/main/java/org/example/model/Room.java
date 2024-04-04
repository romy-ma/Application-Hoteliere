package org.example.model;


public class Room {
    private int roomNumber;
    private String type;
    private boolean isReserved;
    private double price;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isReserved = false; // Au départ, la chambre n'est pas réservée
    }

    // Getters and setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String reservedStatus = isReserved ? "réservée" : "non réservée";
        return "Chambre{" +
                "numéro de chambre=" + roomNumber +
                ", type='" + type + '\'' +
                ", statut de réservation=" + reservedStatus +
                ", prix=" + price +
                '}';
    }
}
