import java.util.ArrayList;
import java.util.Collections;

/**
 * TODO
 * class description
 */

public class RubiksCube {

	private ArrayList<Cube> mCubeList = new ArrayList<Cube>();
	// !TODO: Can be moved or explain and comment it out
	private ArrayList<Cube> mCubeLogicList = new ArrayList<Cube>();
	private ArrayList<Cube> mCubeCopyList = new ArrayList<Cube>();
	// A list of all perspective rotation matrices is needed because just multiplying 
	// one rotation matrix again and again leads to ever more accelerated rotation
	private ArrayList<RotMatrix> mRotMatrices = new ArrayList<RotMatrix>();
	
    private Vector3D[] mAxis = new Vector3D[5];

	private Boolean mRotation = false;
	int mRotationCounter = 0;
  
	public RubiksCube() {
		for (int i = -150; i <= 150; i+=150) {
			for (int j = -150; j <= 150; j+=150) {
				for (int k = -150; k <= 150; k+=150) {
					Cube cb = new Cube(new Vector3D(i, j, k));
					Cube cbLogic = new Cube(new Vector3D(i, j, k));
					Cube cbCopy = new Cube(new Vector3D(i, j, k));
					mCubeList.add(cb);
					mCubeLogicList.add(cbLogic);
					mCubeCopyList.add(cbCopy);
				}
			}
		}

		setAxisArray();
		
		mRotMatrices.add(new RotMatrix());
	}
	
	public void setAxisArray() {
		mAxis[0] = new Vector3D(0, -80, 0);
		mAxis[1] = new Vector3D(0, 0, 0);
		mAxis[2] = new Vector3D(-56.5686, 56.5686, 0);
		mAxis[3] = new Vector3D(0, 0, 0);
		mAxis[4] = new Vector3D(80, 0, 0);
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
		//Collections.sort(mCubeCopyList);

		// Rotate axis
		if (axis != 'y')
			mAxis[0].rotateVector(mRotMatrices.get(mRotMatrices.size() - 1));
		if (axis != 'z')
			mAxis[2].rotateVector(mRotMatrices.get(mRotMatrices.size() - 1));
		if (axis != 'x')
			mAxis[4].rotateVector(mRotMatrices.get(mRotMatrices.size() - 1));
	}
	
	public void rotatePlane(char p) {
		if (mRotation && mRotationCounter < 4) {
			doRotation(p, Math.PI/8);
			mRotationCounter++;
		} else {
			mRotationCounter = 0;
			mRotation = false;
		}
	}
	
	public void doRotation(char p, double phi) {
		if (p == 'l') {
	        rotateLeftX(1, phi);
	    }
	    if (p == 'L') {
	        rotateLeftX(-1, phi);
	    }
	    if (p == 'm') {
	        rotateMiddleX(1, phi);
	    }
	    if (p == 'M') {
	        rotateMiddleX(-1, phi);
	    }
	    if (p == 'r') {
	        rotateRightX(1, phi);
	    }
	    if (p == 'R') {
	        rotateRightX(-1, phi);
	    }
	    
	    if (p == 'u') {
	        rotateUpY(-1, phi);
	    }
	    if (p == 'U') {
	        rotateUpY(1, phi);
	    }
	    if (p == 'e') {
	        rotateMiddleY(-1, phi);
	    }
	    if (p == 'E') {
	        rotateMiddleY(1, phi);
	    }
	    if (p == 'd') {
	        rotateDownY(-1, phi);
	    }
	    if (p == 'D') {
	        rotateDownY(1, phi);
	    }
	    
	    if (p == 'f') {
	        rotateFrontZ(1, phi);
	    }
	    if (p == 'F') {
	        rotateFrontZ(-1, phi);
	    }
	    if (p == 's') {
	        rotateMiddleZ(1, phi);
	    }
	    if (p == 'S') {
	        rotateMiddleZ(-1, phi);
	    }
	    if (p == 'b') {
	        rotateBackZ(1, phi);
	    }
	    if (p == 'B') {
	        rotateBackZ(-1, phi);
	    }
	}
	
	public void copyCubeLogicList() {
		for (int i = 0; i < mCubeList.size(); i++) {
			mCubeList.get(i).copyCube(mCubeLogicList.get(i));
		}
	}
	
	public void rotateLeftX(int dir, double phi) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * phi);
		for (int j = 0; j < mCubeList.size(); j++) {
			if (mCubeList.get(j).getMid().getX() < 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix xRotLogic = RotMatrix.xRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getX() < 0) {
//					mCubeLogicList.get(j).rotateCube(xRotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}

	public void rotateMiddleX(int dir, double phi) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() == 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.xRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getX() == 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}
	
	public void rotateRightX(int dir, double phi) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() > 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.xRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getX() > 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}
	
	public void rotateUpY(int dir, double phi) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() < 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.yRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getY() < 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}

	public void rotateMiddleY(int dir, double phi) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() == 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.yRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getY()  == 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}
	
	public void rotateDownY(int dir, double phi) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() > 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.yRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getY()  > 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}
	
	public void rotateFrontZ(int dir, double phi) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() < 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.zRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getZ() < 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}

	public void rotateMiddleZ(int dir, double phi) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() == 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.zRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getZ() == 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}
	
	public void rotateBackZ(int dir, double phi) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * phi);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() > 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
		copyCubeList();
//		if (mRotationCounter == 0) {
//			RotMatrix rotLogic = RotMatrix.zRotMatrix(dir * Math.PI/2);
//			for (int j = 0; j < mCubeList.size(); j++) {
//				if (mCubeList.get(j).getMid().getZ() > 0) {
//					mCubeLogicList.get(j).rotateCube(rotLogic);
//				}
//			}
//		}
//		else if (mRotationCounter == 14) {
//			copyCubeLogicList();
//		}
	}

	public void setIsRotating() {
		// TODO Auto-generated method stub
		mRotation = true;
	}
	
	public boolean getIsRotating() {
		// TODO Auto-generated method stub
		return mRotation;
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