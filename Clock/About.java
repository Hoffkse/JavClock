package Clock;

import layout.TableLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

/**
 * Created by cpc27 on 3/23/2017.
 */
public class About extends JDialog implements ActionListener {

    JTextArea aboutInfo;
    JButton close;
    JButton copy;

    public About (Frame parent)
    {
        super(parent, "About JavClock", true);
        initDialog();
    }

    public void initDialog()
    {
        aboutInfo = new JTextArea();
        aboutInfo.setLineWrap(true);
        aboutInfo.setText("Hi there, welcome to JavClock! This is a prototype experiment to broaden my skills using the variety of different libraries Java has. "
                + System.lineSeparator() + System.lineSeparator() +
                "There may be bugs as I am still currently expanding the functionality of     the application. "
                + System.lineSeparator() + System.lineSeparator() +
                "If you wish to email me please direct to Hoffkse@gmail.com. Thanks!");
        aboutInfo.setEditable(false);
        Border b = BorderFactory.createLineBorder(Color.BLACK);
        aboutInfo.setBorder(b);
        JScrollPane scroll = new JScrollPane(aboutInfo);

        close = new JButton("Return");
        close.addActionListener(this);

        copy = new JButton("Copy Email to Clipboard");
        copy.addActionListener(this );


        //double size[][] = {{TableLayout.FILL, TableLayout.FILL}, {TableLayout.FILL,TableLayout.FILL}};

        double border = 10;
        double size[][] = {{border, 400, 10, 170, border}, {border, 200 ,10, 200, border}};
        setLayout(new TableLayout(size));


        add(aboutInfo, "1, 1, 1, 3");
        add(copy, "3, 1");
        add(close, "3, 3");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
        requestFocus();
    }

    public void resetTextField(JTextField t){
        t.setText("");
    }

    public void actionPerformed (ActionEvent e)
    {

        if (e.getSource() == copy)
        {
            String email = new String("Hoffkse@gmail.com");
            StringSelection stringSelection = new StringSelection(email);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
        }
        if (e.getSource() == close)
        {

            dispose();
        }
    }
}
