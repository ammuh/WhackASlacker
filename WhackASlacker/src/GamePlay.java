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
    private JLayeredPane[] holes = new JLayeredPane[12];
    private Hole[] holeThreads = new Hole[12];

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

        JPanel[] j = new JPanel[12];

        for (int i = 0; i < 12; i++){
            JLayeredPane l = new JLayeredPane();
            l.setPreferredSize(new Dimension(150,100));
            JPanel pan = new JPanel(){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(150, 100);
                };
            };
            l.setBounds(0, 0, 150, 100);
            JLabel jl = new JLabel();
            jl.setBounds(0,0,150,100);
            //jl.setIcon(new ImageIcon(ImgUtils.scaleImage(150, 100, "src/res/img/desk.png")));
            //l.add(new JLabel(new ImageIcon(ImgUtils.scaleImage(150, 100 ,"src/res/img/ammar.png"))), new Integer(0), 0);
            l.add(pan, new Integer(0), 0);
            grid.add(l);
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

        /*
        time = 30;
        score = 0;
        timeField.setText("" + time);
        scoreField.setText("" + score);*/
    }



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

    private class ImageLabel extends JLabel{
        private Image _myimage;

        public ImageLabel(){
            super();
        }

        public void setIcon(Icon icon) {
            super.setIcon(icon);
            if (icon instanceof ImageIcon)
            {
                _myimage = ((ImageIcon) icon).getImage();
            }
        }

        @Override
        public void paint(Graphics g){
            g.drawImage(_myimage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}

