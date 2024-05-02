package org.example.model;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class DataBaseConnexion {
    private static String url = "jdbc:postgresql://pg-1b316499-grabachakib008-e4aa.a.aivencloud.com:10488/defaultdb";
    private static String username_data = "avnadmin";
    private static String password_database = null;
    private static final int Password_Line =4;
    public static HashMap<String, User> usersMap =new HashMap<>();
    public static HashMap<Integer, Room> roomsMap = new HashMap<>();
    public static HashMap<Integer, Reservation> reservationMap;
    public static Connection connection;
    private static String File_path = "src\\main\\java\\org\\example\\Data_Base_Info.txt";
       public static void getConnection() throws SQLException
       {
           try {
               BufferedReader bufferedReader = new BufferedReader(new FileReader(File_path));
               int currentline = 0;
               while ((password_database = bufferedReader.readLine()) != null )
               {
                   if(Password_Line == currentline)
                   {
                       break;
                   }
                   currentline++;
               }

           }catch( IOException exp)
           {
               JOptionPane.showMessageDialog(null,"problem with file");
           }
           connection = DriverManager.getConnection(url,username_data,password_database);
       }
       public static void getUsers() throws SQLException {
           String query = "SELECT * FROM Clients";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           User user = null;
           while(resultSet.next())
           {
               String username = resultSet.getString("username");
               String password = resultSet.getString("password");
               user = new User(username,password);
               usersMap.put(user.getUsername(),user);
           }
       }
       public static void getRooms() throws SQLException
       {
           String query = "SELECT * FROM Rooms";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           Room room = null;
           while(resultSet.next())
           {
               int roomNumber = resultSet.getInt("roomnumber");
               int price = resultSet.getInt("price");
               boolean isReserved = resultSet.getBoolean("is_reserved");
               Building roomType =  Building.valueOf( resultSet.getString("room_type"));
               room = new Room(price,roomNumber,isReserved,roomType);
               roomsMap.put(room.getRoomnumber(),room);
           }
       }
       public static void updateUsers() throws SQLException
       {
           getUsers();
       }
    public static Admin getAdminFromDataBase() throws SQLException {
        String querry = "SELECT * FROM Admin";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(querry);
        resultSet.next();
        return new Admin(resultSet.getString("name"), resultSet.getString("password"));
    }
    public static void InsertRoomIntoDataBase(Room room) throws SQLException {
        String query = "INSERT INTO rooms (price,is_reserved,room_type) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, room.getRoomprice());
        preparedStatement.setBoolean(2, room.isIsreserved());
        preparedStatement.setObject(3,room.getRoomtype(),Types.OTHER);
        preparedStatement.executeUpdate();
    }
    public static void insertUsersIntoDatabase(User user) throws SQLException {
        String query = "INSERT INTO Clients (username, password) VALUES (?, ?)";
        PreparedStatement statement = DataBaseConnexion.connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();
    }

    public static void insertModifiesRoom(Room room) throws SQLException
    {
        String querry ="UPDATE ROOMS SET price = ? , room_type = ? WHERE roomnumber = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(querry);
        preparedStatement.setInt(1,room.getRoomprice());
        preparedStatement.setObject(2,room.getRoomtype(),Types.OTHER);
        preparedStatement.setInt(3,room.getRoomnumber());
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null,"inserted Succecfully");
    }

    public static void deleteRoom(Room room)throws SQLException
    {
        String deletRoomQuerry = "DELETE FROM ROOMS WHERE roomnumber = ?";
        String deleteRoomFromReservation =  "DELETE FROM RESERVATIONS WHERE room_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deletRoomQuerry);
        PreparedStatement preparedStatement1 = connection.prepareStatement(deleteRoomFromReservation);
        preparedStatement.setInt(1,room.getRoomnumber());
        preparedStatement1.setInt(1,room.getRoomnumber());
        preparedStatement1.executeUpdate();
        preparedStatement.executeUpdate();
    }
    public static void PrintData()
    {
        System.out.println(roomsMap);
    }
}
