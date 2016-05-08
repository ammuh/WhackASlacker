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

        /*
        startButton.addActionListener(this);

        timeLabel = new JLabel("Time Left:");
        pane.add(timeLabel);

        timeArea = new JTextArea(1, 5);
        timeArea.setEditable(false);
        pane.add(timeArea);
        timeArea.setVisible(true);

        scoreLabel = new JLabel("Score:");
        pane.add(scoreLabel);

        scoreArea = new JTextArea(1, 5);
        scoreArea.setEditable(false);
        pane.add(scoreArea);
        scoreArea.setVisible(true);

        buttons = new JButton[35];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(OFF_STRING);
            buttons[i].setOpaque(true);
            buttons[i].setFont(font);
            buttons[i].setBackground(OFF_COLOR);
            pane.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        frame.setContentPane(pane);
        frame.setVisible(true);*/
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
        GamePlay g = new GamePlay(this.mainFrame);
    }
    private void endPanelSet(){

    }
}
