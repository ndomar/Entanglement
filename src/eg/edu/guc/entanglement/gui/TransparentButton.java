package eg.edu.guc.entanglement.gui;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

@SuppressWarnings("serial")
class TransparentButton extends JButton {
	public TransparentButton(String text) { 
	    super(text);
	    setOpaque(false); 
	} 
	    
	public void paint(Graphics g) { 
	    Graphics2D g2 = (Graphics2D) g.create(); 
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); 
	    super.paint(g2); 
	    g2.dispose(); 
	} 

}
