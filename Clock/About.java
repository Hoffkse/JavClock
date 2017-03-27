package Clock;

import layout.TableLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        aboutInfo.setText("This is the text area.");
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
        double size[][] = {{border, 400, 10, 150, border}, {border, .5 ,10, .5, border}};
        setLayout(new TableLayout(size));


        add(aboutInfo, "1, 1, 1, 3");
        add(copy, "3, 1");
        add(close, "3, 3");

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
        requestFocus();
    }

    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == close)
        {
            dispose();
        }
    }
}
