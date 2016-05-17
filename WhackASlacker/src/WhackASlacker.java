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
import java.util.ArrayList;
import java.util.*;

public class WhackASlacker{

    public JFrame mainFrame; //Frame or window that user will interact with.
    public ArrayList<GamePlay> games = new ArrayList<GamePlay>();


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

        JButton button = new JButton("Start");
        info.add(button);
        info.add(Box.createHorizontalStrut(50));

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

        info.add(Box.createHorizontalStrut(10));
        JButton button2 = new JButton("Leaderboard");
        info.add(button2);

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
        Component[] c = {timeField, scoreField, button, button2};
        //Attach event listener, therefore when clicked, the game can start
        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                GamePlay g = new GamePlay(w, holeThreads, c);
                games.add(g);
                g.begin();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                leaderBoardScreen();
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
     * Brings up leaderboard screen of highest scored games in the last few games.
     */
    public void leaderBoardScreen(){
        JPanel j = new JPanel();
        j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
        j.add(new JLabel("LEADERBOARDS"));
        ArrayList<GamePlay> gTemp = new ArrayList<GamePlay>(getGames());
        gTemp =  mergeSort(gTemp);

        int rank = 1;
        Iterator<GamePlay> giter = gTemp.iterator();
        while(giter.hasNext()){
            j.add(new JLabel(rank + ". " + giter.next().getScore() + " points"));
            rank++;
        }
        JButton button = new JButton("Back");
        j.add(button);

        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                mainScreenSet();
            }
        });

        j.setBackground(Color.WHITE);
        mainFrame.setContentPane(j);
        mainFrame.setVisible(true);

    }
    /**
     * Retrieve frame used by game.
     * @return main game frame
     */
    public JFrame getFrame(){
        return this.mainFrame;
    }
    public ArrayList<GamePlay> getGames(){
        return games;
    }

    /**
     * Recursive sorting algorithim used to sort Games based on points.
     * @param whole whole Array
     * @return sorted Array
     */
    private ArrayList<GamePlay> mergeSort(ArrayList<GamePlay> whole) {
        if(whole.size() == 0){
            return whole;
        }
        ArrayList<GamePlay> left = new ArrayList<GamePlay>();
        ArrayList<GamePlay> right = new ArrayList<GamePlay>();
        int center;

        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                left.add(whole.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i=center; i<whole.size(); i++) {
                right.add(whole.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left  = mergeSort(left);
            right = mergeSort(right);

            // Merge the results back together.
            merge(left, right, whole);
        }
        return whole;
    }

    /**
     * helper method to merge two arrays into one.
     * @param left left index
     * @param right right index
     * @param whole whole Array
     */
    private void merge(ArrayList<GamePlay> left, ArrayList<GamePlay> right, ArrayList<GamePlay> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( left.get(leftIndex).getScore() > right.get(rightIndex).getScore()) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<GamePlay> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }
}
