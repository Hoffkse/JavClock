package com.Hoffman;

import javax.swing.*;

/**
 * Created by mbh29 on 1/6/2017.
 */
public class clockGUI extends JFrame {
    private JButton startClock;
    private JButton stopClock;
    private JPanel clockDesign;
    private JTextField displayTime;
    private JPanel rootPanel;

    public clockGUI()
    {
        super("Alarm Clock V1");
        this.setSize(700,500);
        this.setContentPane(rootPanel);
       // this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
