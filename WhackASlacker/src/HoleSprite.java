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
    private int sleepTime;

    public static void main(String args[]){
        JFrame mainFrame = new JFrame("Whack A Slacker");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes java program when window closes
        mainFrame.setResizable(false); //Makes sure window always stays same size
        mainFrame.setVisible(true);
        HoleSprite h = new HoleSprite(50, 50, 40);
        mainFrame.add(h);
        for(int i = 0; i < 15; i++){
            h.animate("src/res/img/runningGrant.png", 294, 165);
        }



    }


    public HoleSprite(int pwidth, int pheight, int fps){
        super();
        width = pwidth;
        height = pheight;
        this.setPreferredSize(new Dimension(width, height));
        sleepTime = 1000/fps;

        try{
            BufferedImage pic = ImageIO.read(new File(restingImg));
            this.setIcon(new ImageIcon(pic));
        }catch(IOException i){
            System.out.println("Image could not be loaded.");
        }


    }

    public void animate(String path, int fh, int fw){
        try{
            final BufferedImage pic;
            final int rows, cols;
            pic = ImageIO.read(new File(path));
            rows = pic.getHeight()/fh;
            cols = pic.getWidth()/fw;
            System.out.println(rows + " and " + cols);
            HoleSprite h = this;
            for(int row = 0; row < rows; row++){
                for(int col = 0; col < cols; col++){
                    try{
                        BufferedImage frame = WhackTools.scaleImage(width, height, pic.getSubimage(col*fw, row*fh, fw, fh));
                        synchronized (h){
                            h.setIcon(new ImageIcon(frame));
                            System.out.println("Frame " + (row* cols + col));
                            Thread.sleep(sleepTime);
                        }
                    }catch (InterruptedException i){
                        System.out.println("Thread stopped");
                    }
                }
            }
            /*new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();*/
        }catch (IOException i){
            System.out.println("Couldn't Load Sprite Path");
        }
    }

}
