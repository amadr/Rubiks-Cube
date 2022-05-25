import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * TODO
 * class description
 */

public class RubikFrame extends JFrame {
	// private RubiksCube mRubikCube;
	private RubikComp mRubikComp;

	public static void main(String[] args) {
		JFrame f = new JFrame("Rubik's Cube");
		f.setLayout(new FlowLayout());
		
		RubikComp rComp = new RubikComp();

//		JPanel input = new JPanel();
//		input.setLayout(new FlowLayout());
//
//		JLabel label = new JLabel("LABEL");
//		input.add(label);
//
//		f.add(input);
		f.add(rComp);
		f.add(rComp.mScrambleButton);
		f.addKeyListener(rComp);

		new Thread(rComp).start();
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
