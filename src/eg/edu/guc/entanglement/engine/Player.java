package eg.edu.guc.entanglement.engine;

import java.util.ArrayList;

public class Player {

	private int playerID;
	// the id of the player (its location in the array of players defined in the
	// board class).
	private int opening;
	private int activeTile;
	// the id of the tile which we can do the actions on.
	// private ArrayList<Integer> openingPath;
	// stores the end ID of the openings the player chose.
	private ArrayList<Integer> tilesPath;
	// shows the activated tiles (tiles the player went on).
	private int score;
	private Board board;
	// the board that the player plays on.
	private int lastEndID;
	private boolean isGameOver;
	private int firstLocation;


	public Player(int id, Board board, int location) {
		this.playerID = id;
		this.board = board;
		this.score = 0;
		// this.openingPath = new ArrayList<Integer>();
		this.tilesPath = new ArrayList<Integer>();
		this.firstLocation = location;
		this.lastEndID = 0;
		this.opening = 0;
		System.out.println("the id is " + id);
		switch (id) {
		case 0:
			this.activeTile = location - this.board.getWidth();
			this.opening = (this.board.getOpeningsPerSide() * 3) - 1;
			break;
		case 1:
			this.activeTile = location + 1;
			this.opening = (this.board.getOpeningsPerSide() * 4) - 1;
			break;
		case 2:
			this.activeTile = location + this.board.getWidth();
			this.opening = (this.board.getOpeningsPerSide() * 1) - 1;
			break;
		case 3:
			this.activeTile = location - 1;
			this.opening = (this.board.getOpeningsPerSide() * 2) - 1;
			break;
		default:
			System.out.println("hi");
			break;
		}
		this.isGameIntiallyOver();
	}
	private void isGameIntiallyOver()
	{
		if (this.playerID == 0)
		{
			if (this.isOnTop(this.firstLocation))
			{
				this.isGameOver = true;

			}
		}
		if (this.playerID == 1)
		{
			if (this.isOnRightSide(this.firstLocation))
			{
				this.isGameOver = true;
			
			}
		}
		if (this.playerID == 2)
		{
			if (this.isOnBottom(this.firstLocation))
			{
				this.isGameOver = true;
				
			}
		}
		if (this.playerID == 3)
		{
			if (this.isOnLeftSide(this.firstLocation))
			{
				this.isGameOver = true;
				
			}
		}
	}

	private boolean isOnLeftSide(int tileID) {
		return ((tileID % this.board.getWidth()) == 0);
	}

	private boolean isOnRightSide(int tileID) {
		return (((tileID + 1) % this.board.getWidth()) == 0);
	}

	private boolean isOnTop(int tileID) {
		return (tileID < this.board.getWidth());
	}

	private boolean isOnBottom(int tileID) {
		return (tileID >= (this.board.getWidth() * (this.board.getHeight() - 1)));
	}

	public void setPlayerStartOpening() {
		this.opening = this.playerID * this.board.getOpeningsPerSide();
	}

	private int[] getTilePosition(int index) {
		int[] position = new int[2];
		int row = index / this.board.getWidth();
		int column = index - (row * this.board.getWidth());
		position[0] = row;
		position[1] = column;
		return position;
	}

	public void fixTile() {

		if (!this.isGameOver) {
			int[] position = this.getTilePosition(this.activeTile);
			System.out.println("intitial location is " + this.activeTile);
			System.out.println("this referes to " + position[0] + "  "
					+ position[1]);
			System.out.println("initial lastEndID is " + this.lastEndID);
			System.out.println("initial opening is " + this.opening);
			this.lastEndID = this.board.getTile(position).getOpeningEndID(
					this.opening);
			System.out.println("final lastEndID is " + this.lastEndID);
			Integer a = new Integer(this.activeTile);
			this.tilesPath.add(a);
			this.board.getTilesPath().add(a);
			this.board.getOpenings().add(this.opening);
			this.activeTile = this.simulateTilePath();
			System.out.println("final location is tile number "
					+ this.activeTile);
				this.convertEndToStart();
			System.out.println("final opening is " + this.opening);
			System.out.println("score was" + this.score);
			this.score = this.score + 1;
			System.out.println(" the player ID is " + this.playerID);
			System.out.println("the score is " + this.score);
			System.out.println("Is the game over ? " + this.isGameOver);
			System.out
					.println("---------------------- tiles path --------------------------");
			this.tilePathPrinter();
			for (int i = 0; i < this.board.getTilesPath().size(); i++)
				{
				      System.out.println(this.board.getTilesPath().get(i));
				}
			if (this.board.isActivated(this.activeTile)) {
				System.out.println("found it");
				this.fixTile();
				System.out.println("player swapped?");
			}
			this.isGameOver();
			for (int i = 0; i < this.board.getNumberOfPlayers(); i++)
			{
				if (i != this.playerID)
				{
					this.board.getPlayer(i).forceFixTile();
				}
			}
		}
	}
	private void forceFixTile()
	{
		if (this.board.isActivated(this.activeTile))
			{
                   		this.fixTile();
			}
	}


