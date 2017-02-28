package Clock;

import javax.swing.*;
import java.awt.*;

public class UserTimeDialog extends JFrame {

    JTextField hours = new JTextField(2);
    JTextField minutes = new JTextField(2);
    JTextField seconds = new JTextField(2);
    JTextField Time = new JTextField(2);
    JLabel hour = new JLabel("Hour:");
    JLabel minute = new JLabel("Minute:");
    JLabel second = new JLabel("Second:");
    JLabel timeText = new JLabel("AM/PM:");
    JButton confirm = new JButton("OK");
    JButton cancel = new JButton("  CANCEL");
    public Boolean status;

    public UserTimeDialog()
    {
        super("Setting time to ring...");
    }

    public void initDialog()
    {


        status = false;
        setBackground(Color.BLACK);
        setLayout(new GridLayout(5,2));
        add(hour);
        add(hours);
        add(minute);
        add(minutes);
        add(second);
        add(seconds);
        add(timeText);
        add(Time);
        add(confirm);
        add(cancel);

        setTitle("Setting time to ring...");
        setPreferredSize(new Dimension(200,300));

        pack();
        setLocationRelativeTo(null);
        toFront();
        requestFocus();
        setVisible(true);

    }

    public Boolean checkIfDone()
    {
        return status;
    }

}
