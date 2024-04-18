package org.example.model;

import org.bson.Document;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String password;
    private int reservationNumber;


    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = hashedPassword(password);

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

    // pour hasher le mot de passe
    public static String hashedPassword (String password){
        try {
            // Création d'un objet MessageDigest pour le hachage
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Mettre à jour le message digest avec le mot de passe
            md.update(password.getBytes());

            // Générer le haché du mot de passe
            byte[] digest = md.digest();

            // Convertir le tableau de bytes en une représentation hexadécimale
            BigInteger bigInt = new BigInteger(1, digest);
            String hashedPassword = bigInt.toString(16);

            // Ajouter des zéros non significatifs si nécessaire
            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Gérer l'exception appropriée
            e.printStackTrace();
            return null;
        }
    }
}

