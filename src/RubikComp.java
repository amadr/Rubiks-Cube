import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

/**
 * 
 */

/**
 * @author amad
 *
 */
public class RubikComp extends JComponent {
	private RubiksCube mCube;
	private Perspective mPerspective;
	private RotMatrix mRotMatrix;
	
	@Override
	public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        
        //Hintergrund wird mit einem grauen Rechteck aufgef�llt.
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, w, h);
    }
	
}
