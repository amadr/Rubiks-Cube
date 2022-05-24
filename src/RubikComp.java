import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;

/**
 * 
 */

public class RubikComp extends JComponent implements Runnable, KeyListener {
	private RubiksCube mRubiksCube;
	//private Cube mCube;
	private Perspective mPerspective;
	
    private Boolean rotate = true;
    private Boolean rotate_ = true;
    
	public RubikComp() {		
		//mCube = new Cube(new Vector3D(0, 0, 100));
		
		mRubiksCube = new RubiksCube();
		mPerspective = new Perspective();
		
		
    	setPreferredSize (new Dimension (1000, 800));
    	addKeyListener(this);
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
    	
		//mRubiksCube.rotate_();
		

        //AffineTransform at = new AffineTransform();
        //at.rotate(Math.PI/8);
        //at.rotate(Math.PI/8, 100, 100);
        
        //g2.setTransform(at);
        
        mPerspective.paintRubiksCube(g2, mRubiksCube);
        
        //mPerspective.paintAxis(g2, mRubiksCube.getAxis());

//    	if(rotate_) {
//        	mRubiksCube.rotate_(Math.PI/30, 'y');
//        	mRubiksCube.rotate_(Math.PI/30, 'z');
//        	
//            rotate_ = false;
//        }
//    	
//        if(rotate) {
//        	mRubiksCube.startRotation();
//        	
//            //rotate = false;
//        }
    	//mRubiksCube.rotatePosX();
    	
	}

	@Override
	public void run() {
		while (true) {
			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	    
	    int key = e.getKeyCode();

	    //x, y, z for rotation of complete RubiksCube
	    // left + ... key implentieren??
	    if (key == 'l') {
	        mRubiksCube.rotateLeftX(1);
	    }
	    if (key == 'L') {
	        mRubiksCube.rotateLeftX(-1);
	    }
	    if (key == 'x') {
	        mRubiksCube.rotateMiddleX(1);
	    }
	    if (key == 'X') {
	        mRubiksCube.rotateMiddleX(-1);
	    }
	    if (key == 'r') {
	        mRubiksCube.rotateRightX(1);
	    }
	    if (key == 'R') {
	        mRubiksCube.rotateRightX(-1);
	    }


	    if (key == 'u') {
	        mRubiksCube.rotateUpY(1);
	    }
	    if (key == 'U') {
	        mRubiksCube.rotateUpY(-1);
	    }
	    if (key == 'y') {
	        mRubiksCube.rotateMiddleY(1);
	    }
	    if (key == 'Y') {
	        mRubiksCube.rotateMiddleY(-1);
	    }
	    if (key == 'd') {
	        mRubiksCube.rotateDownY(1);
	    }
	    if (key == 'D') {
	        mRubiksCube.rotateDownY(-1);
	    }
	    
	    if (key == 'f') {
	        mRubiksCube.rotateFrontZ(1);
	    }
	    if (key == 'F') {
	        mRubiksCube.rotateFrontZ(-1);
	    }
	    if (key == 'z') {
	        mRubiksCube.rotateMiddleZ(1);
	    }
	    if (key == 'Z') {
	        mRubiksCube.rotateMiddleZ(-1);
	    }
	    if (key == 'b') {
	        mRubiksCube.rotateBackZ(1);
	    }
	    if (key == 'B') {
	        mRubiksCube.rotateBackZ(-1);
	    }
	    
	    if (key == '0') {
	        mRubiksCube.reset();
	    }
	    
	    // x-axis pos
	    if (key == KeyEvent.VK_UP) {
	    	mRubiksCube.rotate_(Math.PI/30, 'x');
	    }
	    // x-axis neg
	    if (key == KeyEvent.VK_DOWN) {
	    	mRubiksCube.rotate_(-Math.PI/30, 'x');
	    }
	    
	    // y-axis pos
	    if (key == KeyEvent.VK_LEFT) {
	    	mRubiksCube.rotate_(Math.PI/30, 'y');
	    }
	    // y-axis neg
	    if (key == KeyEvent.VK_RIGHT) {
	    	mRubiksCube.rotate_(-Math.PI/30, 'y');
	    }
	    
	    // z-axis pos
	    if (key == 'p') {
	    	mRubiksCube.rotate_(Math.PI/30, 'z');
	    }
	    // z-axis neg
	    if (key == 'P') {
	    	mRubiksCube.rotate_(-Math.PI/30, 'z');
	    }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
