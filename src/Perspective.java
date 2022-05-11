import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.zip.ZipEntry;

/**
 * The calculation from 3D to 2D is done here. Also the paint methods for a
 * square, the cube and the whole Rubik's cube are declared here.
 */

public class Perspective {
	private double mXoffset, mYoffset, mScale;
	
	private int a = 0;
	
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
			
			System.out.println( "x: " + x[i] + ", y: " + y[i]);
		}

        Shape myVectorShape = new Polygon(x, y, 4);
        g2.draw(myVectorShape);
        //g2.fill(myVectorShape);
	}
	
	public void paintCube(Graphics2D g2, Cube cb) {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++)
				paintSquare(g2, cb.area[i][j]);
		}
	}
	
	public void paintRubiksCube(Graphics2D g2/*, RubiksCube rc*/) {
		
	}
	
	private void parallel_projection(Vector3D vec) {
		int distance = 4;
		
		double projectionConst = (1 / distance - vec.getZ());
		vec.setX(projectionConst * vec.getX());
		vec.setY(projectionConst * vec.getY());		
	}
}
