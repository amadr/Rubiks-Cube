import java.util.ArrayList;
import java.util.Collections;

/**
 * TODO
 * class description
 */

public class RubiksCube {

	private ArrayList<Cube> mCubeList = new ArrayList<Cube>();
	private ArrayList<Cube> mCubeCopyList = new ArrayList<Cube>();
		
	private Boolean mRotation = false;
	int mRotationCounter = 0;

	// A list of all perspective rotation matrices is needed because just multiplying 
	// one rotation matrix again and again leads to ever more accelerated rotation
	private ArrayList<RotMatrix> mRotMatrices = new ArrayList<RotMatrix>();
	
    private Vector3D[] mAxis = new Vector3D[6];
	
	private Boolean rotated = false;
  
	public RubiksCube() {
		for (int i = -150; i <= 150; i+=150) {
			for (int j = -150; j <= 150; j+=150) {
				for (int k = -150; k <= 150; k+=150) {
					Cube cb = new Cube(new Vector3D(i, j, k));
					Cube cb_ = new Cube(new Vector3D(i, j, k));
					mCubeList.add(cb);
					mCubeCopyList.add(cb_);
				}
			}
		}

		setAxisArray();
		
		mRotMatrices.add(new RotMatrix());
	}
	
	public void setAxisArray() {
		mAxis[0] = new Vector3D(450, 20, 0);
		mAxis[1] = new Vector3D(450, 100, 0);
		mAxis[2] = new Vector3D(393.4314, 156.5685, 0);
		mAxis[3] = new Vector3D(450, 100, 0);
		mAxis[4] = new Vector3D(450, 100, 0);
		mAxis[5] = new Vector3D(530, 100, 0);
	}
	
	public ArrayList<Cube> getCubeList() {
		return mCubeList;
	}
	
	public ArrayList<Cube> getCubeCopyList() {
		return mCubeCopyList;
	}
	
	public Vector3D[] getAxis() {
		return mAxis;
	}
	
	private void rotateAxis(RotMatrix rm) {
		//TODO
		mAxis[0].rotateVector(rm);
	}
	
	public void copyCubeList() {
		for (int i = 0; i < mCubeList.size(); i++) {
			mCubeCopyList.get(i).copyCube(mCubeList.get(i));
		}

		// Rotate to old perspective position
		for(int i = 0; i < mCubeList.size(); i++) {
			for (int j = 0; j < mRotMatrices.size(); j++) {
				mCubeCopyList.get(i).rotateCube(mRotMatrices.get(j));
			}
		}
		//Collections.sort(mCubeCopyList);
	}
	
	
	public void sort() {
		Collections.sort(mCubeCopyList);
	}
	
	public void reset() {
		mCubeList.removeAll(mCubeList);
		for (int i = -150; i <= 150; i+=150) {
			for (int j = -150; j <= 150; j+=150) {
				for (int k = -150; k <= 150; k+=150) {
					Cube cb = new Cube(new Vector3D(i, j, k));
					mCubeList.add(cb);
				}
			}
		}
		// Reset to  no perspective rotation
		mRotMatrices.removeAll(mRotMatrices);
		mRotMatrices.add(new RotMatrix());
		copyCubeList();
		
		// Set Axis to old position
		setAxisArray();
	}
	
	public void rotatePerspective(double phi, char axis) {
		if (axis == 'x') {
			mRotMatrices.add(RotMatrix.xRotMatrix(phi));
		} else if (axis == 'y') {
			mRotMatrices.add(RotMatrix.yRotMatrix(phi));
		} else if (axis == 'z') {
			mRotMatrices.add(RotMatrix.zRotMatrix(phi));
		}
		// Only rotate with the newest RotMatrix in list
		for (int i = 0; i < mCubeList.size(); i++) {
			mCubeCopyList.get(i).rotateCube(mRotMatrices.get(mRotMatrices.size()-1));
		}
		// TODO: Rotation point should be 0 point (Nullpunkt)
		for (int i = 0; i < mAxis.length; i++) {
			mAxis[i].rotateVector(mRotMatrices.get(mRotMatrices.size() - 1));
		}
		//Collections.sort(mCubeCopyList);
	}
	
	public void rotateLeftX(int dir) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * Math.PI/2);
		for (int j = 0; j < mCubeList.size(); j++) {
			if (mCubeList.get(j).getMid().getX() < 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
		copyCubeList();
	}

	public void rotateMiddleX(int dir) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() == 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
		copyCubeList();
	}
	
	public void rotateRightX(int dir) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() > 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
		copyCubeList();
	}
	
	public void rotateUpY(int dir) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() < 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
		copyCubeList();
	}

	public void rotateMiddleY(int dir) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() == 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
		copyCubeList();
	}
	
	public void rotateDownY(int dir) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() > 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
		copyCubeList();
	}
	
	public void rotateFrontZ(int dir) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() < 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
		copyCubeList();
	}

	public void rotateMiddleZ(int dir) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() == 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
		copyCubeList();
	}
	
	public void rotateBackZ(int dir) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() > 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
		copyCubeList();
	}
	
	
	/*******************************************************************************/
	/*
	 * Gibt es probleme wenn der user auf einmal mehrer eingaben tätigt zu rotation?
	 * daa alle gleiche zählvariable benutzen
	 */
//	public void rotatePosX() {
//		//copyCubeList();
//		//Collections.sort(mCubeCopyList);
//		RotMatrix xm = new RotMatrix();
//		
//		final double phi = Math.PI/360;
//		mRotationCounter++;
//		if(mRotation && mRotationCounter <= 180)
//		{
//			xm = RotMatrix.xRotMatrix(phi);
//			for(int j = 0; j < mCubeList.size(); j++) {
//				mCubeCopyList.get(j).rotateCube(xm);
//				mCubeList.get(j).rotateCube(xm);
//			}
//		}
//		else {
//			mRotation = false;
//			mRotationCounter = 0;
//		}
//	}
//	
//	public void rotatePosY() {
//		copyCubeList();
//		//Collections.sort(mCubeCopyList);
//		RotMatrix ym = new RotMatrix();
//		
//		final double phi = Math.PI/360;
//		mRotationCounter++;
//		if (mRotation && mRotationCounter <= 180)
//		{
//			ym = RotMatrix.yRotMatrix(phi);
//			for(int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeCopyList.get(j).getMid().getZ() < 0 || true) {
//					mCubeCopyList.get(j).rotateCube(ym);
//
//				}
//			}
//			}
//		else {
//			mRotation = false;
//			mRotationCounter = 0;
//		}
//	}
//	
//	public void rotatePosZ() {
//		copyCubeList();
//		//Collections.sort(mCubeCopyList);
//		RotMatrix zm = new RotMatrix();
//		
//		final double phi = Math.PI/360;
//		mRotationCounter++;
//		if(mRotation && mRotationCounter <= 180)
//		{
//			zm = RotMatrix.zRotMatrix(phi);
//			for(int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeCopyList.get(j).getMid().getZ() < 0) {
//				mCubeCopyList.get(j).rotateCube(zm);
//				}
//			}
//
//			}
//		else {
//			mRotation = false;
//			mRotationCounter = 0;
//		}
//	}
	
//	public static void sleep(int ms)
//	{
//	    try
//	    {
//	        Thread.sleep(ms);
//	    }
//	    catch(InterruptedException ex)
//	    {
//	        Thread.currentThread().interrupt();
//	    }
//	}

}