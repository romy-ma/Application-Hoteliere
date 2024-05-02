package org.example.controller;

import org.example.model.DataBaseConnexion;
import org.example.model.User;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public class DataBaseControler {
    public static void getRooms()
    {
        try
        {
            DataBaseConnexion.getRooms();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"error while getting rooms");
        }
    }
    public static void getUsers()
    {
        try {
            DataBaseConnexion.getUsers();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error data base while getting users");
        }
    }

    public static void getAdmin()
    {
        try {
            DataBaseConnexion.getAdminFromDataBase();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error while getting the admin");
        }
    }
    public static void getConnexion()
    {
        try {
            DataBaseConnexion.getConnection();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error While connecting");
        }
    }

    public static void insertUserToDatBase(User user)
    {
        try {
            DataBaseConnexion.insertUsersIntoDatabase(user);
        }
        catch (SQLException sqlep)
        {
            JOptionPane.showMessageDialog(null,"Error While inserting user");
        }
    }

    public static void updateUsers()
    {
        try
        {
            DataBaseConnexion.updateUsers();
        }
        catch(SQLException ep)
        {
            JOptionPane.showMessageDialog(null,"Error While Updating Users");
        }
    }

    public static boolean isUserNameExist(String userName)
    {
       return DataBaseConnexion.usersMap.containsKey(userName);
    }
}
