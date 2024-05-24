package org.example.controller;

import org.example.model.*;
import org.example.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminController {
    Admin admin;
    AdminAuthenticate adminAuthenticate;
    AdminView adminView;

    MainPage mainPage;
    public AdminController(AdminAuthenticate adminAuthenticate,MainPage mainPage) {
        this.adminAuthenticate = adminAuthenticate;
        adminAuthenticate.onLogInButtonClicked(new LogInButtonListener());
        this.mainPage = mainPage;



    }

    class LogInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminAuthenticate.getUsername().replace(" ","");
            String password = adminAuthenticate.getPassword();
            try {
                Admin test = DataBaseConnexion.getAdminFromDataBase();
                if (username.equals(test.getUsername()) && password.equals(test.getPassword())) {
                    admin = test;
                    adminView = new AdminView();
                    JOptionPane.showMessageDialog(null, "Login Succesful\n welcome back Admin");
                    adminView.onClickedModifyRoomView(new ButtonChooser2());
                    adminView.onClickedCreateRoomView(new ButtonChooser1());
                    adminView.onClickedDeleteRoomView(new ButtonChooser3());
                    adminView.setVisible(true);
                    adminAuthenticate.dispose();
                    adminView.onClickedLogOutButton(new LogOutButtonListener());
                    for(int i=0;i<adminView.reservationsPanelArray.size();i++)
                    {
                        adminView.onClickedAcceptButton(new AcceptButtonListener(i),i);
                        adminView.onClickedDeclineButton(new DeclineButtonListener(),i);
                    }
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
                adminView.createRoomView.dispose();
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
                        adminView.modifyRoomView.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"This Room Is Reserved Cant Modify it");
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
                        adminView.deleteRoomView.dispose();
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
    class AcceptButtonListener implements ActionListener
    {
        int index;
        public AcceptButtonListener(int index)
        {
            this.index = index;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Reservation reservation = DataBaseConnexion.reservationMap.get(adminView.reservationsPanelArray.get(index).getReservationNumber());
                DataBaseConnexion.acceptReservation(reservation);
                JOptionPane.showMessageDialog(null,"Reservation Accepted");
                DataBaseControler.updateRooms();
                DataBaseControler.updateReservations();
                DataBaseControler.updateUsers();
                adminView.updateUI();
                for(int i=0;i<adminView.reservationsPanelArray.size();i++)
                {
                    adminView.onClickedAcceptButton(new AcceptButtonListener(i),i);
                    adminView.onClickedDeclineButton(new DeclineButtonListener(),i);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error in Data Base");
                ex.printStackTrace();
            }
        }
    }
    class DeclineButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Reservation reservation = DataBaseConnexion.reservationMap.get(adminView.reservationsPanelArray.get(0).getReservationNumber());
                DataBaseConnexion.declineReservation(reservation);
                JOptionPane.showMessageDialog(null,"Reservation Declined");
                DataBaseControler.updateRooms();
                DataBaseControler.updateReservations();
                DataBaseControler.updateUsers();
                adminView.updateUI();
                for(int i=0;i<adminView.reservationsPanelArray.size();i++)
                {
                    adminView.onClickedAcceptButton(new AcceptButtonListener(i),i);
                    adminView.onClickedDeclineButton(new DeclineButtonListener(),i);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error in Data Base");
                ex.printStackTrace();
            }
        }
    }
    class LogOutButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            adminView.dispose();
            MainAuthenticateController mainAuthenticateController = new MainAuthenticateController(new MainPage());

        }
    }





}