package RubikFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 */

public class RubikFrame extends JFrame {
	//private RubiksCube mRubikCube;
//	private RubikComp mRubikComp;
	
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
	     
	     Vector3D v1 = new Vector3D (1,2,3);   
	     Vector3D v2 = new Vector3D(4,5,6);
	     
	     System.out.println(v2.getX());
	     
	     v2  = v1; 
	     
	     System.out.println(v2.getX());
	     
	     
	}
}
