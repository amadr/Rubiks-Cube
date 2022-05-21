import java.awt.Color;

/**
 * class description
 */

public class Cube implements Comparable<Cube> {
	private double edgeLength;
	
	private Vector3D mToMid;

	// unit/ normal vectors
	public Vector3D[] mUnitVectors   = new Vector3D[6];
	
	public Vector3D [][][] mEdges = new Vector3D [2][2][2];
	
	public Square [][] mArea = new Square [3][2];
	
	public Cube ()		 // leerer Konstruktor
	{	
		// TODO
	}
	
	public Cube (Vector3D toMid) {	
    this.edgeLength = 135;	

		this.mToMid = toMid; // Vector mit Ausrichtung auf Mittelpunkt (0,0,0)
		this.mUnitVectors[0] = new Vector3D(1,0,0);	
		this.mUnitVectors[1] = new Vector3D(0,1,0);
		this.mUnitVectors[2] = new Vector3D(0,0,1);
		this.mUnitVectors[3] = new Vector3D(-1,0,0);
		this.mUnitVectors[4] = new Vector3D(0,-1,0);
		this.mUnitVectors[5] = new Vector3D(0,0,-1);
		
		/*
		 * ulf -> upper left front
		 * urf -> ...
		 * ...
		 */
		Vector3D ulf = new Vector3D();
		ulf.copyVector(toMid);
		ulf.subtractVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		ulf.subtractVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));	
		ulf.subtractVector(this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
		this.mEdges[0][0][0] = ulf;
		
		Vector3D urf = new Vector3D();
		urf.copyVector(toMid);
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
		
		Vector3D lrf= new Vector3D();
		lrf.copyVector(toMid);
		lrf.addVector(this.mUnitVectors[0].getScaledVector(0.5*this.edgeLength));
		lrf.addVector(this.mUnitVectors[1].getScaledVector(0.5*this.edgeLength));
		lrf.subtractVector(this.mUnitVectors[2].getScaledVector(0.5*this.edgeLength));
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
		
		// ############### Areas erstellen ##########	
		this.setSquares();
	}
	
public void setSquares() {
		
	Square front = new Square(this.mEdges[0][0][0], this.mEdges[1][0][0], this.mEdges[1][1][0], this.mEdges[0][1][0],
			this.mUnitVectors[5], Color.GREEN);
	this.mArea[2][0] = front;

	Square back = new Square(this.mEdges[0][0][1], this.mEdges[1][0][1], this.mEdges[1][1][1], this.mEdges[0][1][1],
			this.mUnitVectors[2], Color.BLUE);
	this.mArea[2][1] = back;

	Square left = new Square(this.mEdges[0][0][1], this.mEdges[0][0][0], this.mEdges[0][1][0], this.mEdges[0][1][1],
			this.mUnitVectors[3], Color.ORANGE);
	this.mArea[0][1] = left;

	Square right = new Square(this.mEdges[1][0][1], this.mEdges[1][0][0], this.mEdges[1][1][0], this.mEdges[1][1][1],
			this.mUnitVectors[0], Color.RED);
	this.mArea[0][0] = right;

	Square top = new Square(this.mEdges[0][0][1], this.mEdges[1][0][1], this.mEdges[1][0][0], this.mEdges[0][0][0],
			this.mUnitVectors[4], Color.WHITE);
	this.mArea[1][0] = top;

	Square bot = new Square(this.mEdges[0][1][1], this.mEdges[1][1][1], this.mEdges[1][1][0], this.mEdges[0][1][0],
			this.mUnitVectors[1], Color.YELLOW);
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
	
	public Vector3D []getNormalVectors(){
		return this.mUnitVectors;
	}
	
	public void copyCube(Cube other) {
		this.mToMid.copyVector(mToMid);
		for (int i = 0; i < mUnitVectors.length; i++) {
			this.mUnitVectors[i].copyVector(other.mUnitVectors[i]);
			//this.mUnitVectors[i] = other.mUnitVectors[i];
		}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					this.mEdges[i][j][k].copyVector(other.mEdges[i][j][k]);
				}
			}
		}
		//this.edgeLength = other.edgeLength;
	}
	
	public void rotateCube() {
		RotMatrix xm = new RotMatrix();
		RotMatrix ym = new RotMatrix();
		RotMatrix zm = new RotMatrix();
		xm = RotMatrix.xRotMatrix(-Math.PI/4);
		ym = RotMatrix.yRotMatrix(-Math.PI/4);
		zm = RotMatrix.zRotMatrix(-Math.PI/4);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
//					mEdges[i][j][k].rotateVector(xm);								
					mEdges[i][j][k].rotateVector(ym);
//					mEdges[i][j][k].rotateVector(zm);	

				}
			}
		}
	
		for (int i = 0; i < mUnitVectors.length; i++) {
//			mUnitVectors[i].rotateVector(xm);
			mUnitVectors[i].rotateVector(ym);
//			mUnitVectors[i].rotateVector(zm);
		}
	}
	
	@Override
    public int compareTo (Cube other){
  		if (this.mToMid.getZ() < other.mToMid.getZ()) {
			return 1; 
		} 
		else if (this.mToMid.getZ() > other.mToMid.getZ()) {
			return -1; 
		} 
		else {
			return 0; 
		}
    }	
	
	public void rotateCube(RotMatrix rm) {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					mEdges[i][j][k].rotateVector(rm);
					//System.out.println(" DAVOR: x: " + this.mToMid.getX() + ", y: " + this.mToMid.getY() + ",z: " + this.mToMid.getZ());
					//System.out.println("DANACH: x: " + this.mToMid.getX() + ", y: " + this.mToMid.getY() + ",z: " + this.mToMid.getZ());
				}
			}
		}
		this.mToMid.rotateVector(rm);
	
		for (int i = 0; i < mUnitVectors.length; i++) {
			mUnitVectors[i].rotateVector(rm);
		}
	}
}

