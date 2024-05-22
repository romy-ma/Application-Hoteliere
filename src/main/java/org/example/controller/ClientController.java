package org.example.controller;


import org.example.model.*;
import org.example.view.ClientView;
import org.example.view.MainPage;
import org.example.view.ReservationFrame;
import org.example.view.ReservationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class ClientController {
    private ClientView clientView;
     User user;
    Reservation reservation;
    ReservationFrame reservationFrame;

    public ClientController(ClientView clientView, User user) {
        this.clientView = clientView;
        this.user = user;
        reservation = DataBaseConnexion.reservationMap.get(DataBaseConnexion.usersMap.get(user.getUsername()).getReservationNumber());
        clientView.onClickedViewReservationButton(new ViewReservationButtonListener());
        clientView.onClickedLogOutButton(new LogOutButtonListener());

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
                reservation = DataBaseConnexion.reservationMap.get(DataBaseConnexion.usersMap.get(user.getUsername()).getReservationNumber());
                user = DataBaseConnexion.usersMap.get(user.getUsername());
                reservationFrame.dispose();

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
            if(user.getReservationNumber() == 0 || reservation == null)
            {
                JOptionPane.showMessageDialog(null,"You have no reservation","Reservation Infog",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {

                String reservationInfo;
                String username = "Username: " + user.getUsername();
                String reservationNumber = "Rservation Number:" + user.getReservationNumber();
                String roomNumber = "Room Number: " + reservation.getRoomToReserve();
                String beginDate = reservation.getBeginDate().toString();
                String endDate = reservation.getEndDate().toString();
                reservationInfo = new String(username + "\n" + reservationNumber + "\n" + roomNumber + "\n" + beginDate + "\n" + endDate);

                Object[] options = {"Ok","Cancel Reservation"};
                int choice  = JOptionPane.showOptionDialog(null,reservationInfo,"Reservation Info",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                if(choice == 1)
                {
                    DataBaseControler.CancelReservationFromDataBase(user.getUsername());
                    JOptionPane.showMessageDialog(null,"Reservation Canceled","Reservation Canceled",JOptionPane.INFORMATION_MESSAGE);
                    DataBaseControler.updateUsers();
                    reservation = null;
                    DataBaseControler.updateReservations();
                    user = DataBaseConnexion.usersMap.get(user.getUsername());
                }
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
}


