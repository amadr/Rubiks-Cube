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
	//private Cube mCube;
	private Perspective mPerspective;
	
    private Boolean rotate = true;
	
	public RubikComp() {		
		//mCube = new Cube(new Vector3D(0, 0, 100));
		
		mRubiksCube = new RubiksCube();
		
		mRubiksCube.copyCubeList();
		
		
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
        
        //mPerspective.paintCube(g2, mCube);
        mPerspective.paintRubiksCube(g2, mRubiksCube);
        
        if(rotate) {
        	mRubiksCube.startRotation();
        	
            rotate = false;
        }
    	mRubiksCube.rotatePosZ();

	}

	@Override
	public void run() {
		while (true) {
			repaint();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
	
}
