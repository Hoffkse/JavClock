package Clock;


import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Timer;



public class AlarmGUI extends JFrame implements ActionListener {


    AlarmClock clock;
    Timer rePaint;
    Boolean alarmTriggered;
    String userDesiredTime;
    JButton addAlarmTime;
    JButton exit;
    JButton startClock;
    JButton stopClock;


    public AlarmGUI(){
        initGUI();
    }

    public void initGUI()
    {
        clock = new AlarmClock();
        startTimer();
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
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

        startClock = new JButton("RESUME CLOCK");
        startClock.addActionListener(this);

        stopClock = new JButton("PAUSE CLOCK");
        stopClock.addActionListener(this);

        exit = new JButton("EXIT");
        exit.addActionListener(this);

        addAlarmTime = new JButton( "Set Clock to Ring");
        addAlarmTime.addActionListener(this);

        buttonPanel.add(startClock);
        buttonPanel.add(stopClock);
        buttonPanel.add(addAlarmTime);
        buttonPanel.add(exit);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.setBackground(Color.cyan);
        contentPanel.add(buttonPanel, gbc);

        add(contentPanel);
        setTitle("WAKE UP!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == addAlarmTime)
        {
            UserTimeDialog settingTime = new UserTimeDialog(this);
            userDesiredTime = settingTime.returnTimeValues();
            System.out.println("the String of user setting time in GUI is: " + userDesiredTime);
            if (userDesiredTime == null)
            {
                JOptionPane.showMessageDialog(this, "The time entered was not valid!");
            }
        }
        else if (e.getSource() == exit)
        {
            System.exit(0);
        }
        else if (e.getSource() == startClock)
        {
            clock.startClock();
            startTimer();
        }
        else if (e.getSource() == stopClock)
        {
            clock.stopTimer();
            rePaint.cancel();
            rePaint.purge();
        }

    }

    public void startTimer()
    {
        rePaint = new Timer();
        rePaint.schedule(new addInterval(), 100, 1000);

    }
    public void stopTimer ()
    {
        rePaint.cancel();
    }

    public class AlarmPanel extends JPanel
    {
        Font myFont = new Font ("Courier New", 1, 50);
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            String time = clock.getTimeString();
            g.setFont(myFont);
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(time)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g.setColor(Color.RED);
            g.drawString(time, x, y);
        }
    }

    class addInterval extends TimerTask {
        public void run()
        {
            repaint();
        }
    }


}