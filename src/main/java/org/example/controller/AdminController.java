package org.example.controller;

import org.example.model.Admin;
import org.example.model.Building;
import org.example.model.DataBaseConnexion;
import org.example.model.Room;
import org.example.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminController {
    Admin admin;
    AdminAuthenticate adminAuthenticate;
    RoomModifiersChoosers adminView;
    public AdminController(AdminAuthenticate adminAuthenticate) {
        this.adminAuthenticate = adminAuthenticate;
        adminAuthenticate.onLoginButtonClicked(new LogInButtonListener());
    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminAuthenticate.getUsername().replace(" ","");
            String password = adminAuthenticate.getpassword();
            try {
                Admin test = DataBaseConnexion.getAdminFromDataBase();
                if (username.equals(test.getUsername()) && password.equals(test.getPassword())) {
                    admin = test;
                    JOptionPane.showMessageDialog(null, "Login Succesful\n welcome back Admin");
                    adminView = new RoomModifiersChoosers();
                    adminView.onClickedModifyRoomView(new ButtonChooser2());
                    adminView.onClickedCreateRoomView(new ButtonChooser1());
                    adminView.onClickedDeleteRoomView(new ButtonChooser3());
                    adminView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "wrong username or password");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "could not get admin");
            }
        }
    }

    class ButtonChooser1 implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.createRoomView = new CreateRoomView();
            adminView.createRoomView.OnClickCreateRoom(new CreateRoomButton());
        }
    }
    class ButtonChooser3 implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.deleteRoomView = new DeleteRoomView();
            adminView.deleteRoomView.OnClickedConfirmButton(new DeleteButtonListener());
        }
    }
    class ButtonChooser2 implements  ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.modifyRoomView = new ModifyRoomView();
            adminView.modifyRoomView.OnClickedApply(new ModifyRoomButton());
        }
    }
    class CreateRoomButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer price = adminView.createRoomView.getPrice();
                Building roomType = adminView.createRoomView.getRoomType();
                Room room = new Room(price,roomType);
                DataBaseConnexion.InsertRoomIntoDataBase(room);
                JOptionPane.showMessageDialog(null,"Room inserted succesful");
                return;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"enter a number not special caracters");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error in inserting to dtabase");
            }
        }
    }

    class ModifyRoomButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Integer price = adminView.modifyRoomView.getRoomPrice();
                Building roomType = adminView.modifyRoomView.getRoomType();
                Integer roomnumber = adminView.modifyRoomView.getRoomNumber();
                if(DataBaseConnexion.roomsMap.containsKey(roomnumber))
                {
                    if(!DataBaseConnexion.roomsMap.get(roomnumber).isReserved())
                    {
                        DataBaseConnexion.insertModifiesRoom(new Room(price,roomnumber,false,roomType));
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"This Room Is Reserved Catne delete");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Room Number Doesnt Exist");
                }
            }
            catch (NumberFormatException emp)
            {
                JOptionPane.showMessageDialog(null,"Enter A number not A character");
            }
            catch (SQLException ei)
            {
                ei.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error in Data Base");
            }
        }
    }
    class DeleteButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer roomNumber  = adminView.deleteRoomView.getRoomNumber();
                if(DataBaseConnexion.roomsMap.containsKey(roomNumber))
                {
                    if(!DataBaseConnexion.roomsMap.get(roomNumber).isReserved())
                    {
                        DataBaseConnexion.deleteRoom(new Room(roomNumber));
                        JOptionPane.showMessageDialog(null,"Room Deleted Successfully");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"This Room Is Reserved Cant delete");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"This Room Number Doesnt exist");
                }

            }catch (SQLException ep)
            {
                ep.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error in Data Base");
            }
            catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null,"Enter A Number Only");
            }
        }
    }





}