package org.example.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class RoomModifiersChoosers extends JFrame {
    public CreateRoomView createRoomView;
    public ModifyRoomView modifyRoomView;
    public DeleteRoomView deleteRoomView;
    JButton createRoomButton;
    JButton modifyRoomButton;
    JButton deleteRoomButton;
    public RoomModifiersChoosers()
    {
        JLabel chooseLabel = new JLabel("Choose an Action:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,2,10,10));
        setSize(300,200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.add(chooseLabel);
        createRoomButton = new JButton("Create Room");
        modifyRoomButton = new JButton("Modfy Room");
        deleteRoomButton = new JButton("Delete Room");


        panel.add(createRoomButton);
        panel.add(modifyRoomButton);
        panel.add(deleteRoomButton);
        this.add(panel);
    }
    public void onClickedCreateRoomView(ActionListener listener){createRoomButton.addActionListener(listener);}
    public void onClickedModifyRoomView(ActionListener listener){modifyRoomButton.addActionListener(listener);}
    public void onClickedDeleteRoomView(ActionListener listener){deleteRoomButton.addActionListener(listener);}

}
