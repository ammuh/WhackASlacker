/**
 * Created by ammu on 5/7/16.
 */
import com.sun.xml.internal.ws.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.io.File;

public class GamePlay{
    //Game Stats
    private volatile int score;
    private volatile int time;
    private JFrame frame;
    private boolean gameRunning;
    private JTextArea timeField;
    private JTextArea scoreField;
    private Hole[] holeThreads = new Hole[12];

    //private final Character[] characters;

    public GamePlay(WhackASlacker w){
        //Initialize game vars and essential UI vars
        frame = w.getFrame();
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
        JPanel grid = new JPanel(new GridLayout(3,4, 0, 0));

        //characters = new Character[1];
        //characters[0]  = new Ammar(this, frame);
        Character.setHoleWH(120,150);
        Character.setGame(this);

        for (int i = 0; i < 12; i++){
            Hole h = new Hole(frame, this);
            grid.add(h.gethPanel());
            holeThreads[i] = h;
        }


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        game.add(grid, BorderLayout.CENTER);
        panel.add(info);
        panel.add(game);

        //Frame Init
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void begin(){
        gameRunning = true;

        scoreField.setText("" + score);

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

    }

    /*public Character[] getCharacters(){
        return characters;
    }*/

    public synchronized void addPoints(int num){
        score += num;
        scoreField.setText("" + score);
    }
    public synchronized void addTime(int num){
        time += num;
    }
    public synchronized int getTime(){
        return time;
    }

}

