import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ammu on 5/14/16.
 */
public class HoleSprite extends JLabel{
    private int height;
    private int width;
    private int x;
    private int y;
    private final String restingImg = "src/res/img/desk.png";

    public static void main(String args[]){

    }


    public HoleSprite(int pwidth, int pheight){
        super();
        width = pwidth;
        height = pheight;
        this.setPreferredSize(new Dimension(width, height));

        try{
            BufferedImage pic = ImageIO.read(new File(restingImg));
            this.setIcon(new ImageIcon(pic));
        }catch(IOException i){
            System.out.println("Image could not be loaded.");
        }


    }

    public void loadCharacter(){


    }

    public void animate(Character c, int fh, int fw){
        BufferedImage pic;
        try{
            pic = ImageIO.read(new File(c.getSpritePath()));

        }catch (IOException i){
            System.out.println("Couldn't Load Sprite Path");
            pic = null;
        }

        int rows = pic.getHeight()/fh;
        int cols = pic.getWidth()/fw;

        HoleSprite h = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int row = 0; row < rows; row++){
                    for(int col = 0; col < cols; col++){
                        h.setIcon(new ImageIcon());
                        try{
                            Thread.sleep(300);
                        }catch (InterruptedException i){System.out.println("Thread stopped");}

                    }
                }
            }
        }).start();

    }

}
