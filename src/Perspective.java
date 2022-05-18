import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Path2D;

/**
 * The calculation from 3D to 2D is done here. Also the paint methods for a
 * square, the cube and the whole Rubik's cube are declared here.
 */

public class Perspective {
	private double mXoffset, mYoffset, mScale;
	
	private Color mColor[];
	
	private int a = 0;
	
	public Perspective() {
		mXoffset = 400;
		mYoffset = 400;
		mScale = 1;
		mColor = new Color[6];
		mColor[0] = Color.GREEN;
		mColor[1] = Color.RED;
		mColor[2] = Color.YELLOW;
		mColor[3] = Color.BLUE;
		mColor[4] = Color.ORANGE;
		mColor[5] = Color.BLACK;	//change it later to WHITE

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
		
		Path2D path = new Path2D.Double();
		//sq.getEdges()[0].normalizeVector();
		
		a++;

		path.moveTo(sq.getEdges()[0].getX() * 200, sq.getEdges()[0].getY() * 200);
		for (int i = 0; i < sq.getEdges().length; i++) {		// i = 0 for Polygon
			x[i] = (int) sq.getEdges()[i].getX() + (int) mXoffset;
			y[i] = (int) sq.getEdges()[i].getY() + (int) mYoffset;
			
			path.lineTo(sq.getEdges()[i].getX() * 200, sq.getEdges()[i].getY() * 200);	       
		}

        Shape myVectorShape = new Polygon(x, y, 4);
        //Shape myVectorShape = new Polygon(getParallelX(sq), getParallelY(sq), 4);
        
        Stroke stroke = new BasicStroke((float) 3.0);
        g2.setStroke(stroke);
        
        path.closePath();
        //g2.draw(path);
        g2.fill(myVectorShape);
        g2.setColor(Color.BLACK);
        g2.draw(myVectorShape);
	}
	
	public void paintCube(Graphics2D g2, Cube cb) {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++)
				//if (cb.mNormalVectors[i][j].getZ() < 0) {	//normalvektor nicht richtig?
				if (cb.mArea[i][j].getNomalVecZ() < 0) {	//normalvektor nicht richtig?
					g2.setColor(cb.mArea[i][j].getColor());
					paintSquare(g2, cb.mArea[i][j]);
				}
		}
	}
	
	public void paintRubiksCube(Graphics2D g2, RubiksCube rc) {
		for (int i = 0; i < rc.getCubeList().size(); i++) {
			paintCube(g2, rc.getCubeList().get(i));
		}
	}
	
	private int [] getParallelX(Square sq) {
		int distance = 100;

		int[] x = new int[4];
		
		for (int i = 0; i < sq.getEdges().length; i++) {
			int projectionConst = (1 / distance - (int)sq.getEdges()[i].getZ());
			x[i] = (int) sq.getEdges()[i].getX() / projectionConst + (int) mXoffset;
		}
		return x;
	}
	
	private int [] getParallelY(Square sq) {
		int distance = 100;

		int[] y = new int[4];
		
		for (int i = 0; i < sq.getEdges().length; i++) {
			int projectionConst = (1 / distance - (int)sq.getEdges()[i].getZ());
			y[i] = (int) sq.getEdges()[i].getY() / projectionConst;
		}
		return y;
	}
}
