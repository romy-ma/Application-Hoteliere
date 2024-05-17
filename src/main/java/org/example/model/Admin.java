package org.example.model;

import java.util.HashMap;

public class Admin {
    private String username;
    private String password;
    HashMap<String, User> usersMap;
    HashMap<Integer,Reservation> reservationMap;
    HashMap<Integer,Room> roomMap;
    public Admin(String username,String password)
    {
        this.password =password;
        this.username = username;
    }

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
}
