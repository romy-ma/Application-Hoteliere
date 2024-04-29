package org.example.view;

import org.example.controller.DataBaseConnexion;
import org.example.model.Building;
import org.example.model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RoomModifiersChoosers extends JFrame {
    CreateRoomView createRoomView;
    ModifyRoomView modifyRoomView;
    DeleteRoomView deleteRoomView;
    public RoomModifiersChoosers()
    {
        JLabel chooseLabel = new JLabel("Choose an Action:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300,200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.add(chooseLabel);
        JButton createRoomButton = new JButton("Create Room");
        JButton modifyRoomButton = new JButton("Modfy Room");
        JButton deleteRoomButton = new JButton("Delete Room");
        createRoomButton.addActionListener(e->
        {
            createRoomView = new CreateRoomView();
            createRoomView.OnClickCreateRoom(new CreateRoomButton());
        });

        modifyRoomButton.addActionListener(e->
        {
            modifyRoomView = new ModifyRoomView();
            modifyRoomView.OnClickedApply(new ModifyRoomButton());
        });

        deleteRoomButton.addActionListener(e->
        {
            deleteRoomView = new DeleteRoomView();
            deleteRoomView.OnClickedConfirmButton(new DeleteButtonListener());
        });

        panel.add(createRoomButton);
        panel.add(modifyRoomButton);
        panel.add(deleteRoomButton);
        this.add(panel);
    }
    class CreateRoomButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Integer price = createRoomView.getPrice();
                Building roomType = createRoomView.getRoomType();
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
              Integer price = modifyRoomView.getRoomPrice();
              Building roomType = modifyRoomView.getRoomType();
              Integer roomnumber = modifyRoomView.getRoomNumber();
              if(DataBaseConnexion.roomsMap.containsKey(roomnumber))
              {
                  if(!DataBaseConnexion.roomsMap.get(roomnumber).isIsreserved())
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
               Integer roomNumber  = deleteRoomView.getRoomNumber();
               if(DataBaseConnexion.roomsMap.containsKey(roomNumber))
               {
                   if(!DataBaseConnexion.roomsMap.get(roomNumber).isIsreserved())
                   {
                       DataBaseConnexion.deleteRoom(new Room(roomNumber));
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
