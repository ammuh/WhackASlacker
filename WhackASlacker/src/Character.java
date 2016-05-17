import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract class for every character to extend and implement given methods.
 */
public abstract class Character {
    // Global variables for character class.
    private static int holeWidth = 120;
    private static int holeHeight = 150;
    private static GamePlay game;

    /**
     * Constructor - Goes through pop animation for character.
     * @param hole Hole that will be popped with character.
     */
    public void pop(Hole hole){
        WhackTools.playSound(getSoundPath());
        MouseAdapter m = new MouseAdapter()
        {
            boolean slackerHit = false;
            public void mouseClicked(MouseEvent e)
            {
                if(!slackerHit) {
                    reward(game);
                    slackerHit = true;
                    hit(hole);
                    hole.setPopStatus(false);
                }
            }
        };
        hole.setPopStatus(true);
        aniUp(hole);
        hole.getLabel().addMouseListener(m);



        try {
            hole.sleep(getTimeUp());
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }
        hole.getLabel().removeMouseListener(m);
        aniDown(hole);
        hole.setPopStatus(false);
    }

    /**
     * Helper method used to obtain the given frames from a sprite sheet.
     * @param pic Buffered sprite sheet.
     * @param numFrames Number of frames in the sprite sheet.
     * @param fheight frame height
     * @param fwidth frame width
     * @return Array of frames for animation.
     */
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
    public abstract void hit(Hole h);
    public abstract void aniDown(Hole h);
    public abstract String getSoundPath();
    public abstract String getSpritePath();
    public abstract int getPointValue();
    public abstract int getTimeUp();
    public abstract int getRanking();
}
