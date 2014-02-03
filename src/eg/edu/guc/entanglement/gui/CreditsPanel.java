package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.event.*;


@SuppressWarnings("serial")
public class CreditsPanel extends JPanel implements MouseListener {
	private JFrame frame;
	private JLabel content;
	private JLabel line;
	private JButton back;
	private BoardConfiguration bc;

	public CreditsPanel(JFrame frame, BoardConfiguration bc) {
		this.bc = bc;
		this.frame = frame;
		this.content = new JLabel(
				"This game was first developed by Gropherwood Studios ");
		this.line = new JLabel(
				"This version of the game was developed by Omar Nada.");
		
		this.setSize(frame.getSize());
		this.setLocation(20, 20);
		this.setLayout(null);
		content.setBounds(40, 0, 760, 540);
		line.setBounds(40, 30, 760, 540);
		this.back = new JButton("Back");
		back.setBounds(450, 520, 120, 80);
		this.add(content);
		this.add(line);
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
}
