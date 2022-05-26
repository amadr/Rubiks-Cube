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
	
	private char[] mMoves;
	
    private Boolean rotate = true;
    private Boolean rotate_ = true;
    
	JButton mScrambleButton;
    
	public RubikComp() {		
		//mCube = new Cube(new Vector3D(0, 0, 100));
		
		mRubiksCube = new RubiksCube();
		mPerspective = new Perspective();
		
		mMoves = new char[18];
		
		mScrambleButton = new JButton("Scramble");
		mScrambleButton.addActionListener(this);

		// Enable keyboard focus
		this.setFocusable(true);
		
    	setPreferredSize (new Dimension (1000, 800));
    	addKeyListener(this);
    	Thread th = new Thread (this);
        th.start ();
    }
	
	public void addMoves() {
		mMoves[0] = 'l';
		mMoves[1] = 'L';
		mMoves[2] = 'm';
		mMoves[3] = 'M';
		mMoves[4] = 'r';
		mMoves[5] = 'R';
		mMoves[6] = 'u';
		mMoves[7] = 'U';
		mMoves[8] = 'e';
		mMoves[9] = 'E';
		mMoves[10] = 'd';
		mMoves[11] = 'E';
		mMoves[12] = 'f';
		mMoves[13] = 'F';
		mMoves[14] = 's';
		mMoves[15] = 'S';
		mMoves[16] = 'b';
		mMoves[17] = 'B';
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
	    char key_char = e.getKeyChar();
	    
	    mRubiksCube.rotatePlane(key_char);
	    
		System.out.println("keyPressed " + key_char);
  
		// reset
	    if (key == '0') {
	        mRubiksCube.reset();
	    }
	    
	    // x-axis pos
	    if (key == 'x') {
	    	mRubiksCube.rotatePerspective(Math.PI/50, 'x');
	    }
	    // x-axis neg
	    if (key == 'X') {
	    	mRubiksCube.rotatePerspective(-Math.PI/50, 'x');
	    }
	    
	    // y-axis pos
	    if (key == 'y') {
	    	mRubiksCube.rotatePerspective(Math.PI/50, 'y');
	    }
	    // y-axis neg
	    if (key == 'Y') {
	    	mRubiksCube.rotatePerspective(-Math.PI/50, 'y');
	    }
	    
	    // z-axis pos
	    if (key == 'z') {
	    	mRubiksCube.rotatePerspective(Math.PI/50, 'z');
	    }
	    // z-axis neg
	    if (key == 'Z') {
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
		
	}
	
}
