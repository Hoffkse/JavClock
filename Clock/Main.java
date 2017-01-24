package Clock;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //AlarmClock clock = new AlarmClock(0,0);
        EventQueue.invokeLater(() -> {
            AlarmGUI gui = new AlarmGUI();
            gui.setVisible(true);
        });




    }
}