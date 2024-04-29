package org.example.view;

import org.example.model.Building;
import org.example.model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModifyRoomView extends JFrame {
    JButton ApplyChanges;
    JTextField RoomNumber;
    JComboBox<Building> roomType;
    JTextField RoomPrice;
    Building[] options = Building.values();
    public ModifyRoomView()
    {
        JLabel putRoomNumber = new JLabel("put the room number:");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2,10,10));
        panel.add(putRoomNumber);

        RoomNumber = new JTextField(20);
        panel.add(RoomNumber);

        JLabel setRoomType = new JLabel("set Room Type");
        panel.add(setRoomType);

        roomType = new JComboBox<>(options);
        panel.add(roomType);

        JLabel setPrice = new JLabel("set Room Price");
        panel.add(setPrice);

        RoomPrice = new JTextField(20);
        panel.add(RoomPrice);

        ApplyChanges = new JButton("Apply");
        panel.add(ApplyChanges);

        this.add(panel);
        setVisible(true);
    }

    public Integer getRoomNumber() throws NumberFormatException
    {
        return Integer.parseInt(RoomNumber.getText().replace(" ",""));
    }
    public Integer getRoomPrice() throws NumberFormatException
    {
        return Integer.parseInt(RoomPrice.getText().replace(" ",""));
    }
    public Building getRoomType()
    {
        return (Building) roomType.getSelectedItem();
    }

    public void OnClickedApply(ActionListener listener)
    {
        ApplyChanges.addActionListener(listener);
    }
}
