/**
 * Created by ammu on 5/7/16.
 */
import com.sun.xml.internal.ws.api.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class GamePlay{
    //Game Stats
    public volatile int score;
    public volatile int time;
    private JFrame frame;
    private boolean gameRunning;
    private JTextArea timeField;
    private JTextArea scoreField;
    private JLabel[] holes = new JLabel[12];
    private Hole[] holeThreads = new Hole[12];

    public GamePlay(JFrame f){
        frame = f;
        score = 0;
        time = 30;

        JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        //Info Layout
        info.setBorder(new EmptyBorder(30, 30, 30, 30));

        info.add(Box.createHorizontalStrut(100));

        JLabel timeLabel = new JLabel("Time Left:");
        info.add(timeLabel);

        timeField = new JTextArea(1, 5);
        timeField.setEditable(false);
        info.add(timeField);
        timeField.setVisible(true);

        JLabel scoreLabel = new JLabel("Score:");
        info.add(scoreLabel);

        scoreField = new JTextArea(1, 5);
        scoreField.setEditable(false);
        info.add(scoreField);
        scoreField.setVisible(true);

        JPanel game = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(0,4));

        for (int i = 0; i < 12; i++){
            JLabel j = new JLabel("     ");
            j.setHorizontalAlignment(JLabel.CENTER);
            holes[i] = j;
            grid.add(j);
            holeThreads[i] = new Hole(j, frame, this);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        game.add(grid, BorderLayout.CENTER);
        panel.add(info);
        panel.add(game);

        //Frame Init
        frame.setContentPane(panel);
        frame.setVisible(true);
        gameRunning = true;
        begin();
    }
    public void begin(){
       /* while(time > 30 && gameRunning){

        }*/


        Thread timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int temp = time;
                while (time > -1) {

                    try {
                        timeField.setText("" + time);
                        time--;
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                gameEnd();

            }
        });

        timerThread.start();

        for (Hole h : holeThreads) {
            h.start();
        }
    }
    public void gameEnd(){
        for (Hole h : holeThreads) {
            h.interrupt();
        }
        /*
        time = 30;
        score = 0;
        timeField.setText("" + time);
        scoreField.setText("" + score);*/
    }

}
