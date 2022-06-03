import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class generates a JFrame containing the RubikComp object and the
 * buttons.
 */

public class RubikFrame extends JFrame {
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Rubik's Cube");
		f.setLayout(new FlowLayout());

		RubikComp rComp = new RubikComp();
		f.add(rComp);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1));
		buttons.add(rComp.mScrambleButton);
		buttons.add(rComp.mPseudoSolveButton);
		buttons.setBackground(Color.GRAY);
		f.add(buttons);

		f.addKeyListener(rComp);

		f.getContentPane().setBackground(Color.GRAY);

		new Thread(rComp).start();
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
