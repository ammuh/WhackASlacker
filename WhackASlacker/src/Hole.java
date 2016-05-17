import javax.swing.*;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Hole class for each whackamole.
 */

public class Hole extends Thread{
	JPanel hPanel;
    JLabel spriteLabel;
    JFrame mframe;
    GamePlay game;
    private volatile boolean isUp = false;
    private volatile ArrayList<Character> queue = new ArrayList<Character>();
    private volatile ArrayList<Integer> timeQueue = new ArrayList<Integer>();

    /**
     * Constructor - Creates a Hole thread to recieve character objects.
     * @param main frame used in main UI
     * @param l Label that will hold the sprite
     */
    public Hole(JFrame main, JLabel l){
        spriteLabel = l;
        mframe = main;
    }

    /**
     * Checks to see if there are any items to animate in the queue.
     */
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

    /**
     * Sets the GamePlay being used.
     * @param g GamePlay
     */
    public void setGame(GamePlay g){
        game = g;
    }

    /**
     * Returns size of queue of current queue synchronously.
     * @return
     */
    public int queueSize(){
        synchronized (queue){
            return queue.size();
        }
    }

    /**
     * Queues objects synchronously.
     * @param c
     */
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