import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Created by ammu on 5/9/16.;
 */
public class Ammar extends Character {
    //GUI Elements
    private GamePlay game;
    private JFrame frame;
    private HoleSprite hole;
    //Audio info
    private final String soundPath = "res/aud/pop.wav";
    //Sprites
    private final String imgPath = "res/img/ammarSprites.png";
    private final BufferedImage[] spriteFrames;
    private final int numFrames = 10;
    private final int fheight = 10;
    private final int fwidth = 20;
    //GamePlay Info
    private final int points = 10;
    private final int timeUp = 1000; //Time in Milliseconds
    private final int ranking = 1;
    private boolean isUp =false;

    public Ammar(GamePlay g, JFrame fr){
        game = g;
        frame = fr;
        BufferedImage img;
        try{
            img = ImageIO.read(new File(getImagePath()));
        }catch (IOException i){
            img = null;
            i.printStackTrace();
        }
        spriteFrames = getBufferedFrames(img, numFrames, fheight, fwidth);
    }

    @Override
    public void reward(GamePlay g) {
        g.addPoints(points);
    }

    @Override
    public void aniUp(Hole h) {
        JLabel l = h.getLabel();

    }

    @Override
    public void aniDown(Hole h) {
        JLabel l = h.getLabel();

    }

    @Override
    public void setPopStatus(Boolean b) {
        isUp = b;
    }


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
