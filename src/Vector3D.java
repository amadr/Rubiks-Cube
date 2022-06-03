/**
 * This class contains a Vector with x, y, and z coordinates.
 */

public class Vector3D {
	
	private double[] mVector = new double[3];

	public Vector3D() {
		mVector[0] = 1;
		mVector[1] = 1;
		mVector[2] = 1;
	}

	public Vector3D(double x, double y, double z) {
		mVector[0] = x;
		mVector[1] = y;
		mVector[2] = z;
	}

	public double getX() {
		return mVector[0];
	}

	public void setX(double x) {
		mVector[0] = x;
	}

	public double getY() {
		return mVector[1];
	}

	public void setY(double y) {
		mVector[1] = y;
	}

	public double getZ() {
		return mVector[2];
	}

	public void setZ(double z) {
		mVector[2] = z;
	}

	public double getLength() {
		return Math.sqrt(mVector[0] * mVector[0] + mVector[1] * mVector[1] + mVector[2] * mVector[2]);
	}

	public Vector3D getScaledVector(double scale) {
		Vector3D rv = new Vector3D();
		rv.copyVector(this);
		rv.scaleVector(scale);
		return rv;
	}

	public void scaleVector(double scale) {
		mVector[0] *= scale;
		mVector[1] *= scale;
		mVector[2] *= scale;
	}

	public void addVector(Vector3D other) {
		mVector[0] += other.mVector[0];
		mVector[1] += other.mVector[1];
		mVector[2] += other.mVector[2];
	}

	public void subtractVector(Vector3D other) {
		mVector[0] -= other.mVector[0];
		mVector[1] -= other.mVector[1];
		mVector[2] -= other.mVector[2];
	}

	public void copyVector(Vector3D other) {
		mVector[0] = other.mVector[0];
		mVector[1] = other.mVector[1];
		mVector[2] = other.mVector[2];
	}

	public void rotateVector(RotMatrix rotMatrix) {
		int row = rotMatrix.mRotMatrix.length, col = rotMatrix.mRotMatrix[0].length;
		double[] result = { 0, 0, 0 };

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// rint is important because, too many decimal points cause normal vector z
				// component to be false
				result[i] += rint(mVector[j] * rotMatrix.mRotMatrix[i][j]);
			}
		}
		mVector[0] = result[0];
		mVector[1] = result[1];
		mVector[2] = result[2];
	}

	private double rint(double value) {
		final int decimalPoints = 4;
		double d = Math.pow(10, decimalPoints);
		return Math.rint(value * d) / d;
	}

}
