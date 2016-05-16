import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ammu on 5/7/16.
 */
public abstract class Character {

    private static int holeWidth = 120;
    private static int holeHeight = 150;
    private static GamePlay game;


    public void pop(Hole hole){
        //WhackTools.playSound(getSoundPath());
        //Mouse Adapter handles hit on
        /*MouseAdapter m = new MouseAdapter()
        {
            boolean slackerHit = false;
            public void mouseClicked(MouseEvent e)
            {
                if(!slackerHit) {
                    reward(game);
                    slackerHit = true;
                    aniDown(hole);
                    setPopStatus(false);
                }
            }
        };*/

        aniUp(hole);
        setPopStatus(true);
        //hole.getLabel().addMouseListener(m);
        try {
            hole.sleep(getTimeUp());
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }
        //hole.getLabel().removeMouseListener(m);
        aniDown(hole);
        setPopStatus(false);

        try {
            hole.sleep((int)(Math.random()*5000)+4000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public ImageIcon[] getBufferedFrames(BufferedImage pic, int numFrames, int fheight, int fwidth){
        ImageIcon[] b = new ImageIcon[numFrames];
        int rows = pic.getHeight()/fheight;
        int cols = pic.getWidth()/fwidth;
        System.out.println(rows + " and " + cols);
        int pos = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(!(row == rows - 1 && col > (numFrames%cols)-1)){
                    b[pos] = new ImageIcon(WhackTools.scaleImage(holeWidth, holeHeight, pic.getSubimage(col*fwidth, row*fheight, fwidth, fheight)));
                    pos++;
                }
            }
        }
        return b;
    }

    public static void setHoleWH(int w, int h){
        if(w > 0){
            holeWidth = w;
        }
        if(h > 0){
            holeHeight = h;
        }
    }
    public static void setGame(GamePlay g){
        game = g;
    }

    public abstract void reward(GamePlay g);
    public abstract void aniUp(Hole h);
    public abstract void aniDown(Hole h);
    public abstract void setPopStatus(Boolean b);
    public abstract String getSoundPath();
    public abstract String getSpritePath();
    public abstract int getPointValue();
    public abstract int getTimeUp();
    public abstract int getRanking();
}
