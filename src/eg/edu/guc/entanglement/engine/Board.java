package eg.edu.guc.entanglement.engine;

import java.io.*;
import java.util.ArrayList;


public class Board implements BoardInterface {

	private Tile[][] blocks;
	// two dimensional array of tiles, it defines the gaming board.
	private int height;
	// height of the array.
	private int width;
	// width of the array.
	private final int numberOfSides = 4;
	private int startLocation;
	// the location which the game will start from (tile ID).
	private int numberOfPlayers;
	// number of players who will play the game (maximum 4).
	private Player[] players;
	// array of players, its length is the number of players.
	private ArrayList<Tile> tileTypes;
	// array list of the tiles, using the tile configuration.
	private int openingsPerSide;
	// number of openings in the tile in one side.
	private int activePlayerID;
	private ArrayList<Integer> tilesPath;
	private ArrayList<Integer> openings;

	// index of the player in the players array who can do an action (fix rotate
	// etc).

	public int getNumberOfTiles()
	{
		return this.tileTypes.size();
	}
	public Board() {

	}
	/*public Board(java.io.Reader reader, int x)
	{
		try {
			BufferedReader br = new BufferedReader(reader);
			br.readLine();
			this.tileTypes = new ArrayList<Tile>();
			this.openingsPerSide = Integer.parseInt(br.readLine());
			int tileTypes = Integer.parseInt(br.readLine());

			for (int i = 0; i < tileTypes; i++) {
				String[] input = br.readLine().split(" ");
				int[] config = new int[input.length];
				for (int j = 0; (j < 4 * openingsPerSide); j++) {
					config[j] = Integer.parseInt(input[j]);
				}
				Tile t = new Tile(config);
				this.tileTypes.add(t);
			}
			this.width = Integer.parseInt(br.readLine());
			this.height = Integer.parseInt(br.readLine());
			this.startLocation = Integer.parseInt(br.readLine());
			this.numberOfPlayers = Integer.parseInt(br.readLine());
			this.players = new Player[this.numberOfPlayers];
			this.blocks = new Tile[this.width][this.height];
			this.blocks[0][0] = this.tileTypes.get(0);
			this.blocks[0][1] = this.tileTypes.get(1);
			this.blocks[1][0] = this.tileTypes.get(0);
			this.blocks[1][1] = this.tileTypes.get(0);
			int[] position = this.getTilePosition(this.startLocation);
			this.blocks[position[0]][position[1]] = null;
			this.tilesPath = new ArrayList<Integer>();
			for (int i = 0; i < this.numberOfPlayers; i++) {
				this.players[i] = new Player(i, this, this.startLocation);
			}
		} catch (IOException e) {
			System.out.println("wont hit here");
		}
		
	}*/


