package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RulesPanel extends JPanel implements MouseListener {
	private JFrame frame;
	private JLabel content;
	private JLabel line;
	private JLabel lineOne;
	private JLabel lineTwo;
	private JLabel lineThree;
	private JLabel lineFour;
	private JButton back;
	private Image image;
	private BoardConfiguration bc;

	public RulesPanel(JFrame frame, BoardConfiguration bc) {
		this.bc = bc;
		this.frame = frame;
		ImageIcon icon = new ImageIcon("ent.jpg");
		this.setImage(icon.getImage());
		this.content = new JLabel(
				"The rules for simple entangelement are as follows ");
		this.line = new JLabel(
				"every player plays on a board, a board have " 
				+ "height and width, and consists of tiles");
		this.lineOne = new JLabel(
				"tiles are the main playing object of this game, mainly, a" 
				+ " tile is like a block which contains some points connected together");
		this.lineTwo = new JLabel(
				"you gain score by fixing the tile, the longer your path is," 
				+ " the higher is your score,");
		this.lineThree = new JLabel(
				"your game is over when you reach a dead end or hit the " 
				+ "starting position or hit the path of another player.");
		this.lineFour = new JLabel(
				"you can rotate and switch the tile you are playing with.");

		this.setSize(frame.getSize());
		this.setLocation(20, 20);
		this.setLayout(null);
		content.setBounds(40, 0, 760, 540);
		line.setBounds(40, 30, 760, 540);
		lineOne.setBounds(40, 60, 760, 540);
		lineTwo.setBounds(40, 90, 760, 540);
		lineThree.setBounds(40, 120, 760, 540);
		lineFour.setBounds(40, 150, 760, 540);
		this.back = new JButton("Back");
		back.setBounds(450, 520, 120, 80);
		this.add(content);
		this.add(line);
		this.add(lineOne);
		this.add(lineTwo);
		this.add(lineThree);
		this.add(lineFour);
		this.add(back);
		this.back.addMouseListener(this);

	}
/*	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}*/

	@Override
	public void mouseClicked(MouseEvent event) {
		this.clearPanel();
		Object obj = event.getSource();
		if (obj instanceof JButton)
		{
			JButton clickedButton = (JButton) obj;
			if (clickedButton.equals(this.back))
			{
				this.clearPanel();
				HomePanel next = new HomePanel(this.frame, bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				//this.frame.setContentPane(next);
				this.frame.add(next);
				this.revalidate();
				this.repaint();
			}
		}
		
		
	}
	public void clearPanel()
	{
		this.removeAll();
		this.validate();
		this.repaint();
		
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
}
