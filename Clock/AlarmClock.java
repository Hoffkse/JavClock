package Clock;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javafx.util.Pair;
import java.util.Date;

public class AlarmClock {

    Timer clockTicking;
    private int hour;
    private int minutes;
    private int seconds;
    private int timeArray[];
    private Boolean timeActive = false;
    private String time;
    private String AMPM;


    public AlarmClock()
    {
        startClock();

        // MOVED TO THE THREAD FOR TIMER, CONSTRUCTER NO LONGER NEEDED FOR NOW
        /*
        //getting time using system time formatter
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("hh mm ss a");
        time = df.format(d);
        setTimeVars(time);
        */
        //System.out.println("the time is: " + time);

    }

    private void setTimeVars(String t)
    {

        String[] timeValues = t.split(" ");
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

       // System.out.println("Hour is: " + hour);
        //System.out.println("minutes is: " + minutes);
        //System.out.println("seconds is: " + seconds);
       // System.out.println(AMPM);

    }

    private void setTime(int h, int m, int s, String a)
    {
        hour = h;
        minutes = m;
        seconds = s;
        AMPM = a;
    }

    /*
    public AlarmClock getClockTime()
    {
        AlarmClock time = new AlarmClock();
        time.setTime(this.hour, this.minutes, this.seconds, this.AMPM);
        return time;
    }
    */

    public int getHour()
    {
        return this.hour;
    }

    public int getMinutes()
    {
        return this.minutes;
    }

    public int getSeconds()
    {
        return this.seconds;
    }

    public String getAMPM  ()
    {
        return this.AMPM;
    }

    private Pair<Integer, Integer> returnTime ()
    {//
        return new Pair<>(hour, minutes);
    }

    private String checkTimeStatus ()
    {
        if (timeActive == true)
        {
            return("The time is still ticking");
        }
        else {
            return ("Time is stopped");
        }
    }

    public void startClock ()
    {
        clockTicking = new Timer();
        timeActive = true;
        clockTicking.schedule(new addInterval(), 100, 1000);
    }


    public void stopTimer ()
    {
        timeActive = false;
        clockTicking.cancel();
    }

    class addInterval extends TimerTask{
        public void run()
        {
            //NEW CODE - ALARM CLOCK BASED OFF SYSTEM TIME UPDATING EVERY SECOND
            if ( timeActive == true)
            {
                Date d = new Date();
                SimpleDateFormat df = new SimpleDateFormat("hh mm ss a");
                time = df.format(d);
              //  System.out.println(time);
                setTimeVars(time);
            }
            else
            {

            }
            //old code to manually run time TIMER IDEA
            /*
            minutes++;
            if (minutes == 60)
            {
                minutes = 0;
                hour++;
                if (hour == 24)
                {
                    hour = 0;
                }
            }
            System.out.println("Hour: "+ hour + "," + " Minutes: " + minutes);
            */
        }
    }

    public String getTimeString()
    {
        if (minutes < 10 && seconds < 10)
        {
            String timeString = new String(String.format("%d:0%d:0%d %s", hour, minutes, seconds, AMPM));
            return timeString;
        }
        else if(minutes < 10)
        {
            String timeString = new String(String.format("%d:0%d:%d %s", hour, minutes, seconds, AMPM));
            return timeString;
        }
        else if (seconds < 10)
        {
            String timeString = new String(String.format("%d:%d:0%d %s", hour, minutes, seconds, AMPM));
            return timeString;
        }
        else
        {
            String timeString = new String(String.format("%d:%d:%d %s", hour, minutes, seconds, AMPM));
            return timeString;
        }

    }

}
