package org.example.controller;


import org.example.model.*;
import org.example.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class ClientController {
    private ClientView clientView;
     User user;

    Reservation reservation;
    ViewReservationFrame viewReservationFrame;
    ReservationFrame reservationFrame;

    public ClientController(ClientView clientView, User user) {
        this.clientView = clientView;
        this.user = user;

        reservation = DataBaseConnexion.reservationMap.get(DataBaseConnexion.usersMap.get(user.getUsername()).getReservationNumber());
        clientView.onClickedViewReservationButton(new ViewReservationButtonListener());
        clientView.onClickedLogOutButton(new LogOutButtonListener());
        clientView.setUserNameField(user.getUsername());

        for(int i=0;i<clientView.roomPanels.size();i++)
        {
            clientView.roomPanels.get(i).OnClickedReserveButton(new RservationButtonListener(i));
        }
    }
    class RservationButtonListener implements ActionListener
    {
        int i;
        public RservationButtonListener(int i)
        {
            this.i = i;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(user.getReservationNumber() != 0)
            {
                JOptionPane.showMessageDialog(null,"You have already a reservation","Reservation Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int roomNumber = clientView.roomPanels.get(i).getRoomNumber();
                int roomPrice = clientView.roomPanels.get(i).getRoomPrice();
                Building roomType = clientView.roomPanels.get(i).getRoomType();
                reservation = new Reservation(roomNumber,user.getUsername());
                reservationFrame = new ReservationFrame();
                reservationFrame.onClickedcreateReservationButtonListener(new ReserveButtonListener());
            }

        }
    }

    class ReserveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                HotelDate beginDate = new HotelDate(reservationFrame.getBeginDay(), reservationFrame.getBeginMonth(), reservationFrame.getBeginYear());
                HotelDate endDate = new HotelDate(reservationFrame.getEndDay(), reservationFrame.getEndMonth(), reservationFrame.getEndYear());
                if (beginDate.compareTo(endDate) == 0 || beginDate.compareTo(endDate) == -1) {
                reservation.setBeginDate(beginDate);
                reservation.setEndDate(endDate);
                DataBaseControler.insertReservationIntoDataBase(reservation);
                JOptionPane.showMessageDialog(null, "Reservation created", "Reservation created", JOptionPane.INFORMATION_MESSAGE);
                DataBaseControler.updateUsers();
                DataBaseControler.updateReservations();
                DataBaseControler.updateRooms();
                user= DataBaseConnexion.usersMap.get(user.getUsername());
                reservationFrame.dispose();
                clientView.updateUI();

                    for(int i=0;i<clientView.roomPanels.size();i++)
                    {
                        clientView.roomPanels.get(i).OnClickedReserveButton(new RservationButtonListener(i));
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "End date must be after begin date", "Wrong Date", JOptionPane.ERROR_MESSAGE);
                }

            }  catch (NumberFormatException el) {
                JOptionPane.showMessageDialog(null, "Enter a number", "Enter a number", JOptionPane.ERROR_MESSAGE);
            }
            catch (DateException ep)
            {
                JOptionPane.showMessageDialog(null, "Enter a valid date", "Enter a valid date", JOptionPane.ERROR_MESSAGE);
            }

        }



    }
    class ViewReservationButtonListener implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            reservation = DataBaseConnexion.reservationMap.get(DataBaseConnexion.usersMap.get(user.getUsername()).getReservationNumber());

            if(user.getReservationNumber() == 0 || reservation == null)
            {
                JOptionPane.showMessageDialog(null,"You have no reservation","Reservation Infog",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                viewReservationFrame = new ViewReservationFrame();
                viewReservationFrame.setBeginDate(reservation.getBeginDate().toString());
                viewReservationFrame.setEndDate(reservation.getEndDate().toString());
                viewReservationFrame.setRoomNumber(reservation.getRoomToReserve());
                viewReservationFrame.setRoomPrice(String.valueOf(DataBaseConnexion.roomsMap.get(reservation.getRoomToReserve()).getRoomprice()) + " DA");
                viewReservationFrame.setUserName(user.getUsername());
                viewReservationFrame.setReservationNumber(user.getReservationNumber());
                viewReservationFrame.onClickedCancelReservationButton(new CancelButtonListener());

//                String reservationInfo;
//                String username = "Username: " + user.getUsername();
//                String reservationNumber = "Rservation Number:" + user.getReservationNumber();
//                String roomNumber = "Room Number: " + reservation.getRoomToReserve();
//                String beginDate = reservation.getBeginDate().toString();
//                String endDate = reservation.getEndDate().toString();
//                String price = DataBaseConnexion.roomsMap.get(reservation.getRoomToReserve()).getRoomprice() + " DA";
//                reservationInfo = new String(username + "\n" + reservationNumber + "\n" + roomNumber + "\n" + beginDate + "\n" + endDate + "\n" +"Room Price: " + price);
//
//
//                Object[] options = {"Ok","Cancel Reservation"};
//                int choice  = JOptionPane.showOptionDialog(null,reservationInfo,"Reservation Info",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
//                if(choice == 1)
//                {
//                    DataBaseControler.CancelReservationFromDataBase(user.getUsername());
//                    JOptionPane.showMessageDialog(null,"Reservation Canceled","Reservation Canceled",JOptionPane.INFORMATION_MESSAGE);
//                    DataBaseControler.updateUsers();
//                    reservation = null;
//                    DataBaseControler.updateReservations();
//                    DataBaseControler.updateRooms();
//                    user = DataBaseConnexion.usersMap.get(user.getUsername());
//                    clientView.updateUI();
//                    for(int i=0;i<clientView.roomPanels.size();i++)
//                    {
//                        clientView.roomPanels.get(i).OnClickedReserveButton(new RservationButtonListener(i));
//                    }
//                }
            }
        }
    }
    class LogOutButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientView.dispose();
            MainAuthenticateController mainAuthenticateController = new MainAuthenticateController(new MainPage());
        }
    }
    class CancelButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            DataBaseControler.CancelReservationFromDataBase(user.getUsername());
            JOptionPane.showMessageDialog(null,"Reservation Canceled","Reservation Canceled",JOptionPane.INFORMATION_MESSAGE);
            DataBaseControler.updateUsers();
            reservation = null;
            DataBaseControler.updateReservations();
            DataBaseControler.updateRooms();
            user = DataBaseConnexion.usersMap.get(user.getUsername());
            viewReservationFrame.dispose();
            clientView.updateUI();
            for(int i=0;i<clientView.roomPanels.size();i++)
            {
                clientView.roomPanels.get(i).OnClickedReserveButton(new RservationButtonListener(i));
            }
        }
    }
}


