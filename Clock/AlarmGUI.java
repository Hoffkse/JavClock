package Clock;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AlarmGUI extends JFrame{

    public AlarmGUI()
    {
        initGUI();
    }

    private void initGUI()
    {

        //initializing the alarm clock object
        AlarmClock clock = new AlarmClock(0,0);

        //JFrame constructing
        this.setLayout(new GridLayout(5, 1));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        setTitle("WAKE UP"); // title of the JFrame
        setSize(500,500); // 500 x 500 gui
        setLocationRelativeTo(null); // center screen

        //adding jpanel to hold all buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setSize(150,150);
        buttonPanel.setBackground(Color.darkGray);
        this.add(buttonPanel);


        //Button constructer for starting alarm clock
        JButton startClock = new JButton("START CLOCK");
        startClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clock.startClock();

            }
        });
        buttonPanel.add(startClock);

        //Button constructer for stopping clock
        JButton stopClock = new JButton("STOP CLOCK");
        stopClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clock.stopTimer();
            }
        });
        buttonPanel.add(stopClock);

        this.add(buttonPanel);


    }



}
