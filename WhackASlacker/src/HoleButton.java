import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HoleButton extends JButton implements ActionListener{
	
	private ImageIcon i = new ImageIcon("src/res/img/desk.png");
	private GamePlay game;
	
	public HoleButton(GamePlay g){
		this.addActionListener(this);
		this.setIcon(i);
		game = g;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("HoleButtonClicked");
		
	}

}
