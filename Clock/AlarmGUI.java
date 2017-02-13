package Clock;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Timer;



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
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;


        AlarmPanel clockPanel = new AlarmPanel();
        clockPanel.setBackground(Color.BLACK);
        Dimension panelSize = new Dimension(500,500);
        clockPanel.setPreferredSize(panelSize);
        contentPanel.add(clockPanel, gbc);

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
                startTimer();
            }
        });
        JButton stopClock = new JButton("STOP CLOCK");
        stopClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clock.stopTimer();
                stopTimer();
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


    public void startTimer()
    {
        Timer rePaint = new Timer();
        rePaint.schedule(new addInterval(), 100, 1000);

    }
    public void stopTimer ()
    {
        rePaint.cancel();
    }

    public class AlarmPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.drawLine(10, 10, 50,50);
            g.drawString(clock.getTimeString(), 50, 50);
        }
    }

    class addInterval extends TimerTask {
        public void run()
        {
            repaint();
        }
    }


}