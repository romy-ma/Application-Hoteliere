package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import org.example.model.DateException;
import org.example.model.HotelDate;

public class ClientView {
    private JTextField beginDayField;
    private JTextField beginMonthField;
    private JTextField beginYearField;
    private JTextField endDayField;
    private JTextField endMonthField;
    private JTextField endYearField;
    private JButton reserveButton;

    public ClientView() {
        // Initialize components
        beginDayField = new JTextField();
        beginMonthField = new JTextField();
        beginYearField = new JTextField();
        endDayField = new JTextField();
        endMonthField = new JTextField();
        endYearField = new JTextField();
        reserveButton = new JButton("Reserve");

        // Set layout and add components
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Begin Date - Day:"));
        panel.add(beginDayField);
        panel.add(new JLabel("Begin Date - Month:"));
        panel.add(beginMonthField);
        panel.add(new JLabel("Begin Date - Year:"));
        panel.add(beginYearField);
        panel.add(new JLabel("End Date - Day:"));
        panel.add(endDayField);
        panel.add(new JLabel("End Date - Month:"));
        panel.add(endMonthField);
        panel.add(new JLabel("End Date - Year:"));
        panel.add(endYearField);
        panel.add(new JLabel()); // Placeholder for spacing
        panel.add(reserveButton);

        // Create a frame and add the panel to it
        JFrame frame = new JFrame("Client View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    
    public HotelDate getBeginDate() throws DateException {
        
        int beginDay = Integer.parseInt(beginDayField.getText());
        int beginMonth = Integer.parseInt(beginMonthField.getText());
        int beginYear = Integer.parseInt(beginYearField.getText());

        
        return new HotelDate(beginDay, beginMonth, beginYear);
    }

    // recuperer date fin 
    public HotelDate getEndDate() throws DateException {
       
        int endDay = Integer.parseInt(endDayField.getText());
        int endMonth = Integer.parseInt(endMonthField.getText());
        int endYear = Integer.parseInt(endYearField.getText());

        //creation de lobjet hoteldate 
        return new HotelDate(endDay, endMonth, endYear);
    }

    // Method to set action listener for reserve button
    public void onReserveButtonClicked(ActionListener listener) {
        reserveButton.addActionListener(listener);
    }

    public void enableReservationButton() {
        reserveButton.setVisible(true); // Activer le bouton
    }

    // Method to display a message to the user
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}


