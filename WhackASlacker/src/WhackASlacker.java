/**
 * Created by ammu on 5/7/16.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WhackASlacker{
    public JFrame mainFrame;

    public static void main(String args[]){
        new WhackASlacker();
    }
    public WhackASlacker(){

        mainFrame = new JFrame("Whack A Slacker");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainScreenSet();
    }
    private void mainScreenSet(){
        JPanel pane1 = new JPanel();
        JButton button = new JButton("Start");
        pane1.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startGame();
            }
        });
        mainFrame.setContentPane(pane1);
        mainFrame.setVisible(true);
    }
    private void startGame(){
        new GamePlay(this.mainFrame);
    }
    private void endPanelSet(){

    }
}
