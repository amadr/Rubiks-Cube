import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author amad
 *
 */
public class RubikFrame extends JFrame {
	private RubiksCube mRubikCube;
	private RubikComp mRubikComp;
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame ("Rubik's Cube");
	    f.setLayout(new BorderLayout());
	    
	    JPanel input = new JPanel();
	     input.setLayout(new GridLayout(5,3));
	      
	     //JLabel f�r die Laenge
	     JLabel length_label = new JLabel("Laenge [m]:");
	     
	     f.add(input, BorderLayout.NORTH);
	     
	     //new Thread(g).start();
	     f.pack();
	     f.setVisible(true);
	     f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}
