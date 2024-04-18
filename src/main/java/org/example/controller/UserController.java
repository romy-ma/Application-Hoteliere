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
    private User user = null;
    private HashMap<String,User> mapUsers;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public UserController(UserView userView)  {
        this.userView = userView;
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DataBaseConnexion.getConnection();
            mapUsers = getUsersFromDataBase();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        userView.onLogInButtonClicked(new LogInButtonListener());
        userView.onSignUpButtonClicked(new SignUpButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername();
            String password = userView.getPassword();
            if(mapUsers.containsKey(username) && mapUsers.get(username).getPassword().equals(password))
            {
                    user = mapUsers.get(username);
                    JOptionPane.showMessageDialog(null,"Login succecful");
            }
            else
            {
                    JOptionPane.showMessageDialog(null,"user not found wrong username or password");
            }
        }
    }

    class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve user input from view
            String username = userView.getUsername();
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
                insertUsersIntoDatabase(newUser);
                JOptionPane.showMessageDialog(null, "User signed up successfully!");
                return;
            }catch (SQLException exp)
            {
                JOptionPane.showMessageDialog(null,"error in data base");
            }




        }
    }

    private boolean isUsernameExists(String username) {
           if(mapUsers.containsKey(username))
           {
               return true;
           }

        return false;
    }
    public HashMap<String,User> getUsersFromDataBase() throws SQLException {
        String query = "SELECT * FROM Clients";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        HashMap<String,User> map = new HashMap<>();
        User user = null;
        while(resultSet.next())
        {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            user = new User(username,password);
            map.put(user.getUsername(),user);
        }
        return map;


    }
    public void insertUsersIntoDatabase(User user) throws SQLException {
        String query = "INSERT INTO Clients (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();


    }

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

