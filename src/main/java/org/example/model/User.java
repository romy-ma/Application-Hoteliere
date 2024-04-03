package org.example.model;

import org.bson.Document;

public class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    // Convert User object to MongoDB Document
    public Document toDocument() {
        return new Document("username", username)
                .append("password", password);
    }

    // Convert MongoDB Document to User object
    public static User fromDocument(Document doc) {
        return new User(doc.getString("username"), doc.getString("password"));
    }
}
