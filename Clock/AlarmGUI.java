package Clock;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Timer;


public class AlarmGUI extends JFrame implements ActionListener {


    private AlarmClock clock;
    private Timer rePaint;
    private String userDesiredTime;
    private JButton stopAlarm;
    private JTextField status;
    private JMenuItem exit;
    private JMenuBar mBar;
    private JMenuItem about;
    private JMenu options;
    private JMenu file;
    private JMenuItem startClock;
    private JMenuItem stopClock;
    private JMenuItem addAlarmTime;
    private JMenuItem setAlarmTime;
    private timeModes mode;

    public  AlarmGUI(){
        initGUI();
    }

    private enum timeModes
    {
        IDLE, RINGING;
    }

    public void initGUI()
    {
        clock = new AlarmClock();
        startTimer();

        mode = timeModes.IDLE;

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

        //menuBar
        mBar = new JMenuBar();
        file = new JMenu("File");
        options = new JMenu("Options");
        about = new JMenu("About..");

        startClock = new JMenuItem("Resume the clock..");
        startClock.addActionListener(this);
        stopClock = new JMenuItem("Pause the clock..");
        stopClock.addActionListener(this);
        addAlarmTime = new JMenuItem("Add Alarm Time");
        addAlarmTime.addActionListener(this);
        exit = new JMenuItem("Exit Application");
        exit.addActionListener(this);
        setAlarmTime = new JMenuItem("Set Alarm Time");
        setAlarmTime.addActionListener(this );
        about = new JMenuItem("About..");
        about.addActionListener(this);


        mBar.add(file);
        mBar.add(options);
        mBar.add(about);
        options.add(startClock);
        options.add(stopClock);
        file.add(addAlarmTime);
        file.add(setAlarmTime);
        file.add(about);
        file.add(exit);

        setJMenuBar(mBar);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        stopAlarm = new JButton("Stop Alarm!");
        stopAlarm.addActionListener(this);
        stopAlarm.setEnabled(false);

        status = new JTextField("Welcome!");
        status.setHorizontalAlignment(JTextField.CENTER);
        status.setEditable(false);
        infoPanel.add(stopAlarm);
        infoPanel.add(status);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.setBackground(Color.cyan);
        contentPanel.add(infoPanel, gbc);

        add(contentPanel);
        setTitle("JavClock V1.00");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == addAlarmTime)
        {
            status.setText("Adding an alarm to the repository..");
            UserTimeDialog settingTime = new UserTimeDialog(this);
            userDesiredTime = settingTime.returnTimeValues();
            status.setText("The specified time has been added to list");
            System.out.println("the String of user setting time in GUI is: " + userDesiredTime);

            if (userDesiredTime == null)
            {
                //Only way this is accessed is if the user hits cancel
            }
        }
        else if (e.getSource() == exit)
        {
            System.exit(0);
        }
        else if (e.getSource() == startClock)
        {
            status.setText("Global clock is resumed");
            clock.startClock();
            startTimer();
        }
        else if (e.getSource() == stopClock)
        {
            status.setText("Global clock is paused");
            clock.stopTimer();
            rePaint.cancel();
            rePaint.purge();
        }
        else if (e.getSource() == stopAlarm)
        {
            mode = timeModes.IDLE;
            status.setText("Alarm is silenced..");
            stopAlarm.setEnabled(false);
        }

        else if (e.getSource() == about)
        {
            status.setText("Viewing about section..");
            About aboutSection = new About(this);
            aboutSection.resetTextField(status);
        }

    }

    private void checkTime(String s) {
        int hour = 0;
        int minutes = 0;
        int seconds = 0;
        String AMPM = "";
        String[] timeValues = s.split(":");
        int len = timeValues.length;
        for (int z = 0; z < len; z++) {
            switch (z) {
                case 0:
                    hour = Integer.parseInt(timeValues[z]);
                case 1:
                    minutes = Integer.parseInt(timeValues[z]);
                case 2:
                    seconds = Integer.parseInt(timeValues[z]);
                case 3:
                    AMPM = timeValues[z];
            }
        }
        if (AMPM.equals(clock.getAMPM())){
            if (hour == clock.getHour()) {
                if (minutes == clock.getMinutes()) {
                    if (seconds == clock.getSeconds()) {
                        System.out.println("The alarm should be ringing!");
                        mode = timeModes.RINGING;

                    }
                }
            }
         }
    }

    private void startTimer()
    {
        rePaint = new Timer();
        rePaint.schedule(new addInterval(), 100, 1000);

    }

    public void stopTimer ()
    {
        rePaint.cancel();
    }


    public void resetTextField(){
        status.setText("");
    }


    public class AlarmPanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (mode == timeModes.RINGING)
            {
                Font alarmFont = new Font ("Courier New", 1, 50);
                String time = clock.getTimeString();
                g.setFont(alarmFont);
                FontMetrics alarmFontMetrics = g.getFontMetrics();
                int xTime = (getWidth() - alarmFontMetrics.stringWidth(time)) / 2;
                int yTime = ((getHeight() - alarmFontMetrics.getHeight()) / 2) + alarmFontMetrics.getAscent();
                g.setColor(Color.RED);
                g.drawString(time, xTime, yTime);
                Font myFont = new Font ("Courier New", 1, 30);
                String alarmGoingOff = ("THE ALARM IS GOING OFF!!");
                g.setFont(myFont);
                int xMessage = xTime - 60 ;
                int yMessage = yTime + 50;
                g.setColor(Color.CYAN);
                g.drawString(alarmGoingOff, xMessage, yMessage );
                stopAlarm.setEnabled(true);
            }
            else if (mode == timeModes.IDLE) {
                Font myFont = new Font ("Courier New", 1, 50);
                String time = clock.getTimeString();
                g.setFont(myFont);
                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(time)) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g.setColor(Color.RED);
                g.drawString(time, x, y);
            }
        }
    }

    class addInterval extends TimerTask {
        public void run()
        {
            if (userDesiredTime != null) {
                checkTime(userDesiredTime);
            }
            repaint();
        }
    }
}