package Clock;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AlarmGUI gui = new AlarmGUI();
                gui.setVisible(true);
            }
        });
    }
}