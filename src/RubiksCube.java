import java.util.ArrayList;
import java.util.Collections;

public class RubiksCube {

	private ArrayList<Cube> mCubeList = new ArrayList<Cube>();
	private ArrayList<Cube> mCubeCopyList = new ArrayList<Cube>();
		
	private Boolean mRotation = false;
	int mRotationCounter = 0;
	
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
	}
	
	public ArrayList<Cube> getCubeList() {
		return mCubeList;
	}
	
	public ArrayList<Cube> getCubeCopyList() {
		return mCubeCopyList;
	}
	
	public void copyCubeList() {
		for (int i = 0; i < mCubeList.size(); i++) {
			mCubeCopyList.get(i).copyCube(mCubeList.get(i));
		}
	}
	
//	public void startRotation() {
//		mRotation = true;
//		//copyCubeList();
//		Collections.sort(mCubeCopyList);
//	}
	
	public void sort() {
		Collections.sort(mCubeList);
	}
	
	// not working properly yet
	public void reset() {
		for (int i = 0; i < mCubeList.size(); i++) {
			mCubeList.get(i).copyCube(mCubeCopyList.get(i));
		}
	}

	public void rotateCube() {
		for(int i = 0; i < mCubeList.size(); i++) {
			mCubeList.get(i).rotateCube();
		}
	}
	
	public void rotate() {
		for(int i = 0; i < mCubeList.size(); i++) {
			mCubeList.get(i).rotateCube();
		}
	}
	
	public void rotateLeftX(int dir) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() < 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
	}

	public void rotateMiddleX(int dir) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() == 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
	}
	
	public void rotateRightX(int dir) {
		RotMatrix xRot = RotMatrix.xRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getX() > 0) {
				mCubeList.get(j).rotateCube(xRot);
			}
		}
	}
	
	public void rotateUpY(int dir) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() < 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
	}

	public void rotateMiddleY(int dir) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() == 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
	}
	
	public void rotateDownY(int dir) {
		RotMatrix yRot = RotMatrix.yRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getY() > 0) {
				mCubeList.get(j).rotateCube(yRot);
			}
		}
	}
	
	public void rotateFrontZ(int dir) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() < 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
	}

	public void rotateMiddleZ(int dir) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() == 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
	}
	
	public void rotateBackZ(int dir) {
		RotMatrix zRot = RotMatrix.zRotMatrix(dir * Math.PI/2);
		for(int j = 0; j < mCubeList.size(); j++) {
			if(mCubeList.get(j).getMid().getZ() > 0) {
				mCubeList.get(j).rotateCube(zRot);
			}
		}
	}
	
	
	
	
	
	/*******************************************************************************/
	/*
	 * Gibt es probleme wenn der user auf einmal mehrer eingaben tätigt zu rotation?
	 * daa alle gleiche zählvariable benutzen
	 */
//	public void rotatePosX() {
//		copyCubeList();
//		//Collections.sort(mCubeCopyList);
//		RotMatrix xm = new RotMatrix();
//		
//		final double phi = Math.PI/360;
//		mRotationCounter++;
//		if(mRotation && mRotationCounter <= 180)
//		{
//			xm = RotMatrix.xRotMatrix(phi);
//			for(int j = 0; j < mCubeList.size(); j++) {
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