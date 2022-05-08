/**
 * 
 */

public class Cube {
	private double edgelength;
	
	private Vector3D tomid;
	
	public Vector3D[] unitVecors = new Vector3D[3];
	
	public  Vector3D [][][] edges = new Vector3D [2][2][2];
	
	public  Square [][] area  = new Square [3][2];
	
	public  Vector3D [][] normalVectors  = new Vector3D [3][2];

	public Cube (Vector3D tomid)
	{	
		this.tomid =tomid; 
	}
}
