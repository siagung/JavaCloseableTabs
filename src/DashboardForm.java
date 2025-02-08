/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.BorderLayout;
import javax.swing.*;

public class DashboardForm extends JPanel {

    public DashboardForm() {
        setLayout(new BorderLayout());
        add(new JLabel("Welcome to the Houseware Store Dashboard", SwingConstants.CENTER), BorderLayout.CENTER);

        // Add more components for the dashboard here
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("View Reports"));
        buttonPanel.add(new JButton("Manage Inventory"));
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
