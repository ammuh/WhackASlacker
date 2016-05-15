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

    private static int holeWidth = 0;
    private static int holeHeight = 0;

    public void pop(Thread t, HoleSprite h){
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
        h.addMouseListener(m);
        try {
            t.sleep(getTimeUp());
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }
        h.removeMouseListener(m);
        try {
            t.sleep((int)(Math.random()*3000));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public BufferedImage[] getBufferedFrames(BufferedImage pic, int numFrames, int fheight, int fwidth){
        BufferedImage[] b = new BufferedImage[numFrames];
        int rows = pic.getHeight()/fheight;
        int cols = pic.getWidth()/fwidth;
        System.out.println(rows + " and " + cols);
        int pos = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(!(row == rows - 1 && col > (numFrames%cols)-1)){
                    b[pos] = WhackTools.scaleImage( holeWidth, holeHeight, pic.getSubimage(col*fheight, row*fheight, fwidth, fheight));
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

    public abstract String getSoundPath();
    public abstract String getSpritePath();
    public abstract int getPointValue();
    public abstract int getTimeUp();
    public abstract int getRanking();
}
