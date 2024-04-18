package org.example.view;

import org.example.model.Admin;
import org.example.model.Building;
import javax.swing.*;
import java.awt.event.ActionListener;


public class AdminView extends JFrame {
    JButton createRoomButton;
    JButton modifyRoomButton;
    JButton deleteRoomButton;
    JTextField priceOfRoom;
    Admin admin;
    JComboBox<Building> roomType;
    Building[] options = Building.values();
    public AdminView(Admin admin)
    {
        this.admin = admin;
        setTitle("Admin Actions");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel setPrice = new JLabel("Set price of the room");
        panel.add(setPrice);

        priceOfRoom = new JTextField(20);
        panel.add(priceOfRoom);

        JLabel setRoomType = new JLabel("Set type of the room");
        panel.add(setRoomType);

        roomType = new JComboBox<>(options);
        panel.add(roomType);

        createRoomButton = new JButton("Create Room");
        panel.add(createRoomButton);
    }
    public Integer getPrice() throws NumberFormatException {

        return Integer.parseInt(priceOfRoom.getText().replace(" ",""));
    }
    public Building getRoomType(){return (Building) roomType.getSelectedItem();}

    public void OnClickCreateRoom(ActionListener listener){createRoomButton.addActionListener(listener);}


}
