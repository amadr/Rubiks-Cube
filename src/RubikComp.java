import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * TODO
 * class description
 */

public class RubikComp extends JComponent implements Runnable, KeyListener, ActionListener {
	private RubiksCube mRubiksCube;
	//private Cube mCube;
	private Perspective mPerspective;
	
    private Boolean rotate = true;
    private Boolean rotate_ = true;
    
	JButton mScrambleButton = new JButton("Scramble");
    
	public RubikComp() {		
		//mCube = new Cube(new Vector3D(0, 0, 100));
		
		mRubiksCube = new RubiksCube();
		mPerspective = new Perspective();
		
		mScrambleButton.addActionListener(this);

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
	    	mRubiksCube.rotatePerspective(Math.PI/50, 'x');
	    }
	    // x-axis neg
	    if (key == KeyEvent.VK_DOWN) {
	    	mRubiksCube.rotatePerspective(-Math.PI/50, 'x');
	    }
	    
	    // y-axis pos
	    if (key == KeyEvent.VK_LEFT) {
	    	mRubiksCube.rotatePerspective(Math.PI/50, 'y');
	    }
	    // y-axis neg
	    if (key == KeyEvent.VK_RIGHT) {
	    	mRubiksCube.rotatePerspective(-Math.PI/50, 'y');
	    }
	    
	    // z-axis pos
	    if (key == 'p') {
	    	mRubiksCube.rotatePerspective(Math.PI/50, 'z');
	    }
	    // z-axis neg
	    if (key == 'P') {
	    	mRubiksCube.rotatePerspective(-Math.PI/50, 'z');
	    }
	}

	private void rotate(Object rotateLeftX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obi = e.getSource();
		if(obi == mScrambleButton)
		{
			scramble();
		}
	}
	
	private void scramble() {
		Robot robot;
		try {
			robot = new Robot();
			// TODO: Simulate keypress or rotation
	        robot.keyPress('l');
	        System.out.println("SCRAMBLE PRESSED");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
