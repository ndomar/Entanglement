package eg.edu.guc.entanglement.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.*;


public class BoardConfiguration implements MouseListener {
	
	private int startLocation;
	private int width;
	private int height;
	private int numberOfTiles;
	private ArrayList<int[]> tiles;
	private String reader;
	private int openingsPerSide;
	private JFrame frame;
	private JDialog dialog;
	private JButton closeDialog;
	
	public BoardConfiguration(JFrame frame, int numberOfPlayers)
	{
		this.frame = frame;
		try
		{
			FileReader fr = new FileReader("BoardConfiguration.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			this.reader = "";
			while ((line = br.readLine()) != null)
			{
				if (!line.equalsIgnoreCase("board"))
				{
					this.reader = this.reader + line + "\n";
				}
				
			}
			//this.reader = this.reader  + numberOfPlayers;
			System.out.println(this.reader);
			this.initVariables();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	public void setOpeningPerSide(int openings)
	{
		try
		{
			this.removeAllTiles();
		}
		catch (TileOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		this.openingsPerSide = openings;
		int[] config = this.randomizeTile(openings);
		
		System.out.println("generated tile" + config.length);
		for (int i = 0; i < config.length; i++)
		{
			System.out.println(i + " connected with " + config[i]);
		}
		ArrayList<int[]> newTiles = new ArrayList<int[]>();
		newTiles.add(0, config);
		
		this.tiles = newTiles;
		System.out.println("number of tiles = " + this.numberOfTiles);		
		System.out.println("add tiles called with openings = " + openings);
		config = this.randomizeTile(openings);
		newTiles.add(1, config);
		this.numberOfTiles = 2;
		this.generateReader();
	}
	public void removeAllTiles() throws TileOutOfBoundsException
	{
		for (int i = 0; i < numberOfTiles; i++)
		{
			System.out.println("number of tiles was " + this.numberOfTiles);
			this.removeTile(0);
		}
		
	}
	public int getTileCount()
	{
		return this.numberOfTiles;
	}
	public void initVariables()
	{
		try
		{
			StringReader sr = new StringReader(this.reader);
			BufferedReader br = new BufferedReader(sr);
			br.readLine();
			this.tiles = new ArrayList<int[]>();
			this.openingsPerSide = Integer.parseInt(br.readLine());
			int tileTypes = Integer.parseInt(br.readLine());

			for (int i = 0; i < tileTypes; i++) {
				String[] input = br.readLine().split(" ");
				int[] config = new int[input.length];
				for (int j = 0; (j < 4 * openingsPerSide); j++) {
					config[j] = Integer.parseInt(input[j]);
				}
				this.tiles.add(config);
			}
			this.width = Integer.parseInt(br.readLine());
			this.height = Integer.parseInt(br.readLine());
			this.startLocation = Integer.parseInt(br.readLine());
			this.numberOfTiles = tileTypes;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void generateReader()
	{
		String output = "4";
		output = output + "\n" + this.openingsPerSide;
		output = output + "\n" + this.numberOfTiles;
		output = output + "\n";
		for (int i = 0; i < this.tiles.size(); i++)
		{
			int[] temp = this.tiles.get(i);
			System.out.println(" i equal " + i);
			for (int j = 0; j < (this.openingsPerSide * 4); j++)
			{
				
				System.out.println("length of temp = " + temp.length);
				output = output + temp[j] + " ";
			}
			output = output + "\n";
		}
		output = output + this.width;
		output = output + "\n" + this.height;
		output = output + "\n" + this.startLocation + "\n";
		this.reader = output;
		System.out.println("-------------------- new reader ----------------");
		System.out.println(output);
	}
	public String getReader()
	{
		return this.reader;
	}
	public void setSize(int size)
	{
		this.height = size;
		this.width = size;
		int location = ((this.width / 2) * this.width) + (this.width / 2);
		try
		{
			this.setLocation(location);
			this.generateReader();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void setLocation(int location) throws TileOutOfBoundsException
	{
		if (!((location < 0) || (location >= (this.width * this.height))))
		{
			this.startLocation = location;
			this.generateReader();
			
		}
		else
		{
			System.out.println("Exception");
			this.dialog = new JDialog(this.frame, "Error Message");
			dialog.setLayout(null);
			JLabel message = new JLabel("the input does not refer to a valid tile.");
			message.setBounds(30, 20, 300, 20);
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
			throw new TileOutOfBoundsException();
		}
		
	}
	public int getSize()
	{
		return height;
	}
	public int getOpeningsPerSide()
	{
		return this.openingsPerSide;
		
	}
	public void addTile(int[] tileConfig)
	{
		this.tiles.add(tileConfig);
		this.numberOfTiles++;
		this.generateReader();
	}
	public void removeTile(int index) throws TileOutOfBoundsException
	{
		try
		{
			this.tiles.remove(index);
			this.numberOfTiles--;
			this.generateReader();
		}
		catch (Exception e)
		{
			System.out.println("Exception");
			this.dialog = new JDialog(this.frame, "Error Message");
			dialog.setLayout(null);
			JLabel message = new JLabel("the input does not refer to a valid tile.");
			message.setBounds(30, 20, 300, 20);
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
			throw new TileOutOfBoundsException();
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
	
		if (event.getSource() instanceof JButton)
		{
			JButton clicked = (JButton) event.getSource();
			
			if (clicked == this.closeDialog)
			{
				this.dialog.dispose();
				this.frame.setAlwaysOnTop(true);
				this.frame.setEnabled(true);
			}
		}
		
	}
	public  void mouseEntered(MouseEvent arg0) { }
	public void mouseExited(MouseEvent arg0) { }
	public void mousePressed(MouseEvent arg0) { }
	public void mouseReleased(MouseEvent arg0) { }
	public  int[] randomizeTile(int openings)
	{
		int[] config = new int[openings * 4];
		int start = 0;
		int end = 0;
		ArrayList<Integer> used = new ArrayList<Integer>();
		ArrayList<Integer> opening = new ArrayList<Integer>();
		for (int i = 0; i < openings * 4; i++)
		{
			opening.add(i);
		}
		for (int i = 0; i < openings * 4; i++)
		{
			if (this.isUsed(opening, i))
			{
				continue;
			}
			if (!opening.isEmpty())
			{
				start = i;
				System.out.println("start is " + start);
				System.out.println("list size is " + opening.size());
				int random = 0;
				random = (int) (Math.random() * opening.size());
				end = (int) opening.get(random);
				while (i == end)
				{
					random = (int) (Math.random() * opening.size());
					end = (int) opening.get(random);
					
				}
				System.out.println("random is " + random);
				System.out.println("end is " + end);
				
				config[start] = end;
				config[end] = start;
				used.add(start);
				used.add(end);
				opening.remove(new Integer(end));
				opening.remove(new Integer(start));
				System.out.println("ArrayList became ");
				for (int j = 0; j < opening.size(); j++)
				{
					System.out.println((int) opening.get(j));
				}
			}
		}
		return config;
	}
	public  boolean isUsed(ArrayList<Integer> e, int index)
	{
		for (int i = 0; i < e.size(); i++)
		{
			if (((int) e.get(i)) == index)
			{
				return false;
			}
		}
		return true;
	}

}
