package Clock;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AlarmGUI extends JFrame {

    AlarmClock clock = new AlarmClock(0, 0);

    Timer rePaint;
    public AlarmGUI(){
        initGUI();
    }

    public void initGUI()
    {

        //Main panel we will be adding to using gridBagLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        //setting gbc values for the clock panel and adding to the content panel
        GridBagConstraints gbc = new GridBagConstraints();

        //gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;


        clockPanel = new DrawClock();
        //clockPanel.setBackground(Color.BLACK);
        Dimension panelSize = new Dimension(500,500);
        clockPanel.setPreferredSize(panelSize);
        contentPanel.add(AlarmClock.AlarmPanel, gbc);

        //setting the changed gbc values for the button panel to be added to contentpane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        JButton startClock = new JButton("START CLOCK");
        startClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clock.startClock();
            }
        });
        JButton stopClock = new JButton("STOP CLOCK");
        stopClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clock.stopTimer();
            }
        });
        JButton exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        buttonPanel.add(startClock);
        buttonPanel.add(stopClock);
        buttonPanel.add(exit);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.setBackground(Color.cyan);
        contentPanel.add(buttonPanel, gbc);

        //setting up the frame options for the entire frame itself
        add(contentPanel);
        setTitle("WAKE UP!");
        //setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }


}