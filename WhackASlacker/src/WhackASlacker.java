/**
 * WhackASlacker
 * By: Ammar Husain, Auni Bagchi, Karthik Vangati, Kenneth Khuu, Omer Saeed, Ketan Kapre
 * Period 4
 *
 * As a part of our Computer Science final, we created a Whack A Mole game using Java Swing, MultiThreaded Programming, and Sprites.
 * See user manual for game rules and full explanation of game.
 */


//Classes for implementing event driven programming.
import com.sun.xml.internal.ws.api.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI Elements
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WhackASlacker{

    public JFrame mainFrame; //Frame or window that user will interact with.


    private final String deskPath = "src/res/img/desk.png";
    /**
     * Starts up game.
     * @param args Arguments passed in during execution.
     */
    public static void main(String args[]){
        new WhackASlacker();
    }

    /**
     * Constructor - Creates WhackASlacker object, which initializes the frame for GamePlay. Automatically starts beginning screen.
     */
    public WhackASlacker(){

        mainFrame = new JFrame("Whack A Slacker");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes java program when window closes
        mainFrame.setResizable(false); //Makes sure window always stays same size
        mainFrame.setVisible(true);
        mainFrame.getContentPane().setBackground( Color.WHITE );
        mainScreenSet();
    }

    /**
     * Loads up startup screen.
     */
    public void mainScreenSet(){

        JPanel info = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        //Info Layout
        info.setBorder(new EmptyBorder(30, 30, 30, 30));
        info.add(Box.createHorizontalStrut(10));

        JButton button = new JButton("Start");
        info.add(button);
        info.add(Box.createHorizontalStrut(100));

        JLabel timeLabel = new JLabel("Time Left:");
        info.add(timeLabel);

        JTextArea timeField = new JTextArea(1, 5);
        timeField.setEditable(false);
        info.add(timeField);
        timeField.setVisible(true);

        JLabel scoreLabel = new JLabel("Score:");
        info.add(scoreLabel);

        JTextArea scoreField = new JTextArea(1, 5);
        scoreField.setEditable(false);
        info.add(scoreField);
        scoreField.setVisible(true);

        JPanel game = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(3,4, 0, 0));

        Hole[] holeThreads = new Hole[12];

        for (int i = 0; i < 12; i++){
            JLabel l = WhackTools.getImageLabel(deskPath, 120, 150);
            Hole h = new Hole(mainFrame, l);
            JPanel j = new JPanel();
            j.setBackground(Color.WHITE);
            j.add(l);
            grid.add(j);
            holeThreads[i] = h;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        game.add(grid, BorderLayout.CENTER);
        panel.add(info);
        panel.add(game);

        WhackASlacker w = this;
        Component[] c = {timeField, scoreField};
        //Attach event listener, therefore when clicked, the game can start
        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                GamePlay g = new GamePlay(w, holeThreads, c);
                g.begin();
            }
        });
        info.setBackground(Color.WHITE);
        game.setBackground(Color.WHITE);
        grid.setBackground(Color.WHITE);

        mainFrame.setContentPane(panel);
        mainFrame.getContentPane().setBackground( Color.WHITE );
        mainFrame.setVisible(true);
    }

    /**
     * Retrieve frame used by game.
     * @return main game frame
     */
    public JFrame getFrame(){
        return this.mainFrame;
    }
}
