/**
 * 
 */

/**
 * @author amad
 *
 */
public class Vector3D {
	private	double[] mVector = new double[3];
	
	public Vector3D() {	};
	public Vector3D(double x, double y, double z) {
		mVector[0] = x;
		mVector[1] = y;
		mVector[2] = z;
	};
	
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
	public double[] scaleVector(double scale) {
		double[] vector = mVector;
		vector[0] *= scale;
		vector[1] *= scale;
		vector[2] *= scale;
		return vector;
	}
	public double[] normalizeVector() {
		double normalizer = 1/getLength();
		return scaleVector(normalizer);
	}
	public double[] addVector(double[] other) {
		double[] vector = mVector;
		vector[0] += other[0];
		vector[1] += other[1];
		vector[2] += other[2];
		System.out.print("Hello"); //##########
		return vector;
	}
	public void copyVector(double[] other) {
		
	}
}
