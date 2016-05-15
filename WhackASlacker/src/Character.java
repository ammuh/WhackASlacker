import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ammu on 5/7/16.
 */
public abstract class Character {

    public static int holeWidth = 0;
    public static int holeHeight = 0;


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

    public void setHoleWH(int w, int h){
        holeHeight = h;
        holeWidth = w;
    }

    public abstract String getSoundPath();
    public abstract String getSpritePath();
    public abstract int getPointValue();
    public abstract int getTimeUp();
    public abstract int getRanking();
    public abstract void pop(Thread t);
}
