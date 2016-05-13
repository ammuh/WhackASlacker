import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.RenderingHints;

public class ImgUtils {

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

}