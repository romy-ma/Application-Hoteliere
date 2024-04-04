package org.example.Service;


import org.example.model.Reservation;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;




import java.util.HashMap;
import java.util.Map;

public class ReservationService {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private Map<Integer, Integer> userReservationMap; // Stocke l'ID de l'utilisateur et l'ID de sa réservation

    public ReservationService() {

        // Connexion à MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("Hotel_QUARTZ");
        collection = database.getCollection("reservations");
        
        
        this.userReservationMap = new HashMap<>();
    }

    public boolean hasReservation(int userId) {
        return userReservationMap.containsKey(userId);
    }

    // Méthode pour enregistrer une réservation
    public void makeReservation(int userId, int reservationId) {
        userReservationMap.put(userId, reservationId);
    }

    // Méthode pour modifier une réservation existante
    public void modifyReservation(int userId, int newReservationId) {
        if (userReservationMap.containsKey(userId)) {
            userReservationMap.put(userId, newReservationId);
        } else {
            System.out.println("Aucune réservation trouvée pour l'utilisateur " + userId);
        }
    }

    // Méthode pour annuler une réservation
    public void cancelReservation(int userId) {
        if (userReservationMap.containsKey(userId)) {
            userReservationMap.remove(userId);
        } else {
            System.out.println("Aucune réservation trouvée pour l'utilisateur " + userId);
        }
    }

    // Ajouter une réservation à la base de données
    public void addReservation(String userName, Reservation reservation) {
        Document reservationDoc = new Document();
        reservationDoc.append("userName", userName)
                .append("roomId", reservation.getRoomId())
                .append("startDate", reservation.getStartDate().toString())
                .append("endDate", reservation.getEndDate().toString());
        collection.insertOne(reservationDoc);
    }


}
