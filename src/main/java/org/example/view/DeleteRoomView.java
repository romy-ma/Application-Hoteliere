package org.example.view;

import org.example.model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteRoomView extends JFrame {
    JTextField RoomNumber;
    JButton ConfirmButton;
    public DeleteRoomView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2,10,10));

        JLabel label = new JLabel("Enter thenumber of the room you wanna delete");
        panel.add(label);

        RoomNumber = new JTextField(20);
        panel.add(RoomNumber);

        ConfirmButton = new JButton("Confirm");
        panel.add(ConfirmButton);

        this.add(panel);
        setVisible(true);
    }
    public Integer getRoomNumber() throws NumberFormatException
    {
        return Integer.parseInt(RoomNumber.getText().replace(" ",""));
    }

    public void OnClickedConfirmButton(ActionListener listener)
    {
        ConfirmButton.addActionListener(listener);
    }
}
