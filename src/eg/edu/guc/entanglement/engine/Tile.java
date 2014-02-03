package eg.edu.guc.entanglement.engine;

public class Tile {

	private int tileID;
	// the position of the tile in the board.
	private Opening[] openings;
	private int[]config;
	


	public Tile() {

	}

	/*
	 * public Tile(int[] input) {
	 * 
	 * int numberOfOpeningsPerSide = input.length / this.numberOfSides;
	 * this.side0 = new Side(numberOfOpeningsPerSide, input, 0); this.side1 =
	 * new Side(numberOfOpeningsPerSide, input, 1); this.side2 = new
	 * Side(numberOfOpeningsPerSide, input, 2); this.side3 = new
	 * Side(numberOfOpeningsPerSide, input, 3); }
	 */

	public Tile(int[] input) {
		this.config = input;
		this.openings = new Opening[input.length];
		for (int i = 0; i < input.length; i++) {
			this.openings[i] = new Opening(i, input[i]);
		}
	}
	public void setTileID(int tileID)
	{
		this.tileID = tileID;
	}
	public int getTileID()
	{
		return this.tileID;
	}
	

	public int[] getConfig() {
		return config;
	}

	public void setConfig(int[] config) {
		this.config = config;
	}

	public void rotateClockWise() {
		for (int i = 0; i < this.openings.length; i++) {
			openings[i].rotateClockWise(this.openings.length);
		}
		this.sortOpenings();
		// rotate clock wise is done by changing each opening startID and endID.
	}

	public void rotateAntiClockWise() {
		for (int i = 0; i < 3; i++) {
			this.rotateClockWise();
		}
		// rotating the tile 3 times clock wise is  equivalent
		// to rotating it one time anti clock wise. 
	}

	public void sortOpenings()
	{
		Opening[] sortedOpenings = new Opening[this.openings.length]; 
		for (int i = 0; i < this.openings.length; i++)
		{  
			for (int j = 0; j < this.openings.length; j++)
			{
				if (this.openings[j].getStartID() == i)
				{
					sortedOpenings[i] = new Opening(i, this.openings[j].getEndID());
					break;
				}
			}
		}
		this.openings = sortedOpenings;
	}
	// the sort Openings method sorts the array of openings such that the start ID of each opening
	// refers to its position in the array.
	public String toString() {
		String result = "the number of openings is " + this.openings.length
				+ "\n";
		for (int i = 0; i < this.openings.length; i++) {
			result = result + "\nfor opening " + i + "\n" + this.openings[i].toString() + "\n";
		}
		return result + "\n the Tile ID is " + this.tileID + "\n";
	}
	public int getOpeningEndID(int opening)
	{
		return this.openings[opening].getEndID();
	}
	public int getOpeningStartID(int opening)
	{
		return this.openings[opening].getStartID();
	}
	

	public static void main(String[] args) {
		int[] x = {4, 3, 5, 1, 0, 2, 7, 6};
		Tile a = new Tile(x);
		a.rotateClockWise();
		a.rotateClockWise();
		System.out.println(a.toString());
		System.out.println(1 % 3);
	}

}
