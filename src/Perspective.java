import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

/**
 * The calculation from 3D to 2D is done here. Also the paint methods for a
 * square, the cube and the whole Rubik's cube are declared here.
 */

public class Perspective {
	private double mXoffset, mYoffset, mScale;
	
	public Perspective() {
		mXoffset = 0;
		mYoffset = 0;
		mScale = 1;
	}
	
	public void setXOffset(double x) {
		mXoffset = x;
	}
	
	public void setYOffset(double y) {
		mYoffset = y;
	}
	
	public void setScale(double s) {
		mScale = s;
	}
	
	public void paintSquare(Graphics2D g2, Square sq) {
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		for (int i = 0; i < sq.getEdges().length; i++) {
			x[i] = (int) sq.getEdges()[i].getX();
			y[i] = (int) sq.getEdges()[i].getY();
		}

        Shape myVectorShape = new Polygon(x, y, 4);
        g2.fill(myVectorShape);
	}
	
	public void paintCube(Graphics2D g2, Cube cb) {
		
	}
	
	public void paintRubiksCube(Graphics2D g2/*, RubiksCube rc*/) {
		
	}
}
