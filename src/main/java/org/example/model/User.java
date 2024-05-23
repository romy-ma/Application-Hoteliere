package org.example.model;

import org.bson.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String username;
    private String password;
    private int reservationNumber;
    private boolean reservationStatus;
    private String email;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String username, String password,String email,int reservationNumber,boolean reservationStatus) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.reservationNumber = reservationNumber;
        this.reservationStatus = reservationStatus;
    }



    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public boolean isReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(boolean reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
