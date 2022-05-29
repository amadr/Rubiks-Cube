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
		axisPath.moveTo(axis[0].getX() + mXoffset + 450, axis[0].getY() + 100);
		for (int i = 1; i < axis.length; i++) {
			axisPath.lineTo(axis[i].getX() + mXoffset + 450, axis[i].getY() + 100);
		}
		g2.draw(axisPath);

		g2.setFont(new Font("Arial", Font.BOLD, 16));
		g2.drawString("Y", (float) (axis[0].getX() + mXoffset) + 460, (float) axis[0].getY() + 100);
		g2.drawString("Z", (float) (axis[2].getX() + mXoffset) + 470, (float) axis[2].getY() + 100);
		g2.drawString("X", (float) (axis[4].getX() + mXoffset) + 460, (float) axis[4].getY() + 100);
	}

	private void paintInstructions(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.BOLD, 18));
		g2.drawString("Instructions: ", (float) mXoffset + 430, 450);
		g2.setFont(new Font("Arial", Font.ITALIC, 18));
		g2.drawString("(Uppercase for opposite rotation)", (float) mXoffset + 430, 470);

		g2.setFont(new Font("Arial", Font.PLAIN, 18));
		g2.drawString("Left:", (float) mXoffset + 430, 495);
		g2.drawString("l", (float) mXoffset + 520, 495);
		g2.drawString("Middle_X: ", (float) mXoffset + 430, 515);
		g2.drawString("m ", (float) mXoffset + 520, 515);
		g2.drawString("Right:", (float) mXoffset + 430, 535);
		g2.drawString("r", (float) mXoffset + 520, 535);

		g2.drawString("Up:", (float) mXoffset + 430, 560);
		g2.drawString("u", (float) mXoffset + 520, 560);
		g2.drawString("Middle_Y:", (float) mXoffset + 430, 580);
		g2.drawString("e", (float) mXoffset + 520, 580);
		g2.drawString("Down:", (float) mXoffset + 430, 600);
		g2.drawString("d", (float) mXoffset + 520, 600);

		g2.drawString("Front:", (float) mXoffset + 430, 625);
		g2.drawString("f", (float) mXoffset + 520, 625);
		g2.drawString("Middle_Z:", (float) mXoffset + 430, 645);
		g2.drawString("s", (float) mXoffset + 520, 645);
		g2.drawString("Back:", (float) mXoffset + 430, 665);
		g2.drawString("b", (float) mXoffset + 520, 665);
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
		paintInstructions(g2);
	}
}