	public Board(java.io.Reader reader) {

		try {
			BufferedReader br = new BufferedReader(reader);
			br.readLine();
			this.tileTypes = new ArrayList<Tile>();
			this.openingsPerSide = Integer.parseInt(br.readLine());
			int tileTypes = Integer.parseInt(br.readLine());

			for (int i = 0; i < tileTypes; i++) {
				String[] input = br.readLine().split(" ");
				int[] config = new int[input.length];
				for (int j = 0; (j < 4 * openingsPerSide); j++) {
					config[j] = Integer.parseInt(input[j]);
				}
				Tile t = new Tile(config);
				this.tileTypes.add(t);
			}
			this.width = Integer.parseInt(br.readLine());
			this.height = Integer.parseInt(br.readLine());
			this.startLocation = Integer.parseInt(br.readLine());
			this.numberOfPlayers = Integer.parseInt(br.readLine());
			this.players = new Player[this.numberOfPlayers];
			this.blocks = new Tile[this.height][this.width];
			this.fillBoard();
			int[] position = this.getTilePosition(this.startLocation);
			System.out.println("row = " + position[0] + "column = " + position[1]);
			//System.out.println("height = " + this.height + " width = " this.width);
			this.blocks[position[0]][position[1]] = null;
			this.tilesPath = new ArrayList<Integer>();
			for (int i = 0; i < this.numberOfPlayers; i++) {
				this.players[i] = new Player(i, this, this.startLocation);
			}
			this.changeInitialGameOver();
			this.openings = new ArrayList<Integer>();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isActivated(int activeTile) {
		for (int i = 0; i < this.tilesPath.size(); i++) {
			if (activeTile == (int) (this.tilesPath.get(i))) {
				return true;
			}
		}
		return false;
	}
	

	public ArrayList<Integer> getTilesPath() {
		return tilesPath;
	}
	public ArrayList<Integer> getOpenings()
	{
		return this.openings;
	}

	public Tile getTile(int[] position) {
		return this.blocks[position[0]][position[1]];
	}

	private void fillBoard() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.blocks[j][i] = new Tile(this.tileTypes.get(
						(int) (Math.random() * this.tileTypes.size()))
						.getConfig());
				this.blocks[j][i].setTileID((this.width * i) + j);
			}
		}
	}

	// the method fills the board with random tiles which were inserted in the
	// tiles array list.
	public String toString() {
		String result = "";
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if (this.blocks[i][j] != null) {
					result = result + "for tile [ " + i + " ] [" + j + " ]\n"
							+ this.blocks[i][j].toString();
				} else {
					result = result + "the tile in position [ " + i + "] [" + j
							+ " ]\n is the initial tile";
				}

			}
		}
		result = result + "\n the width is " + this.width + "\n the height is "
				+ this.height + "\n the number of sides is "
				+ this.numberOfSides + "\n the start location is "
				+ this.startLocation + "\n the openings per side is "
				+ this.openingsPerSide;
		return result;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getOpeningsPerSide() {
		return openingsPerSide;
	}

	public int[] getTilePosition(int index) {
		int[] position = new int[2];
		position[0] = index / width;
		position[1] = index % width;
		return position;
	}

	public Tile getTile(int tileID) {
		return this.tileTypes.get(tileID);
	}

	public void setTile(Tile switched, int[] position) {
		this.blocks[position[0]][position[1]] = switched;
	}

	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	public Player getPlayer(int index) {
		return this.players[index];
	}

	public void swapActivePlayer() {
		this.activePlayerID++;
		if (this.activePlayerID >= this.numberOfPlayers) {
			this.activePlayerID = 0;
		}
		if (this.isGameOver(activePlayerID) && (!this.isAllGameOver())) {
			this.swapActivePlayer();
		}
	}
	public void changeInitialGameOver()
	{
		if (!isAllGameOver())
		{
			while (this.isGameOver(activePlayerID))
			{
				if (this.isGameOver(this.activePlayerID))
				{
					this.swapActivePlayer();
				}
			}

		}
	}

	public boolean isAllGameOver() {
		boolean result = true;
		for (int i = 0; i < this.numberOfPlayers; i++) {
			result = result && this.isGameOver(i);
		}
		return result;
	}


	public int getScore(int player) {
		try {
			return this.players[player].getScore();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("invalid input");
			return -1;
		}

	}

	public boolean isGameOver(int player) {
		try {
			return this.players[player].getIsGameOver();
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	public boolean fixTile() {
		try {
			boolean status = this.players[activePlayerID].getIsGameOver();
			if (!status) {
				this.players[activePlayerID].fixTile();

			}

			return !status;
		} catch (IndexOutOfBoundsException e) {
		

			return false;
		} finally {
			this.swapActivePlayer();
			System.out.println("i did swap the player to ID " + this.activePlayerID);
		}

	}

	public boolean switchTile(int tileID) {
		try {
			boolean status = this.players[activePlayerID].getIsGameOver();
			this.players[activePlayerID].switchTile(tileID);
			return !status;
		}

		catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	public boolean rotateTileClockwise() {
		try {
			boolean status = this.isGameOver(activePlayerID);
			System.out.println("status is " + status);
			if (!status) {
				this.players[activePlayerID].rotateClockWise();
			}
			return !status;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}
	

	public int getStartLocation() {
		return startLocation;
	}


	public void setStartLocation(int startLocation) {
		this.startLocation = startLocation;
	}


	public boolean rotateTileAntiClockwise() {
		try {
			boolean status = this.players[activePlayerID].getIsGameOver();
			this.players[activePlayerID].rotateAntiClockWise();
			return !status;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}
	public int getActivePlayer()
	{
		return this.activePlayerID;
	}
	public Tile getTileSwitch(int index)
	{
		return this.tileTypes.get(index);
	}

	public static void main(String[] args) throws IOException {

		StringReader sr = new StringReader("4\n" + "2\n" + "2\n"
				+ "1 0 3 2 5 4 7 6\n" + "5 4 7 6 1 0 3 2\n" + "5\n" + "5\n"
				+ "12\n" + "1");

		Board b = new Board(sr);
		System.out.println(b.getScore(0));
		b.fixTile();
		System.out.println(b.getScore(0));
		

	}

}
