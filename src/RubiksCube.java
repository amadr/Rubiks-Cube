import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class RubiksCube {

	private ArrayList<Cube> mCubeList = new ArrayList<Cube>();
	private ArrayList<Cube> mCubeCopyList = new ArrayList<Cube>();
	
	private Boolean rotated = false;
	
	int a = 0;
	
	
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
	
	public void rotateCube() {
//		RotMatrix xm = new RotMatrix();
//		RotMatrix ym = new RotMatrix();
//		RotMatrix zm = new RotMatrix();
//		xm = RotMatrix.xRotMatrix(Math.PI/300);
//		ym = RotMatrix.yRotMatrix(Math.PI/300);
//		zm = RotMatrix.zRotMatrix(Math.PI/300);

		for(int i = 0; i < mCubeList.size(); i++) {
			mCubeList.get(i).rotateCube();
		}
	}
	
	public void rotatePosX() {
		RotMatrix xm = new RotMatrix();
		
		final double phi = Math.PI/360;
		a++;
//		if (!rotated || true)
		if(a<360)
		{
		//for (double i = 0; i < 360; i++) {
			xm = RotMatrix.xRotMatrix(phi);
			//sleep(1);
			for(int j = 0; j < mCubeList.size(); j++) {
				mCubeList.get(j).rotateCube(xm);
			}
			//sleep(1);
			//System.out.println("rotate" + i);
			}
		//}
		rotated = true;
	}
	
	public static void sleep(int ms)
	{
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}
}