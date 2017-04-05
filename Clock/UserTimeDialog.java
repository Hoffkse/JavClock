package Clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class UserTimeDialog extends JDialog implements ActionListener {

    JTextField hours = new JTextField(2);
    JTextField minutes = new JTextField(2);
    JTextField seconds = new JTextField(2);
    JRadioButton AM = new JRadioButton("AM");
    JRadioButton PM = new JRadioButton("PM");
    ButtonGroup timeContext = new ButtonGroup();
    JLabel hour = new JLabel("Hour:");
    JLabel minute = new JLabel("Minute:");
    JLabel second = new JLabel("Second:");
    JButton confirm = new JButton("OK");
    JButton cancel = new JButton("  CANCEL");
    StringBuilder userTime = new StringBuilder();
    String finalValue;
    Boolean incorrectInput;
    String buttonSelected;

    public UserTimeDialog(Frame parent)
    {
        super (parent, "Setting time to ring...", true);
        initDialog();
    }

    public void initDialog()
    {
        confirm.addActionListener(this);
        cancel.addActionListener(this);

        setBackground(Color.BLACK);
        setLayout(new GridLayout(5,2));
        add(hour);
        add(hours);
        add(minute);
        add(minutes);
        add(second);
        add(seconds);
        timeContext.add(AM);
        timeContext.add(PM);
        add(AM);
        add(PM);
        add(confirm);
        add(cancel);

        setDefaultLookAndFeelDecorated(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
        requestFocus();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == confirm)
        {
            checkTimeValues();
            //System.out.println("the value of incorrectinput is at return time: " +incorrectInput.toString());
            if (incorrectInput == false)
            {
                userTime = setTimeValues();
                finalValue = userTime.toString();
                System.out.println("the String of user setting time in UserTimeDialog is: " + finalValue);
                dispose();
            }
            else if (incorrectInput){
               JOptionPane.showMessageDialog(this, "The input you tried to enter did not follow time format, try again.");
            }
        }
        else if (e.getSource() == cancel)
        {
            finalValue = null;
            dispose();
        }

    }


    public StringBuilder setTimeValues ()
    {
        StringBuilder stringHolder = new StringBuilder();
        stringHolder.append(hours.getText());
        stringHolder.append(":");
        stringHolder.append(minutes.getText());
        stringHolder.append(":");
        stringHolder.append(seconds.getText());
        stringHolder.append(":");
        //getting the slected radio button from timeContext buttonGroup and adding to the return string

        stringHolder.append(buttonSelected);

        return stringHolder;
    }

    public String returnTimeValues()
    {
        setVisible(true);
        return finalValue;
    }

    public String getSelectedRadioButton(ButtonGroup bg)
    {
        for (Enumeration<AbstractButton> b = bg.getElements(); b.hasMoreElements();)
        {
            AbstractButton nextButton = b.nextElement();
            if (nextButton.isSelected())
            {
                return nextButton.getText();
            }
        }
        return null;
    }

    public void checkTimeValues()
    {
        incorrectInput = false;
        ArrayList<String> timeValues = new ArrayList<>();
        timeValues.add(hours.getText());
        timeValues.add(minutes.getText());
        timeValues.add(seconds.getText());
        buttonSelected = getSelectedRadioButton(timeContext);
        timeValues.add(buttonSelected);

        for(int z = 0; z < timeValues.size(); z++)
        {
            System.out.println("here");
            int num = 0;
            String holder = timeValues.get(z);
            switch(z){
                case 0:
                    if(holder.length() < 1 || holder.length() > 2)
                    {
                        incorrectInput = true;
                    }
                    try{
                      num = Integer.parseInt(holder);
                    }
                    catch(NumberFormatException e){
                        incorrectInput = true;
                    }
                    if (num <= 0 || num > 12)
                    {
                        incorrectInput= true;
                    }

                case 1:
                    if(holder.length() < 1 || holder.length() > 2)
                    {
                        incorrectInput = true;
                    }
                    try{
                        num = Integer.parseInt(holder);
                    }
                    catch(NumberFormatException e){
                        incorrectInput = true;
                    }
                    if (num < 0 || num >= 60)
                    {
                        incorrectInput= true;
                    }
                    if (num < 10)
                    {

                    }
                case 2:
                    if(holder.length() < 1 || holder.length() > 2)
                    {
                        incorrectInput = true;
                    }
                    try{
                        num = Integer.parseInt(holder);
                    }
                    catch(NumberFormatException e){
                        incorrectInput = true;
                    }
                    if (num < 0 || num >= 60)
                    {
                        incorrectInput= true;
                    }

                case 3:
                    if (holder == null)
                    {
                        incorrectInput = true;
                    }

            }
            System.out.println(incorrectInput.toString());
        }



    }

}
