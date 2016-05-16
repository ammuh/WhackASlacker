import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ammu on 5/8/16.
 */
public class Hole extends Thread{
	JPanel hPanel;
    JLabel spriteLabel;
    JFrame mframe;
    GamePlay game;
    HoleSprite sprite;
    private Character[] characters;
    private volatile boolean isUp = false;
    private volatile ArrayList<Character> queue = new ArrayList<Character>();

    private final String deskPath = "src/res/img/desk.png";

    public Hole(JFrame main, GamePlay g){
        hPanel = new JPanel();
        spriteLabel = WhackTools.getImageLabel(deskPath, 120, 150);
        hPanel.add(spriteLabel);
        mframe = main;
        game = g;
        characters = g.getCharacters();
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

    public boolean isUp(){
        return isUp;
    }
    public void setPopStatus(boolean b){
        isUp = b;
    }
    public JPanel gethPanel(){
        return this.hPanel;
    }
    public JLabel getLabel(){
        return this.spriteLabel;
    }
}