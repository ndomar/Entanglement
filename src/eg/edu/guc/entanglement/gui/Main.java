package eg.edu.guc.entanglement.gui;
import java.awt.Dimension;
import java.awt.Toolkit;



import javax.swing.*;


public abstract class Main {
	public static void main(String[]args)
	{

		
		JFrame myFrame = new JFrame("Entangelement");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		myFrame.setSize(dimension);

		//myFrame.setSize(800, 600);
		myFrame.setUndecorated(false);
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		BoardConfiguration bc = new BoardConfiguration(myFrame, 0);
		HomePanel home = new HomePanel(myFrame, bc);
		home.setBounds(0, 0, myFrame.getWidth(), myFrame.getHeight());
		myFrame.getContentPane().add(home);		
		myFrame.setVisible(true);
		//try
		//{
		//	InputStream in = new FileInputStream("Song.au");
		//	AudioStream as = new AudioStream(in);  
		//	AudioPlayer.player.start(as);            
		//}
		//catch (Exception e)
		//{
		//	e.printStackTrace();
		//}
		 
	}
	
	
}
