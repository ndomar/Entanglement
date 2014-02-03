package eg.edu.guc.entanglement.gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import eg.edu.guc.entanglement.engine.*;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ActiveGame extends JPanel implements MouseListener {
	
	private TileGUI[][] tiles;
	private JFrame frame;
	private Board activeBoard;
	private Game game;
	private int numberOfTiles;
	private BoardConfiguration bc;
	
	public Board getActiveBoard() {
		return activeBoard;
	}
	public TileGUI getTile(int[] position)
	{
		return this.tiles[position[0]][position[1]];
	}
	public int getNumberOfTiles()
	{
		return this.numberOfTiles;
	}
	public void setActiveBoard(Board activeBoard) {
		this.activeBoard = activeBoard;
	}
	public ActiveGame(JFrame frame, int numberOfPlayers, Game game, BoardConfiguration bc)
	{
		try {
			this.bc = bc;
			this.setFrame(frame);
			this.game = game;
			//this.setSize(800, 600);
			this.setLocation(200, 200);
			String output = this.bc.getReader() + numberOfPlayers;
			System.out.println("------------ config -------------");
			System.out.println(output);
			this.activeBoard = new Board(new StringReader(output));
			this.tiles = new TileGUI[activeBoard.getWidth()][activeBoard.getHeight()];
			this.setLayout(new GridLayout(this.tiles.length, this.tiles[0].length));
			int counter = 0;
			for (int i = 0; i < this.tiles.length; i++)
			{
				 for (int j = 0; j < this.tiles[i].length; j++) {
					 //this.tiles[i][j] = new ActiveTile(new int[] {5 ,4 ,7 ,6, 1, 0, 3 ,2});
					 this.tiles[i][j] = new TileGUI();
					 if (counter == activeBoard.getStartLocation())	 {
						 this.tiles[i][j].setIsCenter(true);
					 }
					 else {
						this.tiles[i][j].setTileConfig(activeBoard.getTile(
								new int[] {i, j }).getConfig());
						 this.tiles[i][j].createIsFixed();
					 }
					 
					 this.add(this.tiles[i][j]);
					 this.tiles[i][j].addMouseListener(this);
					 counter++;
				 }
			}
			this.drawActiveTiles();
			this.numberOfTiles = this.activeBoard.getNumberOfTiles();
			this.revalidate();
			this.repaint();
		}
		catch (Exception e) 	{
			e.printStackTrace();
		}
	}
	public void drawActiveTiles()
	{
		int players = this.activeBoard.getNumberOfPlayers();
		int activeTile = 0;
		for (int i = 0; i < players; i++)
		{
			
			
			if (!this.activeBoard.isGameOver(i))
			{
				activeTile = this.activeBoard.getPlayer(i).getActiveTile();
				System.out.println("active Tile = "  + activeTile);
				this.tiles[activeTile / this.tiles.length][activeTile
						% this.tiles.length].activeDraw();
				System.out.println("active tile " + activeTile + " for player " + i);
			}
			
			
		}
		this.reAdd();
		this.repaint();
		
		
	}

	/*
	 * public void drawActiveTiles() { int players =
	 * this.activeBoard.getNumberOfPlayers(); int activeTile = 0; for (int i =
	 * 0; i < players; i++) {
	 * 
	 * 
	 * activeTile = this.activeBoard.getPlayer(i).getActiveTile(); int row =
	 * activeTile / this.tiles.length; int column = activeTile %
	 * this.tiles.length; this.tiles[row][column] = new
	 * ActiveTile(this.activeBoard.getTile(new int[] {row,
	 * column}).getConfig()); this.tiles[row][column].repaint();
	 * this.tiles[row][column].revalidate();
	 * 
	 * System.out.println("active tile " + activeTile + " for player " + i);
	 * ActiveTile as = (ActiveTile) this.tiles[row][column];
	 * System.out.println(as.paintCounter);
	 * 
	 * } this.reAdd();
	 * 
	 * }
	 */
	public void reAdd()
	{
		this.removeAll();
		for (int i = 0; i < this.tiles.length; i++)
		{
			 for (int j = 0; j < this.tiles[i].length; j++)
			 { 
				 this.add(this.tiles[i][j]);
			 }
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj instanceof TileGUI)
		{
			TileGUI clicked = (TileGUI) obj;
			int clickedRow = 0;
			int clickedColumn = 0;
			for (int i = 0; i < this.tiles.length; i++)
			{
				for (int j = 0; j < this.tiles.length; j++)
				{
					if (clicked == this.tiles[i][j])
					{
						clickedRow = i;
						clickedColumn = j;
					}
				}
			}
			int activePlayer = this.activeBoard.getActivePlayer();
			System.out.println("players = " + this.activeBoard.getNumberOfPlayers());
			int activeTile = this.activeBoard.getPlayer(activePlayer).getActiveTile();
			int row = this.activeBoard.getTilePosition(activeTile)[0];
			int column = this.activeBoard.getTilePosition(activeTile)[1];
			if ((row == clickedRow) && (column == clickedColumn))
			{
				int activeOpening = this.activeBoard.getPlayer(activePlayer).getOpening();
				this.tiles[row][column].fixDraw(activeOpening, activePlayer);
				this.activeBoard.fixTile();
				java.awt.Toolkit.getDefaultToolkit().beep();

				this.drawLoopBack(activePlayer);
				System.out.println("tile fixed");
				if (!this.activeBoard.isGameOver(activePlayer))
				{
					this.drawActiveTiles();

				}
			}
			
			
			
		}
		this.game.updateScores();
		this.game.checkGameOver();
	}
	public void drawLoopBack(int player)
	{
		ArrayList<Integer> tiles = this.activeBoard.getTilesPath();
		ArrayList<Integer> openings = this.activeBoard.getOpenings();
		//int activePlayer = this.activeBoard.getActivePlayer();
		for (int i = 0; i < tiles.size(); i++)
		{
			int position = (int) tiles.get(i);
			int row = position / this.activeBoard.getWidth();
			int column = position % this.activeBoard.getHeight();
			int opening = (int) openings.get(i);
			this.tiles[row][column].setIsFixed(opening);
			this.tiles[row][column].fixDraw(opening, player);
		}
	}
	public void mouseEntered(MouseEvent e) {
		
		
	}
	public void mouseExited(MouseEvent e) {
		
		
	}
	public void mousePressed(MouseEvent e) {
		
		
	}
	public void mouseReleased(MouseEvent e) {
		
		
	}
	public void clearPanel()
	{
		this.removeAll();
		this.validate();
		this.repaint();
		
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JFrame getFrame() {
		return frame;
	}



}
