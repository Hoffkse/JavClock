package Clock;

import layout.TableLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cpc27 on 3/23/2017.
 */
public class About extends JDialog implements ActionListener {

    JTextArea aboutInfo;
    JButton close;

    public About (Frame parent)
    {
        super(parent, "About JavClock", true);
        initDialog();
    }

    public void initDialog()
    {
        aboutInfo = new JTextArea();
        aboutInfo.setEditable(false);
        JScrollPane scroll = new JScrollPane(aboutInfo);

        close = new JButton("Return");
        close.addActionListener(this);



        setLayout(new TableLayout());


       // GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.BOTH;
        //gbc.gridy = 0;
        //gbc.gridx = 0;
        Dimension textArea = new Dimension(200, 200);
        aboutInfo.setPreferredSize(textArea);
        add(aboutInfo, "0, 0");
        add(close, "0, 1");
       // this.add(aboutInfo, gbc);

        //gbc.anchor = GridBagConstraints.SOUTH;
        //gbc.gridy = 1;
        //gbc.gridx = 1;
        //gbc.weightx = 1;
        //gbc.weighty = .5;
        //add(close, gbc);








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
