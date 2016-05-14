import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;

/**
 * Created by ammu on 5/9/16.;
 */
public class Ammar implements Character {
    //GUI Elements
    private GamePlay game;
    private JFrame frame;
    private JLayeredPane panel;
    //Res info
    private final String soundPath = "res/aud/pop.wav";
    private final String imgPath = "res/img/ammar.png";
    //GamePlay Info
    private final int points = 10;
    private final int timeUp = 1000; //Time in Milliseconds
    private final int ranking = 1;

    public Ammar(GamePlay g, JFrame f, JLayeredPane p){
        game = g;
        frame = f;
        panel = p;
    }
    public void pop(Thread t){}

    /*public void pop(Thread t){
        frame.setVisible(true);
        panel.setText(" POP ");
        WhackTools.playSound(getSoundPath());
        MouseAdapter m = new MouseAdapter()
        {
            boolean slackerHit = false;
            public void mouseClicked(MouseEvent e)
            {
                if(!slackerHit) {
                    game.addPoints(getPointValue());
                    slackerHit = true;
                }
            }
        };
        panel.addMouseListener(m);

        try {
            t.sleep(getTimeUp());
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }

        panel.removeMouseListener(m);
        panel.setText("_______");

        try {
            t.sleep((int)(Math.random()*3000));
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }
    }*/



    @Override
    public String getSoundPath() {
        return soundPath;
    }

    public String getImagePath() {
        return imgPath;
    }
    @Override
    public int getPointValue() {
        return points;
    }
    @Override
    public int getTimeUp() {
        return timeUp;
    }
    @Override
    public int getRanking() {
        return ranking;
    }

    @Override
    public String getSpritePath() {
        return null;
    }
}
