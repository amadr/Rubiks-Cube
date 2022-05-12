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
	private Square mSquare;
	private Cube mCube;
	private Vector3D mVector1 = new Vector3D(5,5,5);
	private Vector3D mVector2 = new Vector3D(100,5,5);
	private Vector3D mVector3 = new Vector3D(100,100,5);
	private Vector3D mVector4 = new Vector3D(5,100,5);
	private Perspective mPerspective;
	private RotMatrix mRotMatrix;
	
	public RubikComp() {
		mSquare = new Square(mVector1, mVector2, mVector3, mVector4, new Vector3D(1,0,0));
		
		mCube = new Cube(new Vector3D(200, 200, 0));
		mPerspective = new Perspective();
    	setPreferredSize (new Dimension (600, 600));
    	Thread th = new Thread (this);
        th.start ();
    }
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();

		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, w, h);
		
        int[] x = {(int) mVector1.getX(), (int) mVector2.getX(), (int) mVector3.getX(), (int) mVector4.getX()};
        int[] y = {(int) mVector1.getY(), (int) mVector2.getY(), (int) mVector3.getY(), (int) mVector4.getY()};

        //Shape myVectorShape = new Polygon(x, y, 4);
        //g2.fill(myVectorShape);
        
        //mPerspective.paintSquare(g2, mSquare);
        
        mPerspective.paintCube(g2, mCube);
        //g2.drawPolygon(x, y, 4);
	}

	@Override
	public void run ()
    {
		while (true)
        {
			repaint();
			
			try { 
					Thread.sleep(10); 
				} 
			catch (InterruptedException e) {}
        }
	}
	
}
