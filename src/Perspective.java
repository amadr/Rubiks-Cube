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
		mXoffset = 0;
		mYoffset = 0;
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
			//parallel_projection(sq.getEdges()[i]);
			x[i] = (int) sq.getEdges()[i].getX();
			y[i] = (int) sq.getEdges()[i].getY();
			
			path.lineTo(sq.getEdges()[i].getX() * 200, sq.getEdges()[i].getY() * 200);
			
//			System.out.println( "x: " + x[i] + ", y: " + y[i]);
	       
		}

        Shape myVectorShape = new Polygon(x, y, 4);
        
        g2.setColor(mColor[a%3]);
        System.out.println( "a%6: " + a%6); 
        Stroke stroke = new BasicStroke((float) 5.0);
        g2.setStroke(stroke);
        
        path.closePath();
        //g2.draw(path);
        //g2.draw(myVectorShape);
        g2.fill(myVectorShape);
	}
	
	public void paintCube(Graphics2D g2, Cube cb) {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++)
				if (cb.mArea[i][j].getNomalVecZ() > 0 ) {	//normalvektor nicht richtig?
					paintSquare(g2, cb.mArea[i][j]);
				}
		}
	}
	
	public void paintRubiksCube(Graphics2D g2/*, RubiksCube rc*/) {
		
	}
	
	private void parallel_projection(Vector3D vec) {
		int distance = 1;
		System.out.println("Z: " + vec.getZ());
		double projectionConst = (1 / distance - vec.getZ()/100 );
		vec.setX(projectionConst * vec.getX());
		vec.setY(projectionConst * vec.getY());		
	}
}
