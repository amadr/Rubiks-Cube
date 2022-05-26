import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

/**
 * The calculation from 3D to 2D is done here. Also the paint methods for a
 * square, the cube and the whole Rubik's cube are declared here.
 */

public class Perspective {
	
	private double mXoffset, mYoffset, mScale;
	
	public Perspective() {
		mXoffset = 400;
		mYoffset = 400;
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
	
	public void paintAxis(Graphics2D g2, Vector3D[] axis) {
		Path2D axisPath = new Path2D.Double();
		g2.setColor(Color.BLACK);
		axisPath.moveTo(axis[0].getX() + mXoffset + 450, axis[0].getY());
		for (int i = 1; i < axis.length; i++) 
		{
			axisPath.lineTo(axis[i].getX() + mXoffset + 450, axis[i].getY());
		}
        g2.draw(axisPath);
        
        g2.setFont(new Font("Arial", Font.BOLD, 16)); 
    	g2.drawString("Y" , (float)(axis[0].getX() + mXoffset)+460, (float)axis[0].getY()+10);
    	g2.drawString("Z" , (float)(axis[2].getX() + mXoffset)+460, (float)axis[2].getY()+10);
    	g2.drawString("X" , (float)(axis[5].getX() + mXoffset)+460, (float)axis[5].getY()+10);
	}
	
	public void paintSquare(Graphics2D g2, Square sq) {
		Path2D path = new Path2D.Double();
		path.moveTo(sq.getEdges()[0].getX() + mXoffset, sq.getEdges()[0].getY() + mYoffset);
		
		for (int i = 1; i < sq.getEdges().length; i++) {
			path.lineTo(sq.getEdges()[i].getX() + mXoffset, sq.getEdges()[i].getY() + mYoffset);	
		}

        
        path.closePath();
        g2.fill(path);
        
        Stroke stroke = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);
        g2.setColor(Color.BLACK);
        g2.draw(path);
        		
        //AXIS
//        g2.drawLine(450+(int)mXoffset, 20, 450+(int)mXoffset, 100);
//        g2.drawLine(450+(int)mXoffset, 100, 393+(int)mXoffset, 156);
//        g2.drawLine(450+(int)mXoffset, 100, 530+(int)mXoffset, 100);
	}
	
	public void paintCube(Graphics2D g2, Cube cb) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (cb.mArea[i][j].getNomalVecZ() < 0) {
					g2.setColor(cb.mArea[i][j].getColor());
					paintSquare(g2, cb.mArea[i][j]);
				}
			}
		}
	}
	
	public void paintRubiksCube(Graphics2D g2, RubiksCube rc) {
		rc.sort();
		for (int i = 0; i < rc.getCubeList().size(); i++) {
			paintCube(g2, rc.getCubeCopyList().get(i));
		}
        paintAxis(g2, rc.getAxis());
	}
	
//	private int [] getParallelX(Square sq) {
//		int distance = 100;
//
//		int[] x = new int[4];
//		
//		for (int i = 0; i < sq.getEdges().length; i++) {
//			int projectionConst = (1 / distance - (int)sq.getEdges()[i].getZ());
//			x[i] = (int) sq.getEdges()[i].getX() / projectionConst + (int) mXoffset;
//		}
//		return x;
//	}
//	
//	private int [] getParallelY(Square sq) {
//		int distance = 100;
//
//		int[] y = new int[4];
//		
//		for (int i = 0; i < sq.getEdges().length; i++) {
//			int projectionConst = (1 / distance - (int)sq.getEdges()[i].getZ());
//			y[i] = (int) sq.getEdges()[i].getY() / projectionConst;
//		}
//		return y;
//	}
}
