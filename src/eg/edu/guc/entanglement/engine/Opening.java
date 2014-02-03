package eg.edu.guc.entanglement.engine;

public class Opening {
	
	private int startID;
	private int endID;
	// defines which points are connected together.
	
	public int getStartID() {
		return startID;
	}
	public int getEndID() {
		return endID;
	}
	
	public Opening(int startID, int endID)
	{
		this.startID = startID;
		this.endID = endID;
	}
	public void rotateClockWise(int numberOfOpenings)
	{
		this.startID = (this.startID + (numberOfOpenings / 4)) % numberOfOpenings;
		this.endID = (this.endID + (numberOfOpenings / 4)) % numberOfOpenings;
		// by rotating a tile clockwise. the openings ID has to change.
	}
	
	
	public String toString()
	{
		return "the start ID is " + this.startID + "\n the end ID is " + this.endID;
	}

}
