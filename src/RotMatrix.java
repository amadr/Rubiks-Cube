/**
 * TODO
 * class description
 */

public class RotMatrix {

	public double[][] mRotMatrix = new double[3][3];

	public RotMatrix() {
		int rows = mRotMatrix.length, cloumns = mRotMatrix[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cloumns; j++) {
				if (i == j) {
					mRotMatrix[i][j] = 1;
				} else {
					mRotMatrix[i][j] = 0;
				}
			}
		}
	}

/*
	public RotMatrix(double r11, double r12, double r13,
					 double r21, double r22, double r23,
					 double r31, double r32, double r33) {
		double matrix[][] = { { r11, r12, r13 }, { r21, r22, r23 }, { r31, r32, r33 } };
		mRotMatrix = matrix;
	}
*/
	
	public RotMatrix(double[] rot) {
		int row = mRotMatrix.length - 1, col = mRotMatrix[0].length - 1;
		int rotLength = rot.length - 1;

		for (int i = row; i >= 0; i--) {
			for (int j = col; j >= 0; j--) {
				if(rotLength >= 0) {
					mRotMatrix[i][j] = rot[rotLength--];
				}
			}
		}
	}
	
	public RotMatrix multiplyMatrices(RotMatrix other) {
		RotMatrix productMatrix = new RotMatrix();
		double[][] result = new double[mRotMatrix.length][other.mRotMatrix[0].length];

		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result[0].length; col++) {
				result[row][col] = multiplyMatricesCell(other.mRotMatrix, row, col);
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println("i, j: " + i + ":" + j + ":: " + result[i][j]);
			}
		}

		productMatrix.mRotMatrix = result;
		return productMatrix;
	}
	
	private double multiplyMatricesCell(double[][] other, int row, int col) {
		double cell = 0;
		for (int i = 0; i < other.length; i++) {
			cell += mRotMatrix[row][i] * other[i][col];
		}
		return cell;
	}

	public static RotMatrix xRotMatrix(double phi) {
		final double[] xRot = {1, 0, 0, 
						 0, Math.cos(phi), -Math.sin(phi), 
						 0, Math.sin(phi), Math.cos(phi)};
		final RotMatrix xRotMatrix = new RotMatrix(xRot);
		return xRotMatrix;
	}
	
	public static RotMatrix yRotMatrix(double phi) {
		final double[] yRot = {Math.cos(phi), 0, Math.sin(phi), 
						 0, 1, 0,
						 -Math.sin(phi), 0, Math.cos(phi)};
		final RotMatrix yRotMatrix = new RotMatrix(yRot);
		return yRotMatrix;
	}
	
	public static RotMatrix zRotMatrix(double phi) {
		final double[] zRot = {Math.cos(phi), -Math.sin(phi), 0, 
						 Math.sin(phi), Math.cos(phi), 0, 
						 0, 0, 1};
		final RotMatrix zRotMatrix = new RotMatrix(zRot);
		return zRotMatrix;
	}
}
