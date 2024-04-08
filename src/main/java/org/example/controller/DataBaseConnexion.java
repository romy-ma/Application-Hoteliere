package org.example.controller;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnexion {
    private static String url = "jdbc:postgresql://pg-1b316499-grabachakib008-e4aa.a.aivencloud.com:10488/defaultdb";
    private static String username_data = "avnadmin";
    private static String password_database = null;
    private static final int Password_Line =4;
    private static String File_path = "src\\main\\java\\org\\example\\Data_Base_Info.txt";
       public static Connection getConnection() throws SQLException
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
           return DriverManager.getConnection(url,username_data,password_database);
       }
}
