package org.example.model;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;

public class DataBaseConnexion {
    private static String url = "jdbc:postgresql://pg-1b316499-grabachakib008-e4aa.a.aivencloud.com:10488/defaultdb";
    private static String username_data = "avnadmin";
    private static String password_database = null;
    private static final int Password_Line =4;
    public static HashMap<String, User> usersMap =new HashMap<>();
    public static HashMap<Integer, Room> roomsMap = new HashMap<>();
    public static HashMap<Integer, Reservation> reservationMap = new HashMap<>();
    public static Connection connection;
    public static User user;
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
               String email = resultSet.getString("email");
               int reservationNumber = resultSet.getInt("reservation_number");
               boolean isReserved = resultSet.getBoolean("reservation_status");
               user = new User(username,password,email,reservationNumber,isReserved);
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
        preparedStatement.setBoolean(2, room.isReserved());
        preparedStatement.setObject(3,room.getRoomtype(),Types.OTHER);
        preparedStatement.executeUpdate();
    }
    public static void insertUsersIntoDatabase(User user) throws SQLException {
        String query = "INSERT INTO Clients (username, password,email) VALUES (?, ?,?)";
        PreparedStatement statement = DataBaseConnexion.connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3,user.getEmail());
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

    public static void insertReservationIntoDatabase(Reservation reservation) throws SQLException {
        String query = "INSERT INTO Reservations (begin_date, end_date, room_number,username) VALUES (?, ?, ?,?)";
        String query3 = "UPDATE Clients SET reservation_number = ? WHERE username = ?";
        PreparedStatement statement = DataBaseConnexion.connection.prepareStatement(query);
        PreparedStatement statement3 = DataBaseConnexion.connection.prepareStatement(query3);
        statement.setDate(1, new java.sql.Date(reservation.getBeginDate().toSqlDate().getTime()));
        statement.setDate(2, new java.sql.Date(reservation.getEndDate().toSqlDate().getTime()));
        statement.setInt(3, reservation.getRoomToReserve());
        statement.setString(4,reservation.getUsername());
        statement.executeUpdate();
        String query4 = "SELECT reservation_number FROM Reservations WHERE room_number = ? AND username = ?";
        PreparedStatement statement4 = connection.prepareStatement(query4);
        statement4.setInt(1,reservation.getRoomToReserve());
        statement4.setString(2,reservation.getUsername());
        ResultSet resultSet = statement4.executeQuery();
        resultSet.next();
        int reservationNumber = resultSet.getInt("reservation_number");
        statement3.setInt(1,reservationNumber);
        statement3.setString(2,reservation.getUsername());
        statement3.executeUpdate();
        String query2 = "UPDATE Rooms SET is_reserved = true WHERE roomnumber = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setInt(1, reservation.getRoomToReserve());
        statement2.executeUpdate();

    }
    public static void updateRooms() throws SQLException
    {
        getRooms();
    }
    public static Reservation getReservationFromDataBase(String username) throws SQLException {
        String query = "SELECT * FROM Reservations WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return new Reservation(resultSet.getInt("room_number"), resultSet.getString("username"));
    }
    public static void getReservationsFromdataBase() throws SQLException, DateException {
        String query = "SELECT * FROM Reservations";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Reservation reservation = null;
        while(resultSet.next())
        {
            int reservationNumber = resultSet.getInt("reservation_number");
            int roomNumber = resultSet.getInt("room_number");
            String username = resultSet.getString("username");
            Date beginDate = resultSet.getDate("begin_date");
            Date endDate = resultSet.getDate("end_date");
            HotelDate begin = new HotelDate(beginDate.getDate(),beginDate.getMonth()+1,beginDate.getYear()+1900);
            HotelDate end = new HotelDate(endDate.getDate(),endDate.getMonth()+1,endDate.getYear()+1900);
            reservation = new Reservation(roomNumber,begin,end,username);
            reservationMap.put(reservationNumber,reservation);

        }

    }
    public static void CancelReservationFromDataBase(String username) throws SQLException {
        String query1 = "UPDATE Rooms SET is_reserved = false WHERE roomnumber = (SELECT room_number FROM Reservations WHERE username = ?)";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, username);
        statement1.executeUpdate();
        String query2 = "UPDATE Clients SET reservation_number = NULL, RESERVATION_STATUS = NULL WHERE username = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, username);
        statement2.executeUpdate();
        String query = "DELETE FROM Reservations WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.executeUpdate();
        String query3 = "UPDATE ROOMS SET is_reserved = false WHERE roomnumber = ?";
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement3.setInt(1, reservationMap.get(usersMap.get(username).getReservationNumber()).getRoomToReserve());
        statement3.executeUpdate();
        reservationMap.remove(usersMap.get(username).getReservationNumber());
    }
    public static void updateReservations() throws SQLException, DateException {
        getReservationsFromdataBase();
    }
    public static void acceptReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE Clients SET reservation_status = true WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, reservation.getUsername());
        statement.executeUpdate();
        String query2 = "UPDATE Rooms SET is_reserved = true WHERE roomnumber = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setInt(1, reservation.getRoomToReserve());
        statement2.executeUpdate();
    }
    public static void declineReservation(Reservation reservation) throws SQLException
    {
        String query = "DELETE FROM Reservations WHERE username = ?";
        String query2 = "UPDATE Clients SET reservation_number = NULL, reservation_status = NULL WHERE username = ?";
        String query3 = "UPDATE Rooms SET is_reserved = false WHERE roomnumber = ?";
        PreparedStatement statement = connection.prepareStatement(query2);
        PreparedStatement statement2 = connection.prepareStatement(query);
        PreparedStatement statement3 = connection.prepareStatement(query3);
        statement.setString(1, reservation.getUsername());
        statement2.setString(1, reservation.getUsername());
        statement3.setInt(1, reservation.getRoomToReserve());
        statement.executeUpdate();
        statement2.executeUpdate();
        statement3.executeUpdate();

    }
    public static String hashString(String stringToHash) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(stringToHash.getBytes());

        byte[] hashedBytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



}



