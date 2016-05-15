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
public class Ammar implements Character {
    //GUI Elements
    private GamePlay game;
    private JFrame frame;
    private HoleSprite hole;
    //Audio info
    private final String soundPath = "res/aud/pop.wav";
    //Sprites
    private final String imgPath = "res/img/ammar.png";
    private final BufferedImage[] spriteFrames;
    private final int numFrames = 10;
    private final int fheight = 10;
    private final int fwidth = 20;
    //GamePlay Info
    private final int points = 10;
    private final int timeUp = 1000; //Time in Milliseconds
    private final int ranking = 1;

    public Ammar(GamePlay g, JFrame fr){
        game = g;
        frame = fr;
        spriteFrames = new BufferedImage[numFrames];
        BufferedImage pic;
        try {
            pic = ImageIO.read(new File(getImagePath()));

        }catch (IOException i){
            pic = null;
            System.out.println(i);
        }
        int rows = pic.getHeight()/fheight;
        int cols = pic.getWidth()/fwidth;

        System.out.println(rows + " and " + cols);
        int pos = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(!(row == rows - 1 && col > (numFrames%cols)-1)){
                    spriteFrames[0] = pic.getSubimage(col*fheight, row*fheight, fwidth, fheight);
                }
            }
        }
    }

    public void pop(Thread t, HoleSprite h){
        WhackTools.playSound(getSoundPath());
    }

    public void pop(Thread t){
        frame.setVisible(true);
        panel.setText(" POP ");

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
