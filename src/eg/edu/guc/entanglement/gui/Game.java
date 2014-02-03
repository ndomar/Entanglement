package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

@SuppressWarnings("serial")
public  class Game extends JPanel implements MouseListener {

	private JFrame frame;
	private int players;
	private ActiveGame activeGame;
	private JPanel scorePanel;
	private JPanel gameOverPanel;
	private ArrayList<GameOver> gameStatus;
	private JPanel actionsPanel;
	private JButton rotateClock;
	private JButton rotateAntiClock;
	private JPanel switchTile;
	private ArrayList<JButton> switchButtons;
	private BoardConfiguration bc;
	private JButton restart;

	public  Game(JFrame frame, int players, BoardConfiguration bc) {
		this.bc = bc;
		this.frame = frame;
		this.players = players;
		this.gameStatus = new ArrayList<GameOver>();
		this.setSize(frame.getSize());
		this.setLocation(20, 20);
		this.setLayout(new BorderLayout(20, 20));
		this.activeGame = new ActiveGame(this.frame, this.players, this,
				this.bc);
		this.add(activeGame, BorderLayout.CENTER);
		this.scorePanel = new JPanel();
		this.scorePanel.setLayout(new GridLayout(0, players + 1));
		this.gameOverPanel = new JPanel();
		
		for (int i = 1; i <= players; i++) {
			
			if (players != 1)
			{
				this.gameOverPanel.setLayout(new GridLayout(players + 1, players, 20, 20));
				scorePanel.add(new JLabel("player " + i + " score is "
						+ this.activeGame.getActiveBoard().getScore(i - 1)));
				gameOverPanel
						.add(new JLabel("Player " + i + " game over state is "));
				boolean isGameOver = this.activeGame.getActiveBoard().isGameOver(
						i - 1);
				if (isGameOver) {
					this.gameStatus.add(new GameOver("red"));
				} else {
					this.gameStatus.add(new GameOver("green"));
				}
				gameOverPanel.add(this.gameStatus.get(i - 1));
			}
			else
			{
				this.gameOverPanel.setLayout(new GridLayout(3, 2, 20, 20));
				scorePanel.add(new JLabel("player " + i + " score is "
						+ this.activeGame.getActiveBoard().getScore(i - 1)));
				gameOverPanel
						.add(new JLabel("Player " + i + " game over state is "));
			//	gameOverPanel
			//	.add(new JLabel("rest " + i + " game over state is "));
				boolean isGameOver = this.activeGame.getActiveBoard().isGameOver(
						i - 1);
				if (isGameOver) {
					this.gameStatus.add(new GameOver("red"));
				} else {
					this.gameStatus.add(new GameOver("green"));
				}
				gameOverPanel.add(this.gameStatus.get(i - 1));
			}

		}
		this.scorePanel.add(new JLabel("it is Player "
				+ (this.activeGame.getActiveBoard().getActivePlayer() + 1)
				+ " turn"));
		this.actionsPanel = new JPanel();
		this.actionsPanel.setLayout(new GridLayout(3, 1, 20, 20));
		this.rotateClock = new JButton("Rotate ClockWise");
		this.rotateClock.addMouseListener(this);
		this.rotateClock.setBackground(Color.white);
		this.rotateClock.setIcon(new ImageIcon("clock.png"));
		 this.rotateClock.setVerticalTextPosition(SwingConstants.TOP);
         this.rotateClock.setHorizontalTextPosition(SwingConstants.CENTER);
		this.rotateAntiClock = new JButton("Rotate AntiClockWise");
		this.rotateAntiClock.addMouseListener(this);
		this.rotateAntiClock.setBackground(Color.white);
		this.rotateAntiClock.setIcon(new ImageIcon("anti.png"));
		 this.rotateAntiClock.setVerticalTextPosition(SwingConstants.TOP);
         this.rotateAntiClock.setHorizontalTextPosition(SwingConstants.CENTER);
		this.switchTile = new JPanel(new GridLayout(this.activeGame.getNumberOfTiles() + 1, 1
				, 10, 10));
		this.switchButtons = new ArrayList<JButton>();
		System.out.println("Number of Tiles = "
				+ this.activeGame.getNumberOfTiles());
		this.switchTile.add(new JLabel("Click on a tile to switch to it"));
		for (int i = 0; i < this.activeGame.getNumberOfTiles(); i++) {
			TileGUI button = new TileGUI(this.activeGame.getActiveBoard()
					.getTileSwitch(i).getConfig());
			button.activeDraw();
			
			button.addMouseListener(this);
			button.setSize(50, 50);
			this.switchButtons.add(button);
			this.switchTile.add(button);
		}
		this.restart = new JButton("Exit to menu");
		this.restart.addMouseListener(this);
		this.restart.setBackground(Color.white);
		this.restart.setIcon(new ImageIcon("exit.svg.png"));
		 this.restart.setVerticalTextPosition(SwingConstants.TOP);
         this.restart.setHorizontalTextPosition(SwingConstants.CENTER);
		this.actionsPanel.add(this.rotateClock);
		this.actionsPanel.add(this.rotateAntiClock);
		this.actionsPanel.add(restart);
		this.add(scorePanel, BorderLayout.NORTH);
		this.add(gameOverPanel, BorderLayout.SOUTH);
		this.add(actionsPanel, BorderLayout.EAST);
		this.add(switchTile, BorderLayout.WEST);
		this.addMouseListener(this);
		//this.frame.setSize(900, 700);
	}


