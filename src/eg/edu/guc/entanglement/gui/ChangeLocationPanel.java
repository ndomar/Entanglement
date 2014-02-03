package eg.edu.guc.entanglement.gui;

import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ChangeLocationPanel extends JPanel implements MouseListener {

	private JButton submit;
	private JTextField field;
	private JFrame frame;
	private BoardConfiguration bc;
	private JButton back;
	private JDialog dialog;
	private JButton closeDialog;

	public ChangeLocationPanel(JFrame frame, BoardConfiguration bc) {
		this.bc = bc;
		this.frame = frame;
		this.setLayout(null);
		JLabel message = new JLabel("Enter the number of the tile that you want to start from");
		message.setBounds(270, 50, 330, 30);
		this.add(message);
		this.field = new JTextField();
		this.field.setBounds(200, 100, 300, 30);
		this.submit = new JButton("Submit");
		this.submit.setBounds(520, 100, 100, 30);
	//	this.field.setVisible(true);
		this.submit.addMouseListener(this);
		this.back = new JButton("Back");
		this.back.addMouseListener(this);
		back.setBounds(340, 460, 120, 80);
		this.add(back);
		this.add(field);
		this.add(submit);

	}


	@Override
	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof JButton) {
			JButton clicked = (JButton) obj;
			boolean isValid = true;
			if (clicked == this.submit) {
				try
				{
					String in = field.getText();
					int input = Integer.parseInt(in);
					this.bc.setLocation(input);
					isValid = true;
				}
				catch (TileOutOfBoundsException e)
				{
					isValid = false;
				}
				catch (NumberFormatException e)
				{
					System.out.println("invalid input");
					System.out.println("Exception");
					this.dialog = new JDialog(this.frame, "Error Message");
					dialog.setLayout(null);
					JLabel message = new JLabel("You did type a string or did not submit an input");
					message.setBounds(10, 20, 300, 20);
					dialog.add(message, BorderLayout.PAGE_START);
					JLabel action = new JLabel(" Press ok to continue");
					action.setBounds(70, 40, 300, 20);
					dialog.add(action);
					dialog.add(message);
					dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					this.closeDialog = new JButton("Ok");
					this.closeDialog.setBounds(95, 70, 80, 40);
					this.closeDialog.addMouseListener(this);
					dialog.add(closeDialog);
					dialog.setBounds(350, 300, 300, 200);
					dialog.setAlwaysOnTop(true);
					dialog.setFocusable(true);
					dialog.setVisible(true);
					this.frame.setEnabled(false);
					isValid = false;
				}
				if (isValid)
				{
					
					this.clearPanel();
					HomePanel next = new HomePanel(this.frame, bc);
					next.setBounds(0, 0, this.getWidth(), this.getHeight());
					System.out.println("size is now " + this.bc.getSize());
					this.frame.setContentPane(next);
					this.repaint();
					this.revalidate();
				}
		

			}
			if (clicked == back)
			{
				this.clearPanel();
				this.add(new HomePanel(this.frame, this.bc));
				this.repaint();
				
			}
			if (clicked == closeDialog)
			{
				this.dialog.dispose();
				this.frame.setEnabled(true);
				this.frame.setAlwaysOnTop(true);
			}
		}

	}

	public void clearPanel() {
		this.removeAll();
		this.validate();
		this.repaint();

	}

	public  void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }

}
