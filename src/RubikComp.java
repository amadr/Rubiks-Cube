import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

/**
 * 
 */

public class RubikComp extends JComponent implements Runnable {
	//private RubiksCube mCube;
	private Cube mCube;
	private Vector3D mVector1 = new Vector3D(5,5,5);
	private Vector3D mVector2 = new Vector3D(100,5,5);
	private Vector3D mVector3 = new Vector3D(100,100,5);
	private Vector3D mVector4 = new Vector3D(5,100,5);
	private Perspective mPerspective;
	private RotMatrix mRotMatrix;
	
    private Boolean rotate = true;
	
	public RubikComp() {		
		mCube = new Cube(new Vector3D(400, 400, 100));
		
		mPerspective = new Perspective();
    	setPreferredSize (new Dimension (800, 800));
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
            mCube.rotateCube();
            //rotate = false;
        }
        mPerspective.paintCube(g2, mCube);
	}

	@Override
	public void run ()
    {
		while (true)
        {
			repaint();
			
			try { 
					Thread.sleep(80); // time to sleep -> 10?
				} 
			catch (InterruptedException e) {}
        }
	}
	
}
