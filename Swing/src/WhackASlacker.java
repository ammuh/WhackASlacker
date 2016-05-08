/**
 * Created by ammu on 5/7/16.
 */

import java.awt.*;
import javax.swing.*;

public class WhackASlacker extends JFrame{

    public static void main(String args[]){
        new WhackASlacker();
    }

    public WhackASlacker(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        setTitle("WhackASlacker");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
