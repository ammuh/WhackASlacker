import javax.swing.*;

/**
 * Created by ammu on 5/8/16.
 */
public class Hole extends Thread{
    private JLabel hPanel;
    private JFrame mframe;
    private GamePlay game;
    private Character[] characters = {};
    public Hole(JLabel hole, JFrame main, GamePlay g){
        hPanel = hole;
        mframe = main;
        game = g;
    }

    public void run(){
        while(game.time > -1){
            try {
                this.sleep((int)Math.random()*3000 +2);
            } catch (InterruptedException e) {
                // Should not happen
                throw new AssertionError(e);
            }
            synchronized (hPanel){
                this.hPanel.setText(" POP ");

                try {
                    this.sleep(2000);
                } catch (InterruptedException e) {
                    // Should not happen
                    throw new AssertionError(e);
                }
                this.hPanel.setText("_______");

            }



        /*
        Character c = characters[(int)Math.random()*characters.length];
        //hPanel = new JPanel();
        mframe.setVisible(true);*/

        }
    }

    public void loop(){

    }
    private void hitSound(Character c){

    }

}
