package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements MouseListener {
	private JButton playGame;
	private JButton rules;
	private JButton credits;
	private JButton feedback;
	private JButton exit;
	private JLabel title;
	private JFrame frame;
	private Image image;
	private JButton settings;
	private BoardConfiguration bc;
	private JDialog dialog;
	private JButton closeDialog;
	private JLabel myLabel;

	public HomePanel(JFrame frame, BoardConfiguration bc) {
		this.bc = bc;
		ImageIcon icon = new ImageIcon("ent.jpg");
		this.image = icon.getImage();
		this.frame = frame;
		this.setLayout(null);
		this.setSize(frame.getSize());
		this.setLocation(0, 0);
		this.playGame = new TransparentButton("Play Game");
		playGame.setBounds(450, 140, 200, 40);
		this.rules = new TransparentButton("Rules");
		rules.setBounds(450, 200, 200, 40);
		this.credits = new TransparentButton("Credits");
		credits.setBounds(450, 260, 200, 40);
		this.feedback = new TransparentButton("Feedback");
		feedback.setBounds(450, 320, 200, 40);
		this.settings = new TransparentButton("Settings");
		this.settings.setBounds(450, 380, 200, 40);
		this.exit = new TransparentButton("Exit");
		this.exit.setFont(new Font("calibri", Font.ITALIC, 30));
		this.feedback.setFont(new Font("calibri", Font.ITALIC, 30));
		this.playGame.setFont(new Font("calibri", Font.ITALIC, 30));
		this.rules.setFont(new Font("calibri", Font.ITALIC, 30));
		this.credits.setFont(new Font("calibri", Font.ITALIC, 30));
		this.settings.setFont(new Font("calibri", Font.ITALIC, 30));
		exit.setBounds(450, 440, 200, 40);
		exit.setForeground(Color.red);
		this.myLabel = new JLabel("TEST");
		myLabel.setBounds(450, 500, 200, 40);
		//this.add(myLabel);
		//this is the game :o
		// run it
		
		myLabel.addMouseListener(this);
		this.myLabel.setForeground(Color.red);
		myLabel.setFont(new Font("Utsaah", Font.ITALIC, 30));
		this.add(playGame);
		this.add(rules);
		this.add(credits);
		this.add(feedback);
		this.add(credits);
		this.add(settings);
		this.add(exit);
		this.title = new JLabel("Simple Entangelement");
		title.setFont(new Font("Courier New", Font.PLAIN, 50));
		title.setBounds(260, 10, 400, 40);
		// this.add(title);
		this.setBackground(Color.black);
		this.addMouseListener(this);
		this.playGame.addMouseListener(this);
		this.rules.addMouseListener(this);
		this.credits.addMouseListener(this);
		this.exit.addMouseListener(this);
		this.feedback.addMouseListener(this);
		this.settings.addMouseListener(this);

		// this.setUndecorated(true);

	}

	@Override
	public void mouseClicked(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof JLabel)
		{
			if ((JLabel) obj == myLabel)
			{
				System.exit(1);
			}
		}
		
		if (obj instanceof JButton) {
			JButton clickedButton = (JButton) obj;
			if ((clickedButton.equals(this.playGame))) {
				int tiles = this.bc.getTileCount();
				if (tiles == 0) {
					this.dialog = new JDialog(this.frame, "Error Message");
					dialog.setLayout(null);
					JLabel message = new JLabel(
							"You can not initialize a game, no tiles were found");
					message.setBounds(10, 20, 300, 20);
					dialog.add(message, BorderLayout.PAGE_START);
					JLabel action = new JLabel(" Press ok to create a tile");
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

				} else {
					this.clearPanel();
					PlayGamePanel next = new PlayGamePanel(this.frame, this.bc);
					next.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
					this.frame.setContentPane(next);
					this.revalidate();
				}

			}
			if (clickedButton.equals(this.rules)) {
				this.clearPanel();
				RulesPanel next = new RulesPanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if (clickedButton.equals(closeDialog)) {
				this.dialog.dispose();
				this.clearPanel();
				AddTilePanel next = new AddTilePanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
				this.frame.setEnabled(true);
				this.frame.setAlwaysOnTop(true);
			}
			if (clickedButton.equals(this.credits)) {
				this.clearPanel();
				CreditsPanel next = new CreditsPanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if (clickedButton.equals(this.feedback)) {
				this.clearPanel();
				FeedbackPanel next = new FeedbackPanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if (clickedButton.equals(this.settings)) {
				this.clearPanel();
				SettingsPanel next = new SettingsPanel(this.frame, this.bc);
				next.setBounds(0, 0, this.getWidth(), this.getHeight());
				this.frame.setContentPane(next);
				this.frame.setContentPane(next);
				this.revalidate();
			}
			if (clickedButton.equals(this.exit)) {
				System.exit(0);
			}
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
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

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void clearPanel() {
		this.removeAll();
		this.validate();
		this.repaint();

	}

}
