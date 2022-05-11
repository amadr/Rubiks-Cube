import java.util.Iterator;

/**
 * 
 */

public class Cube implements Comparable<Cube> {
	private double edgeLength;
	
	private Vector3D mToMid;
	
	public Vector3D[] mUnitVectors   = new Vector3D[3];
	
	public  Vector3D [][][] mEdges = new Vector3D [2][2][2];
	
	public  Square [][] mArea  = new Square [3][2];
	
	public  Vector3D [][] mNormalVectors  = new Vector3D [3][2];

	
	public Cube ()		 // leerer Konstruktor
	{	
		
	}
	
	public Cube (Vector3D toMid)		 // Nicht sicher, ob Daten �bergeben werden sollen 
	{	
		
		this.edgeLength = 10;	// TODO muss angepasst werden 	
		this.mToMid = toMid; // Vector mit Ausrichtung auf Mittelpunkt (0,0,0)
		this.mUnitVectors[0] = new Vector3D(1,0,0);	// Einheitsvectoren 
		this.mUnitVectors[1] = new Vector3D(0,1,0);
		this.mUnitVectors[2] = new Vector3D(0,0,1);
		
		
	 // ###########  Ecken berechnen #####################
		
		Vector3D ulf= toMid;												// claculate upper left corner
		ulf.subtractVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		ulf.addVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));	
		ulf.addVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[0][0][0] = ulf; 
		
		Vector3D urf= toMid;												// claculate upper right corner
		urf.addVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		urf.addVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		ulf.addVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[1][0][0] = urf; 
		
		
		Vector3D llf= toMid;												// claculate lower left corner
		llf.subtractVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		llf.subtractVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		llf.subtractVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[0][1][0] = llf; 
		
		Vector3D lrf= toMid;												// claculate upper right corner
		lrf.addVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		lrf.subtractVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		llf.subtractVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[1][1][0] = lrf; 
		
		//###### backside
		Vector3D ulb = ulf;													// calculate the backside
		ulb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[0][0][1] = ulb; 
		
		Vector3D urb = urf;
		urb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[1][0][1] = urb; 
		
		Vector3D llb = llf;
		llb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[0][1][1] = llb; 
		
		Vector3D lrb = lrf;
		lrb.addVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[1][1][1] = lrb; 
		
		
		// ############### normalen erstellen ##########
		
		
		this.mNormalVectors[0][0] = mUnitVectors[0];			//positive x Richtung
		this.mNormalVectors[1][0] = mUnitVectors[1];			//positive Y Richtung
		this.mNormalVectors[2][0] = mUnitVectors[2];			//positive Z Richtung
		

		this.mNormalVectors[0][1] = mUnitVectors[0].getScaledVector(-1);
		this.mNormalVectors[1][1] = mUnitVectors[1].getScaledVector(-1);
		this.mNormalVectors[2][1] = mUnitVectors[2].getScaledVector(-1);
		
		
		// ############### Areas erstellen ##########
		
	
		Square front = new Square(this.mEdges[0][0][0],this.mEdges[1][0][0],this.mEdges[0][1][0],this.mEdges[1][1][0],this.mNormalVectors[2][0]);
		this.mArea[2][0]= front ;
		
		Square back = new Square(this.mEdges[0][0][1],this.mEdges[1][0][1],this.mEdges[0][1][1],this.mEdges[1][1][1],this.mNormalVectors[2][1]);
		this.mArea[2][1]= back ;
		
		Square left = new Square(this.mEdges[0][0][0],this.mEdges[0][0][1],this.mEdges[0][1][0],this.mEdges[0][1][1],this.mNormalVectors[0][1]);
		this.mArea[0][1]= left ;
		

		Square right = new Square(this.mEdges[1][0][0],this.mEdges[1][0][1],this.mEdges[1][1][0],this.mEdges[1][1][1],this.mNormalVectors[0][0]);
		this.mArea[0][0]= right ;
		

		Square top = new Square(this.mEdges[0][0][0],this.mEdges[1][0][0],this.mEdges[0][0][0],this.mEdges[1][0][1],this.mNormalVectors[1][0]);
		this.mArea[1][0]= top ;
		

		Square bot = new Square(this.mEdges[0][1][0],this.mEdges[1][1][0],this.mEdges[0][1][0],this.mEdges[1][1][1],this.mNormalVectors[1][1]);
		this.mArea[1][1]= bot ;
	
	}
	
	public void setAllColors (Color c) {
		
		for (int i = 0; i < mArea.length; i++) {
			for (int j = 0; j < mArea[i].length; j++) {
				
				mArea[i][j].setColor(c); 
			}
		}
	}
	
	public void setSingleColor(Color c , int row, int column ) {
		
		mArea[row][column].setColor(c);
	}	
	
	public Color getSingleColor(int row, int column){
		return mArea[row][column].getColor();
	}
	
	public Vector3D getMid() {
		return this.mToMid;
	}
	
	public Vector3D [][][] getEdges(){											//TODO erstellung evt. in Konstruktor	
		return this.mEdges;		
	}
	
	public Square [][]getArea(){
		return this.mArea;
	}
	
	public Vector3D [][]getNormalVectors(){

		return this.mNormalVectors;
	}
	
	public void copyCube(Cube other) {
		this.mToMid = other.mToMid;
		this.mUnitVectors[0] = other.mUnitVectors[0];		
		this.mUnitVectors[1] = other.mUnitVectors[1];
		this.mUnitVectors[2] = other.mUnitVectors[2];	
		this.edgeLength = other.edgeLength;
	}
	
	public void calculateNomalVector(){ // TODO Quelle https://www.demo2s.com/java/java-vector-get-the-normal-vector-of-3-points-that-lie-on-a-plane.html
		
//	double [] u = {mUnitVectors[1].getX() - mUnitVectors[0].getX(), mUnitVectors[1].getY() - mUnitVectors[0].getY(), mUnitVectors[1].getZ() - mUnitVectors[0].getZ()} ;				
//	double [] v = {mUnitVectors[2].getX() - mUnitVectors[0].getX(), mUnitVectors[2].getY() - mUnitVectors[0].getY(), mUnitVectors[2].getZ() - mUnitVectors[0].getZ()} ;
//
//	crossProduct[0] = u[1] * v[2] - u[2] * v[1];
//    crossProduct[1] = u[2] * v[0] - u[0] * v[2];
//    crossProduct[2] = u[0] * v[1] - u[1] * v[0];
//		
//		mNormalVectors[0][0] = mUnitVectors[0];
//		mNormalVectors[1][0] = mUnitVectors[1];
//		mNormalVectors[2][0] = mUnitVectors[2];	
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
        
		if (this.mToMid.getZ()<other.mToMid.getZ()) {
			return -1; 
		} 
		else if (this.mToMid.getZ()>other.mToMid.getZ()) {
			return 1 ; 
		} 
		else {
			return 0 ; 
		}
    }	
}