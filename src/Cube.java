import java.awt.Color;

/**
 * 
 */

public class Cube implements Comparable<Cube> {
	private double edgeLength;
	
	private Vector3D mToMid;
	
	public Vector3D[] mUnitVectors   = new Vector3D[6];
	
	public  Vector3D [][][] mEdges = new Vector3D [2][2][2];
	
	public  Square [][] mArea  = new Square [3][2];
	
	public  Vector3D [][] mNormalVectors  = new Vector3D [3][2];

	
	public Cube ()		 // leerer Konstruktor
	{	
		
	}
	
	public Cube (Vector3D toMid)		 // Nicht sicher, ob Daten �bergeben werden sollen 
	{	
		
		
		this.edgeLength = 100;	// TODO muss angepasst werden 	
		this.mToMid = toMid; // Vector mit Ausrichtung auf Mittelpunkt (0,0,0)
		this.mUnitVectors[0] = new Vector3D(1,0,0);	// Einheitsvectoren 
		this.mUnitVectors[1] = new Vector3D(0,1,0);
		this.mUnitVectors[2] = new Vector3D(0,0,1);
		this.mUnitVectors[3] = new Vector3D(-1,0,0);
		this.mUnitVectors[4] = new Vector3D(0,-1,0);
		this.mUnitVectors[5] = new Vector3D(0,0,-1);
		
		
	 // ###########  Ecken berechnen #####################
		
		//Vector3D ulf= new Vector3D(toMid.getX(),toMid.getY(),toMid.getZ() );												// claculate upper left corner
		Vector3D ulf = new Vector3D();
		ulf.copyVector(toMid);
		ulf.subtractVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		ulf.subtractVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));	
		ulf.subtractVector(this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[0][0][0] = ulf;
		
		Vector3D urf = new Vector3D();
		urf.copyVector(toMid);// claculate upper right corner
		urf.addVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		urf.subtractVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		urf.subtractVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[1][0][0] = urf;		
		
		Vector3D llf = new Vector3D();
		llf.copyVector(toMid);// claculate lower left corner
		llf.subtractVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		llf.addVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		llf.subtractVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[0][1][0] = llf;
		
		Vector3D lrf= new Vector3D();												// claculate upper right corner
		lrf.copyVector(toMid);
		lrf.addVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		lrf.addVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		lrf.subtractVector( this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[1][1][0] = lrf;
		
		//###### backside
		Vector3D ulb = new Vector3D();
		ulb.copyVector(ulf);// calculate the backside
		ulb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[0][0][1] = ulb;
		
		Vector3D urb = new Vector3D();
		urb.copyVector(urf);
		urb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[1][0][1] = urb;
		
		Vector3D llb = new Vector3D();
		llb.copyVector(llf);
		llb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[0][1][1] = llb;
		
		Vector3D lrb = new Vector3D();
		lrb.copyVector(lrf);
		lrb.addVector( this.mUnitVectors[2].getScaledVector(this.edgeLength));
		this.mEdges[1][1][1] = lrb;
		
		
		// ############### normalen erstellen ##########	
		this.calculateNomalVector();		
		// ############### Areas erstellen ##########	
		this.setSquares();
	}
	
public void setSquares() {
		
	Square front = new Square(this.mEdges[0][0][0], this.mEdges[1][0][0], this.mEdges[1][1][0], this.mEdges[0][1][0],
			this.mNormalVectors[2][1], Color.GREEN);
	this.mArea[2][0] = front;

	Square back = new Square(this.mEdges[0][0][1], this.mEdges[1][0][1], this.mEdges[1][1][1], this.mEdges[0][1][1],
			this.mNormalVectors[2][0], Color.BLUE);
	this.mArea[2][1] = back;

	Square left = new Square(this.mEdges[0][0][1], this.mEdges[0][0][0], this.mEdges[0][1][0], this.mEdges[0][1][1],
			this.mNormalVectors[0][1], Color.ORANGE);
	this.mArea[0][1] = left;

	Square right = new Square(this.mEdges[1][0][1], this.mEdges[1][0][0], this.mEdges[1][1][0], this.mEdges[1][1][1],
			this.mNormalVectors[0][0], Color.RED);
	this.mArea[0][0] = right;

	Square top = new Square(this.mEdges[0][0][1], this.mEdges[1][0][1], this.mEdges[1][0][0], this.mEdges[0][0][0],
			this.mNormalVectors[1][1], Color.WHITE);
	this.mArea[1][0] = top;

	Square bot = new Square(this.mEdges[0][1][1], this.mEdges[1][1][1], this.mEdges[1][1][0], this.mEdges[0][1][0],
			this.mNormalVectors[1][0], Color.YELLOW);
	this.mArea[1][1] = bot;
		
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
		/////////////////////////////////////////////
		// ist noch abzuaendern
		this.mUnitVectors[0] = other.mUnitVectors[0];		
		this.mUnitVectors[1] = other.mUnitVectors[1];
		this.mUnitVectors[2] = other.mUnitVectors[2];	
		this.edgeLength = other.edgeLength;
	}
	
	
	
	public void calculateNomalVector(){ // TODO Quelle https://www.demo2s.com/java/java-vector-get-the-normal-vector-of-3-points-that-lie-on-a-plane.html
		
		

		this.mNormalVectors[0][0] = mUnitVectors[0];			//positive x Richtung
		this.mNormalVectors[1][0] = mUnitVectors[1];			//positive Y Richtung
		this.mNormalVectors[2][0] = mUnitVectors[2];			//positive Z Richtung
		

		this.mNormalVectors[0][1] = mUnitVectors[3];
		this.mNormalVectors[1][1] = mUnitVectors[4];
		this.mNormalVectors[2][1] = mUnitVectors[5];
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
	
	//public void rotateCube(RotMatrix rm) {
	public void rotateCube() {
//		RotMatrix rx = RotMatrix.xRotMatrix( );
//		RotMatrix ry = RotMatrix.yRotMatrix( );
//		RotMatrix rz = RotMatrix.zRotMatrix( );
		RotMatrix xm = new RotMatrix();
		RotMatrix ym = new RotMatrix();
		RotMatrix zm = new RotMatrix();
		xm = RotMatrix.xRotMatrix(Math.PI/300);
		ym = RotMatrix.yRotMatrix(Math.PI/300);
		zm = RotMatrix.zRotMatrix(Math.PI/300);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					
					mEdges[i][j][k].rotateVector(xm);								
					mEdges[i][j][k].rotateVector(ym);
					mEdges[i][j][k].rotateVector(zm);	

				}
			}
		}
	
		//System.out.println("VOR VOR:" + "i,j: "+ 0 + " " + 1 +"; x :"+mNormalVectors[0][1].getX()+"y :"+mNormalVectors[0][1].getY()+"z :"+mNormalVectors[0][1].getZ());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
