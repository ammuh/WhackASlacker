/**
 * WhackASlacker
 * By: Ammar Husain, Auni Bagchi, Karthik Vangati, Kenneth Khuu, Omer Saeed, Ketan Kapre
 * Period 4
 *
 * As a part of our Computer Science final, we created a Whack A Mole game using Java Swing, MultiThreaded Programming, and Sprites.
 * See user manual for game rules and full explanation of game.
 */


//Classes for implementing event driven programming.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI Elements
import javax.swing.*;
import java.awt.*;

public class WhackASlacker{

    public JFrame mainFrame; //Frame or window that user will interact with.

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
        mainScreenSet();
    }

    /**
     * Loads up startup screen.
     */
    public void mainScreenSet(){
        //Add button to panel
        JPanel pane1 = new JPanel();
        JButton button = new JButton("Start");
        pane1.add(button);

        //Attach event listener, therefore when clicked, the game can start
        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                startGame();
            }
        });

        mainFrame.setContentPane(pane1);
        mainFrame.setVisible(true);
    }

    /**
     * Starts a game.
     */
    private void startGame(){
        GamePlay g = new GamePlay(this);
        g.begin();
    }

    /**
     * Loads up end screen.
     */
    public void endPanelSet(){

    }

    /**
     * Retrieve frame used by game.
     * @return main game frame
     */
    public JFrame getFrame(){
        return this.mainFrame;
    }
}
