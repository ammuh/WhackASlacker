import javax.swing.*;

/**
 * Created by ammu on 5/9/16.
 */
public class Ammar implements Character {

    private GamePlay game;
    private JFrame frame;
    private JLabel panel;

    public Ammar(GamePlay g, JFrame f, JLabel p){
        game = g;
        frame = f;
        panel = p;
    }

    @Override
    public String getSoundPath() {
        return "res/aud/ammu.mp3";
    }

    @Override
    public String getImagePath() {
        return "res/img/ammu.png";
    }

    @Override
    public int getPointValue() {
        return 10;
    }

    @Override
    public int getTimeUp() {
        return 2000;
    }

    @Override
    public int getRanking() {
        return 1;
    }
}
