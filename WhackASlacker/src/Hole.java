import javax.swing.*;

/**
 * Created by ammu on 5/8/16.
 */
public class Hole extends Thread{
	JPanel hPanel;
    JLabel spriteLabel;
    JFrame mframe;
    GamePlay game;
    HoleSprite sprite;
    private Character[] characters;

    private final String deskPath = "src/res/img/desk.png";

    public Hole(JFrame main, GamePlay g){
        hPanel = new JPanel();
        spriteLabel = WhackTools.getImageLabel(deskPath, 120, 150);
        hPanel.add(spriteLabel);
        mframe = main;
        game = g;
        //characters = g.getCharacters();
    }

    public void run(){
        try {
            this.sleep((int)(Math.random()*3000));
        } catch (InterruptedException e) {
            // Should not happen
            throw new AssertionError(e);
        }
        while(game.getTime() > -1){

                //Character c = characters[(int)Math.random()*characters.length];
                //c.pop(this);
        }
    }

    public JPanel gethPanel(){
        return this.hPanel;
    }
    public JLabel getLabel(){
        return this.spriteLabel;
    }
}