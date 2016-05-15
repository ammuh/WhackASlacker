import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
/**
 * Created by ammu on 5/12/16.
 */
public abstract class WhackTools {

    public static BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
        BufferedImage bi = null;
        try {
            ImageIcon ii = new ImageIcon(filename);//path to image
            bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bi;
    }

    public static BufferedImage scaleImage(int WIDTH, int HEIGHT, BufferedImage img) {
        BufferedImage bi = null;
        try {
            bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bi;
    }

    public static BufferedImage makeTransparentImage(BufferedImage br) {
        for (int i = 0; i < br.getHeight(); i++) {
            for (int j = 0; j < br.getWidth(); j++) {
                Color c = new Color(br.getRGB(j, i));
                int r = c.getRed();
                int b = c.getBlue();
                int g = c.getGreen();
                if ((r == 255 && b == 255 && g == 255)) {
                    System.out.println("r g b " + r + g + b);
                    br.setRGB(j, i, 0xFF000000);
                }
            }
        }
        return br;
    }

    public static JLabel getImageLabel(String path){
        try {
            JLabel picLabel;
            BufferedImage pic = ImageIO.read(new File(path));
            picLabel = new JLabel(new ImageIcon(pic));
            return picLabel;
        }catch (IOException i){
            System.out.println("ERRORRRRR");
            return null;
        }
    }

    public static JLabel getImageLabel(String path, int w, int h){
        JLabel picLabel;
        BufferedImage pic = WhackTools.scaleImage(w, h, path);
        picLabel = new JLabel(new ImageIcon(pic));
        return picLabel;

    }

    public static void playSound(String path) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
                    AudioFormat format = inputStream.getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    Clip clip = (Clip)AudioSystem.getLine(info);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
