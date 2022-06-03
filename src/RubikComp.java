import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * This class generates the Rubik's Cube with 27 cubes, initializes the drawing
 * and implements the keyboard controls.
 */

public class RubikComp extends JComponent implements Runnable, KeyListener, ActionListener {
	
	private RubiksCube mRubiksCube;
	private Perspective mPerspective;

	private char[] mMoves;
	private ArrayList<Character> mMovesList = new ArrayList<Character>();
	private char mCurrentMove;

	public JButton mScrambleButton;
	public JButton mPseudoSolveButton;

	private Timer mTimer;
	
	public RubikComp() {
		mRubiksCube = new RubiksCube();
		mPerspective = new Perspective();

		mMoves = new char[] { 'l', 'L', 'm', 'M', 'r', 'R', 'u', 'U', 'e', 'E', 'd', 'D', 'f', 'F', 's', 'S', 'b',
				'B' };

		mScrambleButton = new JButton("Scramble");
		mScrambleButton.addActionListener(this);
		mScrambleButton.setFocusable(false);
		mScrambleButton.setBackground(Color.BLACK);
		mScrambleButton.setForeground(Color.WHITE);

		mPseudoSolveButton = new JButton("Solve");
		mPseudoSolveButton.addActionListener(this);
		mPseudoSolveButton.setFocusable(false);
		mPseudoSolveButton.setBackground(Color.BLACK);
		mPseudoSolveButton.setForeground(Color.WHITE);

		mTimer = new Timer(10, this);
		setPreferredSize(new Dimension(1100, 800));
		addKeyListener(this);
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		mPerspective.paintRubiksCube(g2, mRubiksCube);

		if (!mRubiksCube.getIsRotating()) {
			mTimer.stop();
		}
	}

	@Override
	public void run() {
		while (true) {
			repaint();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		char key_char = e.getKeyChar();

		if (!mRubiksCube.getIsRotating()) {
			for (char c : mMoves) {
				if (key_char == c) {
					mCurrentMove = key_char;
					mTimer.start();
					mRubiksCube.setIsRotating();
					mRubiksCube.rotatePlane(mCurrentMove);
				}
			}
		}

		// reset
		if (key_char == '0') {
			mRubiksCube.reset();
		}

		// x-axis pos
		if (key_char == 'x') {
			mRubiksCube.rotatePerspective(Math.PI / 50, 'x');
		}
		// x-axis neg
		if (key_char == 'X') {
			mRubiksCube.rotatePerspective(-Math.PI / 50, 'x');
		}

		// y-axis pos
		if (key_char == 'y') {
			mRubiksCube.rotatePerspective(Math.PI / 50, 'y');
		}
		// y-axis neg
		if (key_char == 'Y') {
			mRubiksCube.rotatePerspective(-Math.PI / 50, 'y');
		}

		// z-axis pos
		if (key_char == 'z') {
			mRubiksCube.rotatePerspective(Math.PI / 50, 'z');
		}
		// z-axis neg
		if (key_char == 'Z') {
			mRubiksCube.rotatePerspective(-Math.PI / 50, 'z');
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obi = e.getSource();
		if (obi == mScrambleButton) {
			scramble();
		} else if (obi == mPseudoSolveButton) {
			solve();
		} else if (obi == mTimer) {
			mRubiksCube.rotatePlane(mCurrentMove);
		}
	}

	private void scramble() {
		Random rand = new Random();
		mRubiksCube.addNewCubes(mRubiksCube.getCubeList());
		mMovesList.removeAll(mMovesList);
		for (int i = 0; i < 20; i++) {
			int n = rand.nextInt(17);
			mRubiksCube.doRotation(mMoves[n], Math.PI / 2);
			if (Character.isLowerCase(mMoves[n])) {
				mMovesList.add(Character.toUpperCase(mMoves[n]));
			} else if (Character.isUpperCase(mMoves[n])) {
				mMovesList.add(Character.toLowerCase(mMoves[n]));
			}
		}
	}

	private void solve() {
		for (int i = mMovesList.size(); i > 0; i--) {
			mRubiksCube.doRotation(mMovesList.get(i - 1), Math.PI / 2);
		}
		// RubiksCube is solved, remove all chars from moves list
		mMovesList.removeAll(mMovesList);
	}

}
