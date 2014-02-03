package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

public class SettingsPanel extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5471867843547579578L;
	private BoardConfiguration bc;
	private JButton changeSize;
	private JButton changeStartLocation;
	private JButton addTile;
	private JButton removeTile;
	private JButton changeLocation;
	private JButton changeOpenings;
	private JFrame frame;
	private JButton exit;
	private Image image;
	
	public SettingsPanel(JFrame frame, BoardConfiguration bc) 
	{
		this.bc = bc;
		this.frame = frame;
		ImageIcon icon = new ImageIcon("ent.jpg");
		this.image = icon.getImage();
		this.setLayout(null);
		this.setSize(frame.getSize());
		this.setLocation(0, 0);
	    this.changeSize = new TransparentButton("Change Board Size");
		changeSize.setBounds(350, 140, 340, 60);
		this.addTile = new TransparentButton("Add a Tile");
		addTile.setBounds(350, 220, 340, 60);
		this.removeTile = new TransparentButton("Remove a Tile");
		removeTile.setBounds(350, 300, 340, 60);
		this.changeLocation = new TransparentButton("Change Start Location");
		this.changeLocation.setBounds(350, 380, 340, 60);
		this.changeOpenings = new TransparentButton("Change openings number");
		this.changeOpenings.setBounds(350, 460, 340, 60);
		this.exit = new TransparentButton("Back");
		this.exit.setBounds(350, 540, 340, 60);
		this.removeTile.setFont(new Font("calibri", Font.ITALIC, 30)); 
		this.changeSize.setFont(new Font("calibri", Font.ITALIC, 30)); 
		this.addTile.setFont(new Font("calibri", Font.ITALIC, 30)); 
		this.changeLocation.setFont(new Font("calibri", Font.ITALIC, 30)); 
		this.exit.setFont(new Font("calibri", Font.ITALIC, 30));
		this.changeOpenings.setFont(new Font("calibri", Font.ITALIC, 30)); 
		this.removeTile.addMouseListener(this);
		this.changeSize.addMouseListener(this);
		this.addTile.addMouseListener(this);
		this.exit.addMouseListener(this);
		this.changeLocation.addMouseListener(this);
		this.changeOpenings.addMouseListener(this);
		this.add(changeSize);
		this.add(changeLocation);
		this.add(addTile);
		this.add(removeTile);
		this.add(exit);
		exit.setForeground(Color.red);
		this.add(changeOpenings);
		
	}
	public JFrame getFrame()
	{
		return this.frame;
	}

	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof JButton)
		{
			JButton clickedButton = (JButton) obj;
			if ((clickedButton.equals(this.changeSize)))
			{
				this.clearPanel();
				ChangeSizePanel next = new ChangeSizePanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if ((clickedButton.equals(this.changeOpenings)))
			{
				this.clearPanel();
				ChangeOpeningsPanel next = new ChangeOpeningsPanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if (clickedButton.equals(this.addTile))
			{
				this.clearPanel();
				AddTilePanel next = new AddTilePanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				
				this.revalidate();
			}
			if (clickedButton.equals(exit))
			{
				this.clearPanel();
				HomePanel next = new HomePanel(this.frame, bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.add(next);
				this.revalidate();
				this.repaint();
			}
			if (clickedButton.equals(this.removeTile))
			{
				this.clearPanel();
				RemoveTilePanel next = new RemoveTilePanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if (clickedButton.equals(this.changeLocation))
			{
				this.clearPanel();
				ChangeLocationPanel next = new ChangeLocationPanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
			}

		}
		
	}
	public void clearPanel()
	{
		this.removeAll();
		this.validate();
		this.repaint();
		
		
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}
	public void mouseEntered(MouseEvent event) {
		
		if (event.getSource() instanceof JButton)
		{
			JButton entered = (JButton) event.getSource();
			if (entered != exit)
			{
				entered.setForeground(Color.green);
			}
		}
	}

	public void mouseExited(MouseEvent event) {
		
		if (event.getSource() instanceof JButton)
		{
			JButton entered = (JButton) event.getSource();
			if (entered != exit)
			{
				entered.setForeground(Color.black);
			}
		}
	}

	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
	
	public void setChangeStartLocation(JButton changeStartLocation) {
		this.changeStartLocation = changeStartLocation;
	}
	public JButton getChangeStartLocation() {
		return changeStartLocation;
	}
	
	

}
