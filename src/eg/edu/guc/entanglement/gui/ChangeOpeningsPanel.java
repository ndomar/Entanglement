package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ChangeOpeningsPanel extends JPanel implements MouseListener {

	private JButton submit;
	private JTextField field;
	private JFrame frame;
	private BoardConfiguration bc;
	private JButton back;
	private JDialog dialog;
	private JButton closeDialog;

	public ChangeOpeningsPanel(JFrame frame, BoardConfiguration bc) {
		this.bc = bc;
		this.frame = frame;
		this.setLayout(null);
		JLabel message = new JLabel("Enter the number of the desired Openings");
		message.setBounds(270, 50, 330, 30);
		this.add(message);
		JLabel message1 = new JLabel(
				"please note that the number should  not  be 0, "
						+ "openings will remiain the same if you input 0");
		message1.setBounds(150, 80, 700, 30);
		this.add(message1);
		this.field = new JTextField();
		this.field.setBounds(200, 150, 300, 30);
		this.submit = new JButton("Submit");
		this.submit.setBounds(520, 150, 100, 30);
		// this.field.setVisible(true);
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
				try {
					String in = field.getText();
					int input = Integer.parseInt(in);
					System.out.println("the input is " + input);
					if (input > 0) {
						this.bc.setOpeningPerSide(input);
						isValid = true;
					} else {
						System.out.println("invalid input");
						System.out.println("Exception");
						this.dialog = new JDialog(this.frame, "Error Message");
						dialog.setLayout(null);
						JLabel message = new JLabel("The input " + input
								+ " can not be used to play the game");
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
					
				} catch (NumberFormatException e) {
					System.out.println("invalid input");
					System.out.println("Exception");
					this.dialog = new JDialog(this.frame, "Error Message");
					dialog.setLayout(null);
					JLabel message = new JLabel(
							"You did type a string or did not submit an input");
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

				if (isValid) {

					this.clearPanel();
					HomePanel next = new HomePanel(this.frame, bc);
					next.setBounds(0, 0, this.getWidth(), this.getHeight());
					System.out.println("size is now " + this.bc.getSize());
					// this.frame.setContentPane(next);
					this.frame.setContentPane(next);
					this.repaint();
					this.revalidate();
				}

			}
			if (clicked == back) {
				this.clearPanel();
				this.add(new HomePanel(this.frame, this.bc));
				this.repaint();

			}
			if (clicked == closeDialog) {
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
