import java.awt.Dimension;

import javax.swing.JFrame;

import ihm.VueChessquito;

public class App {
	
	public static void main(String[] args) {
		final int size = 4;
		
		VueChessquito vue = new VueChessquito(size);
		JFrame frame = new JFrame();
		frame.setTitle("Chessquito");
		frame.add(vue);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(700, 700);
		frame.setMinimumSize(new Dimension(500, 500));
	}
	
	
}
