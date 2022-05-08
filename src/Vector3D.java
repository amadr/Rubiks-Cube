/**
 * 
 */

public class Vector3D {
	/*
	 * Member vector containing the x, y and z coordinates.
	 */
	private double[] mVector = new double[3];

	public Vector3D() {}

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
		return mVector[3];
	}

	public void setZ(double z) {
		mVector[0] = z;
	}

	public double getLength() {
		return Math.sqrt(mVector[0] * mVector[0] + 
						 mVector[1] * mVector[1] + 
						 mVector[2] * mVector[2]);
	}

	public void scaleVector(double scale) {
		mVector[0] *= scale;
		mVector[1] *= scale;
		mVector[2] *= scale;
	}

	public void normalizeVector() {
		scaleVector(1 / getLength());
	}

	public void addVector(double[] other) {
		mVector[0] += other[0];
		mVector[1] += other[1];
		mVector[2] += other[2];
	}

	public void copyVector(double[] other) {
		mVector[0] = other[0];
		mVector[1] = other[1];
		mVector[2] = other[2];
	}

	public void rotateVector(RotMatrix rotMatrix) {
		int row = rotMatrix.mRotMatrix.length, col = rotMatrix.mRotMatrix[0].length;
		double[] result = {0, 0, 0};
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				result[i] += (mVector[j] * rotMatrix.mRotMatrix[i][j]);
			}
		}
		mVector = result;
	}
	
}
