import java.util.ArrayList;
import java.util.Collections;

public class RubiksCube {

	private ArrayList<Cube> mCubeList = new ArrayList<Cube>();
	private ArrayList<Cube> mCubeCopyList = new ArrayList<Cube>();
		
	private Boolean mRotation = false;
	int mRotationCounter = 0;
	
	
	public RubiksCube() {
		for (int i = -150; i <= 150; i+=150) {
			for (int j = -150; j <= 150; j+=150) {
				for (int k = -150; k <= 150; k+=150) {
					Cube cb = new Cube(new Vector3D(i, j, k));
					mCubeList.add(cb);
					mCubeCopyList.add(cb);
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
	
	public void startRotation() {
		mRotation = true;
		//Collections.sort(mCubeCopyList);
	}
	
	public void rotateCube() {
		for(int i = 0; i < mCubeList.size(); i++) {
			mCubeList.get(i).rotateCube();
		}
	}
	
	/*
	 * Gibt es probleme wenn der user auf einmal mehrer eingaben tätigt zu rotation?
	 * daa alle gleiche zählvariable benutzen
	 */
	public void rotatePosX() {
		copyCubeList();
		Collections.sort(mCubeCopyList);
		RotMatrix xm = new RotMatrix();
		
		final double phi = Math.PI/360;
		mRotationCounter++;
		if(mRotation && mRotationCounter <= 180)
		{
			xm = RotMatrix.xRotMatrix(phi);
			for(int j = 0; j < mCubeList.size(); j++) {
				mCubeCopyList.get(j).rotateCube(xm);
			}
		}
		else {
			mRotation = false;
			mRotationCounter = 0;
		}
	}
	
	public void rotatePosY() {
		copyCubeList();
		//Collections.sort(mCubeCopyList);
		RotMatrix ym = new RotMatrix();
		
		final double phi = Math.PI/360;
		mRotationCounter++;
		if (mRotation && mRotationCounter <= 180)
		{
			ym = RotMatrix.yRotMatrix(phi);
			for(int j = 0; j < mCubeList.size(); j++) {
				if (mCubeCopyList.get(j).getMid().getZ() < 0 || true) {
					mCubeCopyList.get(j).rotateCube(ym);

				}
			}
			}
		else {
			mRotation = false;
			mRotationCounter = 0;
		}
	}
	
	public void rotatePosZ() {
		copyCubeList();
		//Collections.sort(mCubeCopyList);
		RotMatrix zm = new RotMatrix();
		
		final double phi = Math.PI/360;
		mRotationCounter++;
		if(mRotation && mRotationCounter <= 180)
		{
			zm = RotMatrix.zRotMatrix(phi);
			for(int j = 0; j < mCubeList.size(); j++) {
				if (mCubeCopyList.get(j).getMid().getZ() > 0) {
				mCubeCopyList.get(j).rotateCube(zm);
				}
			}

			}
		else {
			mRotation = false;
			mRotationCounter = 0;
		}
	}
	
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