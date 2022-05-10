import java.util.Iterator;

/**
 * 
 */

public class Cube implements Comparable<Cube> {
	private double edgeLength;
	
	private Vector3D toMid;
	
	public Vector3D[] mUnitVectors   = new Vector3D[3];
	
	public  Vector3D [][][] edges = new Vector3D [2][2][2];
	
	public  Square [][] area  = new Square [3][2];
	
	public  Vector3D [][] normalVectors  = new Vector3D [3][2];

	
	public Cube ()		 // Nicht sicher, ob Daten übergeben werden sollen 
	{	
		
	}
	
	public Cube (Vector3D toMid)		 // Nicht sicher, ob Daten übergeben werden sollen 
	{	
		this.toMid = toMid; // Vector mit Ausrichtung auf Mittelpunkt (0,0,0)
//		vx.normalizeVector();
//		vy.normalizeVector();	// TODO	Vector3D toMid,Vector3D vx,Vector3D vy, Vector3D vz
//		vz.normalizeVector();
//		this.mUnitVectors[0]= vx;
//		this.mUnitVectors[1]= vy;
//		this.mUnitVectors[2]= vz;
//		this.edgeLength = 10;	// TODO muss angepasst werden 	
		
		this.mUnitVectors[0] = new Vector3D(1,0,0);	// Einheitsvectoren 
		this.mUnitVectors[1] = new Vector3D(0,1,0);
		this.mUnitVectors[2] = new Vector3D(0,0,1);
		
		
	 // ###########  Ecken berechnen #####################

		Vector3D halfUnit0 = this.mUnitVectors[0].getScaledVector(0.5);
		Vector3D halfUnit1 = this.mUnitVectors[0].getScaledVector(0.5);
		Vector3D halfUnit2 = this.mUnitVectors[0].getScaledVector(0.5);		
		
		Vector3D ulf= toMid;												// claculate upper left corner
		ulf.subtractVector(halfUnit0.getScaledVector(0.5*this.edgeLength));
		ulf.addVector(halfUnit1.getScaledVector(0.5*this.edgeLength));	
		ulf.addVector(halfUnit2.getScaledVector(0.5*this.edgeLength));
		this.edges[0][0][0] = ulf; 
		
		Vector3D urf= toMid;												// claculate upper right corner
		urf.addVector(halfUnit0.getScaledVector(0.5*this.edgeLength));
		urf.addVector(halfUnit1.getScaledVector(0.5*this.edgeLength));
		ulf.addVector(halfUnit2.getScaledVector(0.5*this.edgeLength));
		this.edges[1][0][0] = urf; 
		
		
		Vector3D llf= toMid;												// claculate lower left corner
		llf.subtractVector(halfUnit0.getScaledVector(0.5*this.edgeLength));
		llf.subtractVector(halfUnit1.getScaledVector(0.5*this.edgeLength));
		llf.subtractVector(halfUnit2.getScaledVector(0.5*this.edgeLength));
		this.edges[0][1][0] = llf; 
		
		Vector3D lrf= toMid;												// claculate upper right corner
		lrf.addVector(halfUnit0.getScaledVector(0.5*this.edgeLength));
		lrf.subtractVector(halfUnit1.getScaledVector(0.5*this.edgeLength));
		llf.subtractVector(halfUnit2.getScaledVector(0.5*this.edgeLength));
		this.edges[1][1][0] = lrf; 
		
		//###### backside
		Vector3D ulb = ulf;													// calculate the backside
		ulb.addVector(halfUnit2.getScaledVector(this.edgeLength));
		this.edges[0][0][1] = ulb; 
		
		Vector3D urb = urf;
		urb.addVector(halfUnit2.getScaledVector(this.edgeLength));
		this.edges[1][0][1] = urb; 
		
		Vector3D llb = llf;
		llb.addVector(halfUnit2.getScaledVector(this.edgeLength));
		this.edges[0][1][1] = llb; 
		
		Vector3D lrb = lrf;
		lrb.addVector(halfUnit2.getScaledVector(0.5*this.edgeLength));
		this.edges[1][1][1] = lrb; 
		
		
		// ############### normalen erstellen ##########
		
		
		this.normalVectors[0][0] = mUnitVectors[0];			//positive x Richtung
		this.normalVectors[1][0] = mUnitVectors[1];			//positive Y Richtung
		this.normalVectors[2][0] = mUnitVectors[2];			//positive Z Richtung
		

		this.normalVectors[0][1] = mUnitVectors[0].getScaledVector(-1);
		this.normalVectors[1][1] = mUnitVectors[1].getScaledVector(-1);
		this.normalVectors[2][1] = mUnitVectors[2].getScaledVector(-1);
		
		
		// ############### Areas erstellen ##########
		
	
		Square front = new Square(this.edges[0][0][0],this.edges[1][0][0],this.edges[0][1][0],this.edges[1][1][0],this.normalVectors[2][0]);
		this.area[2][0]= front ;
		
		Square back = new Square(this.edges[0][0][1],this.edges[1][0][1],this.edges[0][1][1],this.edges[1][1][1],this.normalVectors[2][1]);
		this.area[2][1]= back ;
		
		Square left = new Square(this.edges[0][0][0],this.edges[0][0][1],this.edges[0][1][0],this.edges[0][1][1],this.normalVectors[0][1]);
		this.area[0][1]= left ;
		

		Square right = new Square(this.edges[1][0][0],this.edges[1][0][1],this.edges[1][1][0],this.edges[1][1][1],this.normalVectors[0][0]);
		this.area[0][0]= right ;
		

		Square top = new Square(this.edges[0][0][0],this.edges[1][0][0],this.edges[0][0][0],this.edges[1][0][1],this.normalVectors[1][0]);
		this.area[1][0]= top ;
		

		Square bot = new Square(this.edges[0][1][0],this.edges[1][1][0],this.edges[0][1][0],this.edges[1][1][1],this.normalVectors[1][1]);
		this.area[1][1]= bot ;
	
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
	
	public Vector3D [][][] getEdges(){											//TODO erstellung evt. in Konstruktor
		
		
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
		this.mUnitVectors[0] = other.mUnitVectors[0];		
		this.mUnitVectors[1] = other.mUnitVectors[1];
		this.mUnitVectors[2] = other.mUnitVectors[2];	
		this.edgeLength = other.edgeLength;	
	}
	
	public void calculateNomalVector(){ // TODO Quelle https://www.demo2s.com/java/java-vector-get-the-normal-vector-of-3-points-that-lie-on-a-plane.html
		
//	double [] u = {unitVectors[1].getX() - unitVectors[0].getX(), unitVectors[1].getY() - unitVectors[0].getY(), unitVectors[1].getZ() - unitVectors[0].getZ()} ;				
//	double [] v = {unitVectors[2].getX() - unitVectors[0].getX(), unitVectors[2].getY() - unitVectors[0].getY(), unitVectors[2].getZ() - unitVectors[0].getZ()} ;

//	crossProduct[0] = u[1] * v[2] - u[2] * v[1];
//    crossProduct[1] = u[2] * v[0] - u[0] * v[2];
//    crossProduct[2] = u[0] * v[1] - u[1] * v[0];
		
		normalVectors[0][0] = mUnitVectors[0];
		normalVectors[1][0] = mUnitVectors[1];
		normalVectors[2][0] = mUnitVectors[2];
		
		
	}
	
	public void rotateCube(RotMatrix rm) {
		
//		RotMatrix rx = RotMatrix.xRotMatrix( );
//		RotMatrix ry = RotMatrix.yRotMatrix( );
//		RotMatrix rz = RotMatrix.zRotMatrix( );
//		
//		
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
