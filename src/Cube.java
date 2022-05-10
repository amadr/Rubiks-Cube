import java.util.Iterator;

/**
 * 
 */

public class Cube implements Comparable<Cube> {
	private double edgeLength;
	
	private Vector3D toMid;
	
	public Vector3D[] unitVectors   = new Vector3D[3];
	
	public  Vector3D [][][] edges = new Vector3D [2][2][2];
	
	public  Square [][] area  = new Square [3][2];
	
	public  Vector3D [][] normalVectors  = new Vector3D [3][2];

	public Cube (Vector3D toMid,Vector3D vx,Vector3D vy, Vector3D vz)		 // Nicht sicher, ob Daten übergeben werden sollen 
	{	
		this.toMid = toMid; // Vector mit Ausrichtung auf Mittelpunkt (0,0,0)
		vx.normalizeVector();
		vy.normalizeVector();	// TODO
		vz.normalizeVector();
		this.unitVectors[0]= vx;
		this.unitVectors[1]= vy;
		this.unitVectors[2]= vz;
		this.edgeLength = 10;	// TODO muss angepasst werden 	
		
//		this.unitVectors[0] = new Vector3D(1,0,0);	// Einheitsvectoren 
//		this.unitVectors[1] = new Vector3D(0,1,0);
//		this.unitVectors[2] = new Vector3D(0,0,1);

	}
	
	public void setAllColors (Color c) {
		
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				
				area[i][j].setColor(c); 
			}
		}
	}
	
	public void setSingleColor(Color c , int row, int column ) {
		
		area[row][column].setColor(c);
	}	
	
	public Color getSingleColor(int row, int column){
		return area[row][column].getColor();
	}
	
	public Vector3D getMid() {
		return this.toMid;
	}
	
	public Vector3D [][][] getEdges(){
		return this.edges;
	}
	
	public Square [][]getArea(){
		return this.area;
	}
	
	public Vector3D [][]getNormalVectors(){
		return this.normalVectors;
	}
	
	public void copyCube(Cube other) {
		this.toMid = other.toMid;
		this.unitVectors[0] = other.unitVectors[0];		
		this.unitVectors[1] = other.unitVectors[1];
		this.unitVectors[2] = other.unitVectors[2];	
		this.edgeLength = other.edgeLength;	
	}
	
	public void calculateNomalVector(){ // TODO Quelle https://www.demo2s.com/java/java-vector-get-the-normal-vector-of-3-points-that-lie-on-a-plane.html
//	double [] u = {unitVectors[1].getX() - unitVectors[0].getX(), unitVectors[1].getY() - unitVectors[0].getY(), unitVectors[1].getZ() - unitVectors[0].getZ()} ;				
//	double [] v = {unitVectors[2].getX() - unitVectors[0].getX(), unitVectors[2].getY() - unitVectors[0].getY(), unitVectors[2].getZ() - unitVectors[0].getZ()} ;

//	crossProduct[0] = u[1] * v[2] - u[2] * v[1];
//    crossProduct[1] = u[2] * v[0] - u[0] * v[2];
//    crossProduct[2] = u[0] * v[1] - u[1] * v[0];
		
		normalVectors[0][0] = unitVectors[0];
		normalVectors[1][0] = unitVectors[1];
		normalVectors[2][0] = unitVectors[2];
		
		
	}
	@Override
    public int compareTo (Cube other){
        
		if (this.toMid.getZ()<other.toMid.getZ()) {
			return -1; 
		} 
		else if (this.toMid.getZ()>other.toMid.getZ()) {
			return 1 ; 
		} 
		else {
			return 0 ; 
		}
    }

	
	
	
	
	
	
	
}
