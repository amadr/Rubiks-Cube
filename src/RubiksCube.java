import java.util.ArrayList;

public class RubiksCube {

	private ArrayList<Cube> mCubeList = new ArrayList<Cube>();
	private ArrayList<Cube> mCubeCopyList = new ArrayList<Cube>();
	
	
	public RubiksCube()
	{
		for (int i = -150; i <= 150; i+=150) {
			for (int j = -150; j <= 150; j+=150) {
				for (int k = -150; k <= 150; k+=150) {
					Cube cb = new Cube(new Vector3D(i, j, k));
					mCubeList.add(cb);
				}
			}
		}
	}
	
	public ArrayList<Cube> getCubeList()
	{
		return mCubeList;
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
}