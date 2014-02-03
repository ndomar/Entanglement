package eg.edu.guc.entanglement.gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class AddTilePanel extends JPanel implements MouseListener {

	private JFrame frame;
	private BoardConfiguration bc;
	private ArrayList<JButton> openings;
	private boolean isStart;
	private int start;
	private int end;
	private int[] config;
	private JButton back;
	private TileGUI drawnTile;
	private boolean[] clickedButtons;

	public AddTilePanel(JFrame frame, BoardConfiguration bc) {
		this.isStart = true;
		this.frame = frame;
		this.bc = bc;
		int openings = bc.getOpeningsPerSide() * 4;
		this.config = new int[openings];
		this.clickedButtons = new boolean[openings];
		this.openings = new ArrayList<JButton>();
		this.setLayout(new BorderLayout(20, 20));
		 this.setLayout(new BorderLayout(20, 20));
		JLabel message = new JLabel(
			" you connect the openings by pressing its id "
						+ "and then the id of its opposite opening");
		message.setBounds(150, 20, 600, 20);
		this.add(message, BorderLayout.PAGE_START);
		this.add(new JLabel(
			"                                                          "),
				BorderLayout.WEST);
		this.add(new JLabel(
		"                                                          "),
		BorderLayout.EAST);

		JPanel myPanel = new JPanel();
		myPanel.setSize(600, 600);
		myPanel.setLayout(new GridLayout(openings + 1, 1));
		for (int i = 0; i < openings; i++) {
			JButton button = new JButton("Opening number " + i);
			button.setBounds(250, 100, 300, 40);
			button.addMouseListener(this);
			this.openings.add(button);
			myPanel.add(button);
			System.out.println("button added");
		}
		this.add(myPanel, BorderLayout.EAST);
		this.back = new JButton("Back");
		this.back.addMouseListener(this);
		JPanel backPanel = new JPanel();
		//backPanel.setLayout(new GridLayout(1, 5));
		backPanel.add(back);
		this.add(backPanel, BorderLayout.WEST);
		this.drawnTile = new TileGUI();
		this.drawnTile.createChosenOpenings(openings);
		JPanel tilePanel = new JPanel();
		tilePanel.setLayout(new GridLayout(4, 4));
		for (int i = 0; i < 16; i++)
		{
			if (i != 5)
			{
				tilePanel.add(new JPanel());
			}
			else
			{
				tilePanel.add(drawnTile);
			}
		}
		//tilePanel.add(drawnTile);
		
		this.add(tilePanel, BorderLayout.CENTER);
	}



	@Override
	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof JButton) {
			JButton clicked = (JButton) obj;
			int index = 0;
			for (int i = 0; i < 4 * this.bc.getOpeningsPerSide(); i++) {
				if (clicked == this.openings.get(i)) {
					System.out.println("button pressed");
					index = i;
					this.openings.get(i).removeMouseListener(this);
					this.openings.get(i).setOpaque(true);
					this.openings.get(i).setBackground(Color.black);
					this.openings.get(i).setText("button was clicked");
					break;
				}
			}
			if (isStart) {
				this.start = index;
				System.out.println("start set to " + this.start);
			} else {
				this.end = index;
				System.out.println("end set to " + this.end);
				this.config[start] = end;
				this.config[end] = start;
				for (int i = 0; i < this.config.length; i++) {
					System.out.println(i + " connected to " + config[i]);
				}
				this.clickedButtons[start] = true;
				this.clickedButtons[end] = true;
				this.drawnTile.setChosen(start);
				this.drawnTile.setChosen(end);
				System.out.println("Start = " + this.start + " End = " + this.end);
				this.drawnTile.setTileConfig(this.config);
				this.drawnTile.chosenDraw();
			}
			isStart = !isStart;
			this.addTile();
			if (clicked == back)
			{
				this.clearPanel();
				this.add(new HomePanel(this.frame, this.bc));
			}
	
		}

	}

	public void addTile() {
		if (allClicked()) {
			this.bc.addTile(config);
			this.clearPanel();
			HomePanel next = new HomePanel(this.frame, bc);
			next.setBounds(0, 0, this.getWidth(), this.getHeight());
			 this.frame.setContentPane(next);
			//this.frame.add(new HomePanel(this.frame, this.bc));
			System.out.println("home panel added");

		}
	}

	public boolean allClicked() {

		for (int i = 0; i < this.openings.size(); i++) {
			if (!this.openings.get(i).getText()
					.equalsIgnoreCase("button was clicked")) {
				return false;
			}
		}
		return true;
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
