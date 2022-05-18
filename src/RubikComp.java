import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

/**
 * 
 */

public class RubikComp extends JComponent implements Runnable {
	private RubiksCube mRubiksCube;
	private Cube mCube;
	private Perspective mPerspective;
	private RotMatrix mRotMatrix;
	
    private Boolean rotate = true;
	
	public RubikComp() {		
		mCube = new Cube(new Vector3D(0, 0, 100));
		
		mRubiksCube = new RubiksCube();
		
		mPerspective = new Perspective();
    	setPreferredSize (new Dimension (1000, 800));
    	Thread th = new Thread (this);
        th.start ();
    }
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();

        if(rotate) {
            //mCube.rotateCube();
            //rotate = false;
        	
        	mRubiksCube.rotateCube();
        }
        //mPerspective.paintCube(g2, mCube);
        
        mPerspective.paintRubiksCube(g2, mRubiksCube);
	}

	@Override
	public void run ()
    {
		while (true)
        {
			repaint();
			
			try { 
					Thread.sleep(10); // time to sleep -> 10?
				} 
			catch (InterruptedException e) {}
        }
	}
	
}
