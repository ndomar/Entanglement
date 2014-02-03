package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RemoveTilePanel extends JPanel implements MouseListener  {

	private BoardConfiguration bc;
	private JFrame frame;
	private JButton submit;
	private JTextField field;
	private JButton closeDialog;
	private JDialog dialog;
	private JButton back;

	public RemoveTilePanel(JFrame frame, BoardConfiguration bc) {
		this.bc = bc;
		this.frame = frame;
		this.setSize(frame.getSize());
		this.setLayout(null);
		JLabel message = new JLabel(
				"Enter the number of the tile you want to remove");
		message.setBounds(300, 50, 300, 30);
		this.add(message);
		this.field = new JTextField();
		this.field.setBounds(200, 100, 300, 30);
		this.submit = new JButton("Submit");
		this.submit.setBounds(520, 100, 100, 30);
		this.field.setVisible(true);
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
			if (clicked == this.submit) {
				boolean isValid = true;
				String in = field.getText();
				try {
					int input = Integer.parseInt(in);
					this.bc.removeTile(input);
					isValid = true;

				} catch (TileOutOfBoundsException e) {
					isValid = false;
				} catch (NumberFormatException e) {
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
			if (clicked == this.closeDialog) {
				this.dialog.dispose();
				this.frame.setEnabled(true);
				this.frame.setAlwaysOnTop(true);
			}
			if (clicked == back) {
				this.clearPanel();
				this.add(new HomePanel(this.frame, this.bc));
				this.repaint();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void clearPanel() {
		this.removeAll();
		this.validate();
		this.repaint();

	}

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Entangelement");
		myFrame.setSize(800, 600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// myFrame.getContentPane().setLayout(null);
		RemoveTilePanel sp = new RemoveTilePanel(myFrame,
				new BoardConfiguration(myFrame, 2));
		myFrame.getContentPane().add(sp);
		myFrame.setVisible(true);

	}

	

}