	private void tilePathPrinter() {
		for (int i = 0; i < this.tilesPath.size(); i++) {
			System.out.println("the tile path " + i + "  "
					+ this.tilesPath.get(i));
		}
	}





	public int getScore() {
		return this.score;
	}

	public boolean getIsGameOver() {
		return this.isGameOver;
	}

	private boolean isOnLeftSide() {
		return ((activeTile % this.board.getWidth()) == 0);
	}

	private boolean isOnRightSide() {
		return (((activeTile + 1) % this.board.getWidth()) == 0);
	}

	private boolean isOnTop() {
		return (activeTile < this.board.getWidth());
	}

	private boolean isOnBottom() {
		return (activeTile >= (this.board.getWidth() * (this.board.getHeight() - 1)));
	}


	private void isGameOver() {

		if (!this.isGameOver)
		{
			if ((this.firstLocation == this.activeTile)) {
				this.isGameOver = true;
			}
			for (int i = 0; i < this.board.getNumberOfPlayers(); i++) {
				if (i == this.playerID) {
					continue;
				}
				if ((this.lastEndID == this.board.getPlayer(i).getOpening())
						&& (this.activeTile == this.board.getPlayer(i)
								.getActiveTile())) {
					this.isGameOver = true;
					this.board.getPlayer(i).setIsGameOver(true);
					System.out.println("players did hit");
				}
			}	
		}
		

	}

	public void setIsGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public int getLastEndID() {
		return this.lastEndID;
	}

	public int getActiveTile() {
		return this.activeTile;
	}

	public void switchTile(int tileID) {
		this.isGameOver();
		if (!this.isGameOver) {
			int[] position = this.getTilePosition(this.activeTile);
			Tile t = this.board.getTile(tileID);
			
			int id = this.board.getTile(position).getTileID();
			int [] config = t.getConfig();
			Tile switched = new Tile(config);
			switched.setTileID(id);
			
			this.board.setTile(switched, position);
		}
	}

	public void rotateClockWise() {
		if (!this.isGameOver) {
			int[] position = this.getTilePosition(this.activeTile);
			this.board.getTile(position).rotateClockWise();
		}
	}

	public void rotateAntiClockWise() {
		if (!this.isGameOver) {
			int[] position = this.getTilePosition(this.activeTile);
			this.board.getTile(position).rotateAntiClockWise();
		}
	}

	private int simulateTilePath() {
		this.isGameOver();
		if (!this.isGameOver) {

			int totalOpenings = 4 * this.board.getOpeningsPerSide();
			System.out.println("total opening is " + totalOpenings);
			if (lastEndID < (totalOpenings / 4)) {
				if (this.isOnTop())
				{
					this.isGameOver = true;
				}
				return this.activeTile - this.board.getWidth();
			}
			if (lastEndID < (totalOpenings / 2)) {
				if (this.isOnRightSide())
				{
					this.isGameOver = true;
				}
				System.out.println("hi there");
				return this.activeTile + 1;
			}
			if (lastEndID < ((3 * totalOpenings) / 4)) {
				if (this.isOnBottom())
				{
					this.isGameOver = true;
				}
				return this.activeTile + this.board.getWidth();
			}
			if (this.isOnLeftSide())
			{
				this.isGameOver = true;
			}
			return this.activeTile - 1;
		}
		return this.activeTile;
	}
	public int getOpening()
	{
		return this.opening;
	}

	private void convertEndToStart() {

		int totalOpenings = 4 * this.board.getOpeningsPerSide();
		if (lastEndID >= ((3 * totalOpenings) / 4)) {
			this.opening = ((5 * this.board.getOpeningsPerSide() - 1) - lastEndID);
		} else {
			if (lastEndID >= (totalOpenings / 2)) {
				this.opening = ((3 * this.board.getOpeningsPerSide() - 1) - lastEndID);
			} else {
				if (lastEndID >= (totalOpenings / 4)) {
					this.opening = ((5 * this.board.getOpeningsPerSide() - 1) - lastEndID);
				} else {
					this.opening = ((3 * this.board.getOpeningsPerSide() - 1) - lastEndID);
				}
			}
		}
	}

}
