import javax.swing.*;
import java.util.ArrayList;


public class Hole extends Thread{
	JPanel hPanel;
    JLabel spriteLabel;
    JFrame mframe;
    GamePlay game;
    private volatile boolean isUp = false;
    private volatile ArrayList<Character> queue = new ArrayList<Character>();
    private volatile ArrayList<Integer> timeQueue = new ArrayList<Integer>();

    public Hole(JFrame main, JLabel l){
        spriteLabel = l;
        mframe = main;
    }

    public void run(){
        while(game.getTime() > -1){
            if(queueSize() > 0){
                synchronized (queue){
                    Character c = queue.remove(0);
                    c.pop(this);
                }
            }
        }
    }
    public void setGame(GamePlay g){
        game = g;
    }
    public int queueSize(){
        synchronized (queue){
            return queue.size();
        }
    }
    public void queue(Character c){
        synchronized (queue) {
            queue.add(c);
        }

    }
    public synchronized boolean isUp(){

            return isUp;
    }
    public void setPopStatus(boolean b){
        isUp = b;
    }
    /*public JPanel gethPanel(){
        return this.hPanel;
    }*/

    public JLabel getLabel(){
        return this.spriteLabel;
    }
}