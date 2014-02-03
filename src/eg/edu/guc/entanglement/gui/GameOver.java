package eg.edu.guc.entanglement.gui;
import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GameOver extends JLabel {
	private String color;
	
	public GameOver(String color)
	{
		this.color = color;
	}
	public void paintComponent(Graphics g)
	{
		this.setSize(360, 10);
		Polygon poly = new Polygon(new int[] {0, this.getWidth(),
				this.getWidth(), 0 }, new int[] {0, 0, this.getHeight(),
				this.getHeight() }, 4);
		g.drawPolygon(poly);
		if (this.color.equalsIgnoreCase("red"))
		{
			g.setColor(Color.red);
		}
		else
		{
			g.setColor(Color.green);
		}
		
		g.fillPolygon(poly);
	}
	public void setColor(String color)
	{
		this.color = color;
		repaint();
	}

}
