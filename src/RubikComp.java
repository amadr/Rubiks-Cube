import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

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

    	if(rotate_) {
        	mRubiksCube.rotate_();
        	
            rotate_ = false;
        }
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	    
	    char key_char = e.getKeyChar();

	    //x, y, z for rotation of complete RubiksCube
	    // left + ... key implentieren??
	    if (key_char == 'l') {
	        mRubiksCube.rotateLeftX(1);
	    }
	    if (key_char == 'L') {
	        mRubiksCube.rotateLeftX(-1);
	    }
	    if (key_char == 'x') {
	        mRubiksCube.rotateMiddleX(1);
	    }
	    if (key_char == 'X') {
	        mRubiksCube.rotateMiddleX(-1);
	    }
	    if (key_char == 'r') {
	        mRubiksCube.rotateRightX(1);
	    }
	    if (key_char == 'R') {
	        mRubiksCube.rotateRightX(-1);
	    }


	    if (key_char == 'u') {
	        mRubiksCube.rotateUpY(1);
	    }
	    if (key_char == 'U') {
	        mRubiksCube.rotateUpY(-1);
	    }
	    if (key_char == 'y') {
	        mRubiksCube.rotateMiddleY(1);
	    }
	    if (key_char == 'Y') {
	        mRubiksCube.rotateMiddleY(-1);
	    }
	    if (key_char == 'd') {
	        mRubiksCube.rotateDownY(1);
	    }
	    if (key_char == 'D') {
	        mRubiksCube.rotateDownY(-1);
	    }
	    
	    if (key_char == 'f') {
	        mRubiksCube.rotateFrontZ(1);
	    }
	    if (key_char == 'F') {
	        mRubiksCube.rotateFrontZ(-1);
	    }
	    if (key_char == 'z') {
	        mRubiksCube.rotateMiddleZ(1);
	    }
	    if (key_char == 'Z') {
	        mRubiksCube.rotateMiddleZ(-1);
	    }
	    if (key_char == 'b') {
	        mRubiksCube.rotateBackZ(1);
	    }
	    if (key_char == 'B') {
	        mRubiksCube.rotateBackZ(-1);
	    }
	    
	    if (key_char == '0') {
	        mRubiksCube.reset();
	    }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
