import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * GamePlay class is instantiated each game and is used to contain game information and handle the graphics and points for each run.
 */
public class GamePlay{
    //Game Stats
    private volatile int score = 0;
    private volatile int time = 100;
    private JFrame frame;
    private boolean gameRunning;
    private JTextArea timeField;
    private JTextArea scoreField;
    public Hole[] holeThreads;
    private Component[] components;
    //Manifest of characters
    private final Character[] characters;

    /**
     * Constructor - Initializes GamePlay, must create character manifest here, and new threads if this is not the first game.
     * @param w WhackaSlacker instance for frame and other general info
     * @param threads Threads that were spawned for each hole
     * @param c Essential GUI components that need to be manipulated
     */
    public GamePlay(WhackASlacker w, Hole[] threads, Component[] c){
        components = c;
        //Initialize game vars and essential UI vars
        frame = w.getFrame();
        //Character Manifest
        characters = new Character[4];
        characters[0]  = new Ammar(this, frame);
        characters[1] = new Ani(this, frame);
        characters[2] = new Charlie(this, frame);
        characters[3] = new Umesh(this, frame);

        Character.setHoleWH(120,150);
        Character.setGame(this);
        //UI Elements
        if(w.getGames().size() < 1){

            holeThreads = threads;
        }else{
            this.holeThreads = new Hole[12];
            Hole[] h = w.getGames().get(0).holeThreads;
            for(int i = 0; i < h.length; i++){
                this.holeThreads[i] = new Hole(frame, h[i].getLabel());
            }
        }
        timeField = (JTextArea) components[0];
        scoreField = (JTextArea) components[1];
    }

    /**
     * Starts the actual GamePlay. Buttons are disabled, timer and queuing thread is created and started.
     */
    public void begin(){
        gameRunning = true;
        components[2].setEnabled(false);
        components[3].setEnabled(false);
        scoreField.setText("" + score);

        Thread timerThread = new Thread(new Runnable() {
            @Override
            /**
             * This is a thread that is used to keep the game time and determine when the game ends.
             */
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

        Thread queueChar = new Thread(new Runnable() {
            @Override
            /**
             * This method is the queuing algorithim that queues Characters to holes in groups of 3 or less.
             */
            public void run() {
                ArrayList<Hole> l = new ArrayList<Hole>(Arrays.asList(holeThreads));
                while (gameRunning){
                    Hole[] holesToAni = new Hole[(int)(Math.random()*3)+1];
                    System.out.println("L size: " + l.size() + " holesToAniSize: " + holesToAni.length);
                    for(int i = 0; i < holesToAni.length; i++){
                        holesToAni[i] = l.remove((int)(Math.random()*l.size()));
                    }
                    System.out.println(Arrays.toString(holesToAni));
                    int time = (int)Math.random()*4000 + 1;
                    for(Hole hole : holesToAni){
                        hole.setPopStatus(true);
                    }
                    for(int i = 0; i < holesToAni.length; i++){
                        holesToAni[i].queue(characters[(int)(Math.random()*characters.length)]);
                    }
                    while(!popComplete(holesToAni)){
                    }
                    System.out.println("pop complete");
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    l = new ArrayList<Hole>(Arrays.asList(holeThreads));
                }
            }

            /**
             * Helper method used to check if a group is finished popping up and down.
             * @param h Group of threads that were queued.
             * @return If pop is complete
             */
            private boolean popComplete(Hole[] h){
                for(Hole hole : h){
                    if(hole.isUp()){
                        return false;
                    }
                }
                return true;
            }
        });

        timerThread.start(); //Start the timer thread.
        for (Hole h : holeThreads) {
            h.setGame(this);
            h.start(); //Starts Hole threads to recieve character objects.
        }
        queueChar.start(); //Start queuing mechanism to send Characters to different Holes.
    }

    /**
     * Renables buttons and resets game.
     */
    public void gameEnd(){
        gameRunning = false;
        components[2].setEnabled(true);
        components[3].setEnabled(true);

    }

    /**
     * Adds points synchronously to avoid memory overwrites.
     * @param num Number of points.
     */
    public synchronized void addPoints(int num){
        score += num;
        scoreField.setText("" + score);
    }

    /**
     * Adds time synchronously to avoid memory overwrites.
     * @param num Amount of time.
     */
    public synchronized void addTime(int num){
        time += num;
        scoreField.setText("" + score);
    }

    /**
     * Gets the current game time.
     * @return time
     */
    public synchronized int getTime(){
        return time;
    }

    /**
     * Gets the current score.
     * @return score
     */
    public int getScore(){
        return score;
    }
}

