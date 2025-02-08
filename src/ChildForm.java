/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.awt.BorderLayout;
import javax.swing.*;

public class ChildForm extends JPanel {

    public ChildForm() {
        setLayout(new BorderLayout());
        add(new JLabel("This is a child form", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}