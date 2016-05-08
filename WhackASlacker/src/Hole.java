import javax.swing.*;

/**
 * Created by ammu on 5/8/16.
 */
public class Hole {
    private JPanel hPanel;
    private JFrame mframe;
    private Character[] characters = {new Ammar(), new Auni(), new Ani()};
    public Hole(JPanel hole, JFrame main){
        hPanel = hole;
        mframe = main;
    }
    public void loop(){
        while(true){
            pop();
            try {
                Thread.sleep((int)Math.random()*4000);
            } catch (InterruptedException e) {
                // Should not happen
                throw new AssertionError(e);
            }
        }
    }
    public void pop(){
        Character c = characters[(int)Math.random()*characters.length];
        hPanel = new JPanel();
        mframe.setVisible(true);

    }

    private void hitSound(Character c){
        
    }
}
