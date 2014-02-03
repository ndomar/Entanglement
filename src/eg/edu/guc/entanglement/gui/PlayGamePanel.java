package eg.edu.guc.entanglement.gui;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


@SuppressWarnings("serial")
public class PlayGamePanel extends JPanel implements MouseListener {
	
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JFrame frame;
	private JButton back;
	private Image image;
	private BoardConfiguration bc;
	public PlayGamePanel(JFrame frame, BoardConfiguration bc)
	{
		this.bc = bc;
		this.frame = frame;
		ImageIcon icon = new ImageIcon("ent.jpg");
		this.image = icon.getImage();
		this.one = new TransparentButton("1 Player");
		this.two = new TransparentButton("2 Players");
		this.three = new TransparentButton("3 Players");
		 this.four = new TransparentButton("4 Players");
		this.setSize(frame.getSize());
		this.setLocation(0, 0);
		this.setLayout(null);
		one.setBounds(450, 140, 200, 40);
		two.setBounds(450, 200, 200, 40);
		three.setBounds(450, 260, 200, 40);
		four.setBounds(450, 320, 200, 40);
		this.add(one);
		this.add(two);
		this.add(three);
		this.add(four);
		this.back = new TransparentButton("Back");
		back.setBounds(450, 380, 200, 40);
		this.add(back);
		this.one.setFont(new Font("calibri", Font.ITALIC, 30));
		this.two.setFont(new Font("calibri", Font.ITALIC, 30));
		this.three.setFont(new Font("calibri", Font.ITALIC, 30));
		this.four.setFont(new Font("calibri", Font.ITALIC, 30));
		this.back.setFont(new Font("calibri", Font.ITALIC, 30));
		this.one.addMouseListener(this);
		this.two.addMouseListener(this);
		this.three.addMouseListener(this);
		this.four.addMouseListener(this);
		this.back.addMouseListener(this);
		this.back.setForeground(Color.red);
		
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof JButton) {
			JButton clickedButton = (JButton) obj;
			if (clickedButton.equals(one)) {
				this.clearPanel();
				Game next = new Game(this.frame, 1, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.revalidate();
				this.repaint();
			}
			if (clickedButton.equals(two)) 	{
				this.clearPanel();
				Game next = new Game(this.frame, 2, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.revalidate();
				this.repaint();
			}
			if (clickedButton.equals(three)) {
				this.clearPanel();
				Game next = new Game(this.frame, 3, this.bc);
				next.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
				this.frame.setContentPane(next);
				this.revalidate();
				this.repaint();
			}
			if (clickedButton.equals(four)) {
				this.clearPanel();
				Game next = new Game(this.frame, 4, this.bc);
				next.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
				//this.frame.setContentPane(next);
				this.frame.add(next);
				this.revalidate();
				this.repaint();
			}
			if (clickedButton.equals(back)) {
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
public void mouseEntered(MouseEvent event) {
		
		if (event.getSource() instanceof JButton)
		{
			JButton entered = (JButton) event.getSource();
			if (entered != back)
			{
				entered.setForeground(Color.green);
			}
		}
	}

	public void mouseExited(MouseEvent event) {
		
		if (event.getSource() instanceof JButton)
		{
			JButton entered = (JButton) event.getSource();
			if (entered != back)
			{
				entered.setForeground(Color.black);
			}
		}
	}
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
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
}