//				mNormalVectors[i][j].rotateVector(xm);
				
				//System.out.println("VORHER: "  + "i,j: "+ i + " " + j +"; x :" +mNormalVectors[i][j].getX()+"y :"+mNormalVectors[i][j].getY()+"z :"+mNormalVectors[i][j].getZ());
				//mNormalVectors[i][j].rotateVector(ym);
				//System.out.println("NACHHER:" + "i,j: "+ i + " " + j +"; x :"+mNormalVectors[i][j].getX()+"y :"+mNormalVectors[i][j].getY()+"z :"+mNormalVectors[i][j].getZ());

//				mNormalVectors[i][j].rotateVector(zm);
			}
		}
		// Besser weil nicht 2 loops
		for (int i = 0; i < mUnitVectors.length; i++) {
			mUnitVectors[i].rotateVector(xm);
			mUnitVectors[i].rotateVector(ym);
			mUnitVectors[i].rotateVector(zm);
		}
		
//		this.mUnitVectors[0].rotateVector(xm);
//		this.mUnitVectors[1].rotateVector(xm);
//		this.mUnitVectors[2].rotateVector(xm);
//
//		this.mUnitVectors[0].rotateVector(ym);
//		this.mUnitVectors[1].rotateVector(ym);
//		this.mUnitVectors[2].rotateVector(ym);
//
//		this.mUnitVectors[0].rotateVector(zm);
//		this.mUnitVectors[1].rotateVector(zm);
//		this.mUnitVectors[2].rotateVector(zm);

	//	this.setSquares();
		//calculateNomalVector();
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