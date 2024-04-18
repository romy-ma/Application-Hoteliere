package org.example.controller;

import org.example.model.User;
import org.example.view.UserView;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserController {
    private UserView userView;
    private User user;

    public UserController(UserView userView)  {
        this.userView = userView;
        userView.onLogInButtonClicked(new LogInButtonListener());
        userView.onSignUpButtonClicked(new SignUpButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername().replace(" ","");
            String password = userView.getPassword();
            if(DataBaseConnexion.usersMap.containsKey(username) && DataBaseConnexion.usersMap.get(username).getPassword().equals(password))
            {
                    user = DataBaseConnexion.usersMap.get(username);
                    JOptionPane.showMessageDialog(null,"Login succecful");
                    return;
            }
            else
            {
                if(!DataBaseConnexion.usersMap.containsKey(username))
                {
                    JOptionPane.showMessageDialog(null,"wrong username,Try again");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"wrong password,Try again");
                }
            }
        }
    }

    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername().replace(" ","");
            String password = userView.getPassword();

            // Perform sign-up logic
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Check if username already exists in database
            if (isUsernameExists(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            // Create a new user object
            User newUser = new User(username, password);
            try {
                DataBaseConnexion.insertUsersIntoDatabase(newUser);
                DataBaseConnexion.updateUsers();
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
                return;
            }catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"error in data base");
            }
        }
    }

    private boolean isUsernameExists(String username) {
        return DataBaseConnexion.usersMap.containsKey(username);
    }

<<<<<<< HEAD
=======


>>>>>>> 83aa695cef892566315f35ea02f2766d7f25c427
}



//
//package org.example.controller;
//
//import org.example.model.User;
//import org.example.view.UserView;
//
//import javax.swing.*;
//        import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class UserController {
//    private UserView userView;
//    private MongoClient mongoClient;
//    private MongoDatabase database;
//    private MongoCollection<Document> collection;
//
//    public UserController(UserView userView) {
//        this.userView = userView;
//
//        // Connect to MongoDB
//        mongoClient = MongoClients.create("mongodb://localhost:27017");
//        database = mongoClient.getDatabase("Hotel_QUARTZ");
//        collection = database.getCollection("users");
//
//        userView.onSignInButtonClicked(new SignInButtonListener());
//        userView.onSignUpButtonClicked(new SignUpButtonListener());
//    }
//
//    class SignInButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Retrieve user input from view
//            String username = userView.getUsername();
//            String password = userView.getPassword();
//            boolean isAdmin = userView.isAdminSelected(); // Get admin status
//
//            // Perform sign-in logic
//            Document query = new Document("username", username).append("password", password);
//            if (isAdmin) {
//                query.append("isAdmin", true); // Check if user is admin
//            }
//            Document userDoc = collection.find(query).first();
//            if (userDoc != null) {
//                JOptionPane.showMessageDialog(null, "Sign in successful!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    class SignUpButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Retrieve user input from view
//            String username = userView.getUsername();
//            String password = userView.getPassword();
//
//            // Perform sign-up logic
//            if (username.isEmpty() || password.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Check if username already exists in database
//            if (isUsernameExists(username)) {
//                JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Create a new user object
//            User newUser = new User(username, password);
//
//            // Save new user to database
//            Document userDoc = newUser.toDocument();
//            collection.insertOne(userDoc);
//
//            JOptionPane.showMessageDialog(null, "User signed up successfully!");
//        }
//    }
//
//    private boolean isUsernameExists(String username) {
//        Document query = new Document("username", username);
//        return collection.countDocuments(query) > 0;
//    }
//}

