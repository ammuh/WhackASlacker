import javax.swing.*;

/**
 * Created by ammu on 5/8/16.
 */
public class Hole extends Thread{
     JLabel hPanel;
     JFrame mframe;
     GamePlay game;
    private Character[] characters;
    public Hole(JLabel hole, JFrame main, GamePlay g){

        hPanel = hole;
        mframe = main;
        game = g;
        characters = new Character[1];
        characters[0]  = new Ammar(game, mframe, hPanel);
    }

    public void run(){
        try {
            this.sleep((int)(Math.random()*3000));
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }
        while(game.getTime() > -1){

                Character c = characters[(int)Math.random()*characters.length];
                c.pop(this);
                //hPanel = new JPanel();
        }
    }

    public void loop(){

    }
    private void hitSound(Character c){

    }

}
