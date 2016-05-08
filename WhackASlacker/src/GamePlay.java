/**
 * Created by ammu on 5/7/16.
 */
import java.awt.*;
import javax.swing.*;

public class GamePlay{
    //Game Stats
    private volatile int score;
    private volatile int time;
    private JFrame frame;
    private boolean gameRunning;

    public GamePlay(JFrame f){
        frame = f;
        score = 0;
        time = 30;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel info = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(0,3));
        game.add(grid);
        panel.add(info);
        panel.add(game);

        info.add(new JLabel("Game Has Started"));
        for (int i = 0; i < 10; i++){
            grid.add(new JLabel("Element" + (i+1)));
        }

        frame.setContentPane(panel);
        frame.setVisible(true);
        gameRunning = true;
    }
    public void begin(){
       /* while(time > 30 && gameRunning){

        }*/


    }

}
