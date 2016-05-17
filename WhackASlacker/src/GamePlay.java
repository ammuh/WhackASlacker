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

    private final Character[] characters;

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

    public void begin(){
        gameRunning = true;
        components[2].setEnabled(false);
        components[3].setEnabled(false);
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

        Thread queueChar = new Thread(new Runnable() {
            @Override
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

            private boolean popComplete(Hole[] h){
                for(Hole hole : h){
                    if(hole.isUp()){
                        return false;
                    }
                }
                return true;
            }
        });

        timerThread.start();
        for (Hole h : holeThreads) {
            h.setGame(this);
            h.start();
        }
        queueChar.start();
    }


    public void gameEnd(){
        gameRunning = false;
        components[2].setEnabled(true);
        components[3].setEnabled(true);

    }

    public Character[] getCharacters(){
        return characters;
    }

    public synchronized void addPoints(int num){
        score += num;
        scoreField.setText("" + score);
    }
    public synchronized void addTime(int num){
        time += num;
        scoreField.setText("" + score);
    }
    public synchronized int getTime(){
        return time;
    }

    public int getScore(){
        return score;
    }
}