	public void updateScores() {
		this.scorePanel.removeAll();
		for (int i = 1; i <= players; i++) {
			System.out.println("player " + i + " score is "
					+ this.activeGame.getActiveBoard().getScore(i - 1));
			int score = this.activeGame.getActiveBoard().getScore(i - 1);
			System.out.println("score is " + i + " " + score);
			scorePanel.add(new JLabel("player " + i + " score is "
					+ this.activeGame.getActiveBoard().getScore(i - 1)));
		}
		this.scorePanel.add(new JLabel("it is Player "
				+ (this.activeGame.getActiveBoard().getActivePlayer() + 1)
				+ " turn"));
		this.updateGameOver();

	}

	public void checkGameOver() {
		if (this.activeGame.getActiveBoard().isAllGameOver()) {
			/*
			 * this.clearPanel(); JPanel newPanel = new JPanel(new
			 * GridLayout((players + 1), 1)); JLabel gameOver = new
			 * JLabel("Game is Over"); newPanel.add(gameOver); for (int i = 0; i
			 * < players; i++) { int score =
			 * this.activeGame.getActiveBoard().getScore(i); JLabel message =
			 * new JLabel("Score of player " + (i + 1) + " was " + score);
			 * newPanel.add(message); } this.add(newPanel);
			 */
			this.clearPanel();
			this.add(new GameOverPanel(this.frame, this.bc, players, this));

		}
	}

	public ActiveGame getActiveGame() {
		return this.activeGame;
	}

	public void updateGameOver() {
		// this.gameOverPanel.removeAll();
		// for (int i = 1; i <= players; i++)
		// {
		// gameOverPanel.add(new JLabel ("Player " + i +
		// " game over state is "));
		// }
		for (int i = 0; i < players; i++) {
			boolean isGameOver = this.activeGame.getActiveBoard().isGameOver(i);
			if (isGameOver) {
				this.gameStatus.get(i).setColor("red");
			} else {
				this.gameStatus.get(i).setColor("green");
			}
			// gameOverPanel.add(new
			// JLabel(this.activeGame.getActiveBoard().isGameOver(i - 1) +
			// " "));
		}

	}

	public void mouseClicked(MouseEvent event) {
		this.updateScores();
		Object obj = event.getSource();
		if (obj instanceof JButton) {
			JButton clicked = (JButton) obj;
			if (clicked == this.rotateClock) {
				int player = this.activeGame.getActiveBoard().getActivePlayer();
				int tile = this.activeGame.getActiveBoard().getPlayer(player)
						.getActiveTile();
				int row = tile / this.activeGame.getActiveBoard().getWidth();
				int column = tile % this.activeGame.getActiveBoard().getWidth();
				this.activeGame.getTile(new int[] {row, column })
						.rotateClockwise();
				this.activeGame.getActiveBoard().rotateTileClockwise();
			}
			if (clicked == this.rotateAntiClock) {
				int player = this.activeGame.getActiveBoard().getActivePlayer();
				System.out.println("active player is " + player);
				int tile = this.activeGame.getActiveBoard().getPlayer(player)
						.getActiveTile();
				int row = tile / this.activeGame.getActiveBoard().getWidth();
				int column = tile % this.activeGame.getActiveBoard().getWidth();
				this.activeGame.getTile(new int[] {row, column })
						.rotateAntiClockwise();
				this.activeGame.getActiveBoard().rotateTileAntiClockwise();
			}
			if (clicked == this.restart)
			{
				this.clearPanel();
				//this.frame.setSize(800, 600);
				this.add(new HomePanel(this.frame, this.bc));
			}
			for (int i = 0; i < this.switchButtons.size(); i++) {
				if (clicked == this.switchButtons.get(i)) {
					this.activeGame.getActiveBoard().switchTile(i);
					int player = this.activeGame.getActiveBoard()
							.getActivePlayer();
					int tile = this.activeGame.getActiveBoard()
							.getPlayer(player).getActiveTile();
					int row = tile
							/ this.activeGame.getActiveBoard().getWidth();
					int column = tile
							% this.activeGame.getActiveBoard().getWidth();
					int[] config = this.activeGame.getActiveBoard()
							.getTileSwitch(i).getConfig();
					this.activeGame.getTile(new int[] {row, column })
							.setTileConfig(config);
					this.repaint();
					this.revalidate();
					break;
				}
			}
		}
	}


	public  void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
	
	public void clearPanel() {
		this.removeAll();
		this.validate();
		this.repaint();

	}

}
