import java.awt.Color;

/**
 * This class contains 4 edges, the normal vector and the color of the plane.
 */

public class Square {

	private Vector3D[] mEdges = new Vector3D[4];

	private Vector3D mNormalVector;

	private Color mColor;

	public Square(Vector3D ul, Vector3D ur, Vector3D lr, Vector3D ll, Vector3D nv, Color c) {
		this.mEdges[0] = ul;
		this.mEdges[1] = ur;
		this.mEdges[2] = lr;
		this.mEdges[3] = ll;
		this.mNormalVector = nv;
		this.mColor = c;
	}

	public void setEdges(Vector3D[] e) {
		this.mEdges[0] = e[0];
		this.mEdges[1] = e[1];
		this.mEdges[2] = e[2];
		this.mEdges[3] = e[3];
	}
	
	public Vector3D[] getEdges() {
		return mEdges;
	}

	public void setNomalVec(Vector3D nv) {
		this.mNormalVector = nv;
	}
	
	public double getNomalVecZ() {
		return this.mNormalVector.getZ();
	}

	public void setColor(Color c) {
		mColor = c;
	}

	public Color getColor() {
		return this.mColor;
	}

	public void copySquare(Square other) {
		this.mEdges[0] = other.mEdges[0];
		this.mEdges[1] = other.mEdges[1];
		this.mEdges[2] = other.mEdges[2];
		this.mEdges[3] = other.mEdges[3];
		this.mNormalVector = other.mNormalVector;
		this.mColor = other.mColor;
	}
}
