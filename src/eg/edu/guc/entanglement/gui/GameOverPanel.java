package eg.edu.guc.entanglement.gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel implements MouseListener {
	
	private JButton playAgain;
	private Image image;
	private JFrame frame;
	private BoardConfiguration bc;
	private int players;
	private Game game;
	
	public GameOverPanel(JFrame frame, BoardConfiguration bc, int players, Game game)
	{
		ImageIcon icon = new ImageIcon("ent2.jpg");
		this.game = game;
		this.setImage(icon.getImage());
		this.frame = frame;
		this.setPlayers(players);
		this.bc = bc;
		this.setLayout(null);
		this.setSize(900, 700);
		this.setLocation(20, 20);

		JLabel gameOver = new JLabel("Game is Over");

		this.add(gameOver);
		for (int i = 0; i < players; i++)
		{
			int score = this.game.getActiveGame().getActiveBoard().getScore(i);
			JLabel message = new JLabel("Score of player " + (i + 1) + " was " + score);
			message.setBounds(300, 40 + (100 * i), 200, 80);
			this.add(message);
		}
	    this.playAgain = new TransparentButton("Play Again");
		playAgain.setBounds(300, 500, 200, 80);
		this.playAgain.addMouseListener(this);
		this.add(playAgain);
	}

	public void clearPanel()
	{
		this.removeAll();
		this.validate();
		this.repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof JButton)
		{
			this.clearPanel();
			
			this.add(new HomePanel(this.frame, this.bc));
		}
		
	}
	public  void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setPlayers(int players) {
		this.players = players;
	}

	public int getPlayers() {
		return players;
	}

	

}
