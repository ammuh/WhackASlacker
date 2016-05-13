import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ammu on 5/12/16.
 */
public abstract class WhackTools {

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

    private void playSound(String path) {
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
