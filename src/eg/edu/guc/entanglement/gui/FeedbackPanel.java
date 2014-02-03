package eg.edu.guc.entanglement.gui;
import javax.swing.*;


import java.awt.event.*;

import java.awt.*;

@SuppressWarnings("serial")
public class FeedbackPanel extends JPanel implements MouseListener {
	private  JLabel feedback;
	private JFrame frame;
	private JButton back;
	private Image image;
	private BoardConfiguration bc;
	
	public FeedbackPanel(JFrame frame, BoardConfiguration bc)
	{
		this.bc = bc;
		this.frame = frame;
		ImageIcon icon = new ImageIcon("ent.jpg");
		this.setImage(icon.getImage());
		this.setLayout(null);
		this.setSize(frame.getSize());
		this.setLocation(20, 20);
		this.feedback = new JLabel("You can send your feedback to admin@noobdude.com");
		feedback.setBounds(350, 80, 700, 300);
		this.add(feedback);
		this.back = new JButton("Back");
		back.setBounds(450, 520, 120, 80);
		this.add(back);
		this.addMouseListener(this);
		this.back.addMouseListener(this);
	}
/*	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}*/

	@Override
	public void mouseClicked(MouseEvent event) {
		
		Object obj = event.getSource();
		if (obj instanceof JButton)
		{
			JButton clickedButton = (JButton) obj;
			if (clickedButton.equals(this.back))
			{
				this.clearPanel();
				HomePanel next = new HomePanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				//this.frame.setContentPane(next);
				this.clearPanel();
				this.frame.add(next);
				this.revalidate();
				this.repaint();
			}
		}
		
		
	}
	public  void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
	public void clearPanel()
	{
		this.removeAll();
		this.validate();
		this.repaint();
		
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

}
