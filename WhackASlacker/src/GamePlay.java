/**
 * Created by ammu on 5/7/16.
 */


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePlay{
    //Game Stats
    private volatile int score;
    private volatile int time;
    private JFrame frame;
    private boolean gameRunning;
    private JTextArea timeField;
    private JTextArea scoreField;
    private Hole[] holeThreads;

    private final Character[] characters;

    public GamePlay(WhackASlacker w, Hole[] threads, Component[] components){
        //Initialize game vars and essential UI vars
        frame = w.getFrame();
        score = 0;
        time = 100;
        //Character Manifest
        characters = new Character[1];
        characters[0]  = new Ammar(this, frame);
        Character.setHoleWH(120,150);
        Character.setGame(this);
        //UI Elements
        holeThreads = threads;
        timeField = (JTextArea) components[0];
        scoreField = (JTextArea) components[1];
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
    }
    public synchronized int getTime(){
        return time;
    }

}

