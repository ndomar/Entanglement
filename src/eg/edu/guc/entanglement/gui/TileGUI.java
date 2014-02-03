package eg.edu.guc.entanglement.gui;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class TileGUI extends JButton {

	private boolean isFixed;
	private ArrayList<int[]> fixingPlayer;
	private boolean isActive;
	private boolean isBlank;
	private boolean isCenter;
	private boolean chosen;
	private int counter;
	private int[] tileConfig;
	private int[] xCoordinates;
	private int[] yCoordinates;
	private boolean[] isFixedOpening;
	private boolean[] chosenOpenings;

	public TileGUI() {
		this.isFixed = false;
		this.isActive = false;
		this.isBlank = true;
		this.chosen = false;
		this.counter = 0;
		System.out.println("assigned width = " + this.getWidth());
		this.fixingPlayer = new ArrayList<int[]>();


	}

	public TileGUI(int[] tileConfig) {
		this.tileConfig = tileConfig;
		this.isFixed = false;
		this.isActive = false;
		this.chosen = false;
		this.isBlank = true;
		this.fixingPlayer = new ArrayList<int[]>();
		this.isFixedOpening = new boolean[this.tileConfig.length];
	}
	public void createChosenOpenings(int size)
	{
		this.chosenOpenings = new boolean[size];
	}

	public void paintComponent(Graphics g) {
		if (!isBlank) {
			if (isActive) {
				Polygon poly = new Polygon(new int[] {0, this.getWidth(),
						this.getWidth(), 0 }, new int[] {0, 0,
						this.getHeight(), this.getHeight() }, 4);
				g.setColor(Color.green);
				g.drawPolygon(poly);
				g.fillPolygon(poly);
				g.setColor(Color.white);
				if (this.tileConfig != null) {
					for (int i = 0; i < this.tileConfig.length; i++) {
						this.generateCoordinates();
						System.out.println("connecting for opening " + i);

						int x1 = this.getCoordinates(i)[0];
						int y1 = this.getCoordinates(i)[1];
						int x2 = this.getCoordinates(this.tileConfig[i])[0];
						int y2 = this.getCoordinates(this.tileConfig[i])[1];
						System.out.println("x1 = " + x1 + " y1 = " + y1
								+ " x2 = " + x2 + " y2 = " + y2);
						QuadCurve2D q = new QuadCurve2D.Float();
						q.setCurve(x1, y1, this.getWidth() / 2,
								this.getHeight() / 2, x2, y2);
						Graphics2D ga = (Graphics2D) g;
						Stroke drawingStroke = new BasicStroke(3);
						ga.setStroke(drawingStroke);
						ga.setPaint(Color.white);
						ga.draw(q);
					}
				}
				this.counter++;
			}
			if (chosen)
			{
				Polygon poly = new Polygon(new int[] {0, this.getWidth(),
						this.getWidth(), 0 }, new int[] {0, 0,
						this.getHeight(), this.getHeight() }, 4);
				g.setColor(Color.green);
				g.drawPolygon(poly);
				g.fillPolygon(poly);
				g.setColor(Color.white);
				if (this.tileConfig != null) {
					for (int i = 0; i < this.tileConfig.length; i++) {
						this.generateCoordinates();
						System.out.println("connecting for opening " + i);
						if (this.chosenOpenings[i])
						{

							int x1 = this.getCoordinates(i)[0];
							int y1 = this.getCoordinates(i)[1];
							int x2 = this.getCoordinates(this.tileConfig[i])[0];
							int y2 = this.getCoordinates(this.tileConfig[i])[1];
							System.out.println("x1 = " + x1 + " y1 = " + y1
									+ " x2 = " + x2 + " y2 = " + y2);
							QuadCurve2D q = new QuadCurve2D.Float();
							q.setCurve(x1, y1, this.getWidth() / 2,
									this.getHeight() / 2, x2, y2);
							Graphics2D ga = (Graphics2D) g;
							Stroke drawingStroke = new BasicStroke(3);
							ga.setStroke(drawingStroke);
							ga.setPaint(Color.white);
							ga.draw(q);
						}

					}
				}
				this.counter++;
			}
			
			
			if (isFixed) {
				Polygon poly = new Polygon(new int[] {0, this.getWidth(),
						this.getWidth(), 0 }, new int[] {0, 0,
						this.getHeight(), this.getHeight() }, 4);
				g.setColor(Color.red);
				g.drawPolygon(poly);
				g.fillPolygon(poly);
				g.setColor(Color.white);
				float a = 0.37254903f;
				float b = 0.105882354f;
				float c = 0.32941177f;
				Color myColor = new Color(a, b, c);
				a = 0.6117647f;
				b = 0.62352943f;
				c = 0.101960786f;
				Color newColor = new Color(a , b, c);
				if (this.tileConfig != null) {
					for (int i = 0; i < this.tileConfig.length; i++) {
						g.setColor(Color.white);
						if (isFixed(i)) {
							// if (( i == lastFixed ) || ( i ==
							// tileConfig[lastFixed])) {
							// if (player == 0)
							// {
							int player = getFixingPlayer(i);
							if (player == 0)
							{
								g.setColor(Color.cyan);
							}
							if (player == 1)
							{
								g.setColor(Color.yellow);
							}
							if (player == 2)
							{
								g.setColor(newColor);
							}
							if (player == 3)
							{
								g.setColor(myColor);
							}
							
							//g.setColor(Color.black);
							// }
							// if (player == 1)
							// {
							// g.setColor(Color.cyan);
							// }
							// if (player == 2)
							// {
							// g.setColor(Color.pink);
							// }
							// if (player == 3)
							// {
							// g.setColor(Color.yellow);
							// }
						}
						int x1 = this.getCoordinates(i)[0];
						int y1 = this.getCoordinates(i)[1];
						int x2 = this.getCoordinates(this.tileConfig[i])[0];
						int y2 = this.getCoordinates(this.tileConfig[i])[1];
						QuadCurve2D q = new QuadCurve2D.Float();
						q.setCurve(x1, y1, this.getWidth() / 2,
								this.getHeight() / 2, x2, y2);
						Graphics2D ga = (Graphics2D) g;
						Stroke drawingStroke = new BasicStroke(3);
						ga.setStroke(drawingStroke);
						ga.draw(q);
					}
				}
			}
			
		} else {
			if (isCenter)
			{
				Polygon poly = new Polygon(new int[] {0, this.getWidth(),
						this.getWidth(), 0 }, new int[] {0, 0, this.getHeight(),
						this.getHeight() }, 4);
				g.setColor(Color.red);
				g.drawPolygon(poly);
				g.fillPolygon(poly);
			}
			else
			{
				Polygon poly = new Polygon(new int[] {0, this.getWidth(),
						this.getWidth(), 0 }, new int[] {0, 0, this.getHeight(),
						this.getHeight() }, 4);
				g.setColor(Color.black);
				g.drawPolygon(poly);
				g.fillPolygon(poly);
			}

		}
	}

	public void fixDraw(int activeOpening, int player) {
		this.isFixed = true;
		this.isBlank = false;
		this.isActive = false;
		this.isFixedOpening[activeOpening] = true;
		this.isFixedOpening[this.tileConfig[activeOpening]] = true;
		this.fixingPlayer.add(new int[] {activeOpening,
				tileConfig[activeOpening], player });
		this.repaint();
		this.revalidate();
	}
	public int getFixingPlayer(int opening)
	{
		for (int i = 0; i < this.fixingPlayer.size(); i++)
		{
			int[] temp = fixingPlayer.get(i);
			if ((temp[0] == opening) || (temp[1] == opening))
			{
				return temp[2];
			}
		}
		return 0;
	}

	public int[] getCoordinates(int opening) {
		int side = opening / (this.tileConfig.length / 4);
		if (side == 0) {
			return new int[] {this.xCoordinates[opening], 0 };
		}
		if (side == 1) {
			return new int[] {this.getWidth(),
					this.yCoordinates[opening % (this.tileConfig.length / 4)] };
		}
		if (side == 2) {
			// return new int[] {
			// this.xCoordinates[opening % (this.tileConfig.length / 4)],
			// this.getHeight() };
			int id = ((this.tileConfig.length / 4) - 1)
					- (opening % (this.tileConfig.length / 4));

			return new int[] {this.xCoordinates[id], this.getHeight() };

		}
		if (side == 3) {
			// return new int[] {0, this.yCoordinates[opening %
			// (this.tileConfig.length / 4)]};
			int id = ((this.tileConfig.length / 4) - 1)
					- (opening % (this.tileConfig.length / 4));
			return new int[] {0, this.yCoordinates[id] };
		}

		return null;
	}

	public void setIsFixed(int opening) {
		this.isFixedOpening[opening] = true;
		this.isFixedOpening[this.tileConfig[opening]] = true;
	}

	public boolean isFixed(int opening) {
		return isFixedOpening[opening];
	}

	public void activeDraw() {
		this.isFixed = false;
		this.isBlank = false;
		this.isActive = true;
		System.out.println("active called");
		this.repaint();
		this.revalidate();
	}

	/*
	 * public int[] getCoordinates(int openingID) { switch (openingID) { case 0:
	 * return new int[] {this.getWidth() / 4, 0 }; case 1: return new int[] {(3
	 * * this.getWidth()) / 4, 0 }; case 2: return new int[] {this.getWidth(),
	 * this.getHeight() / 4 }; case 3: return new int[] {this.getWidth(), (3 *
	 * this.getHeight()) / 4 }; case 4: return new int[] {(3 * this.getWidth())
	 * / 4, this.getHeight() }; case 5: return new int[] {this.getWidth() / 4,
	 * this.getHeight() }; case 6: return new int[] {0, (3 * this.getHeight()) /
	 * 4 }; case 7: return new int[] {0, this.getHeight() / 4 }; default: return
	 * null; }
	 * 
	 * }
	 */

	public void generateCoordinates() {
		int width = this.getWidth();
		System.out.println(" width = " + this.getWidth());
		int shift = width / (this.tileConfig.length / 4);
		int intitialLocation = width / ((this.tileConfig.length / 4) + 2);
		System.out.println("initial location is on width =  "
				+ intitialLocation);
		this.xCoordinates = new int[this.tileConfig.length / 4];
		for (int i = 0; i < this.xCoordinates.length; i++) {
			this.xCoordinates[i] = intitialLocation + (i * shift);

		}
		System.out.println("width shift = " + shift);
		int height = this.getHeight();
		shift = height / (this.tileConfig.length / 4);
		System.out.println("height shift = " + shift);
		intitialLocation = height / ((this.tileConfig.length / 4) + 2);
		System.out.println("initial location is on height =  "
				+ intitialLocation);
		this.yCoordinates = new int[this.tileConfig.length / 4];
		for (int i = 0; i < this.xCoordinates.length; i++) {
			this.yCoordinates[i] = intitialLocation + (i * shift);
		}

	}
	public void setChosen(int index)
	{
		this.chosenOpenings[index] = true;
	}

	public int[] getTileConfig() {
		return tileConfig;
	}
	public void chosenDraw()
	{
		this.chosen = true;
		this.isBlank = false;
		this.repaint();
		this.revalidate();
	}

	public void setTileConfig(int[] tileConfig) {
		this.tileConfig = new int[tileConfig.length];
		for (int i = 0; i < this.tileConfig.length; i++) {
			this.tileConfig[i] = tileConfig[i];
		}

	}

	public void setIsCenter(boolean state) {
		this.isCenter =  state;
	}

	public void createIsFixed() {
		this.isFixedOpening = new boolean[this.tileConfig.length];
	}

	public void rotateClockwise() {
		int[] temp = new int[this.tileConfig.length];
		int openings = temp.length / 4;
		System.out.println("tile was ");
		for (int i = 0; i < this.tileConfig.length; i++)
		{
			System.out.println(i +  " connected with " + tileConfig[i]);
		}
		for (int i = 0; i < this.tileConfig.length - openings; i++) {
			temp[i + openings] = (this.tileConfig[i] + openings)
					% (this.tileConfig.length);
		}
		for (int i = 0; i < openings; i++) {
			temp[i] = (this.tileConfig[(3 * openings) + i] + openings)
					% this.tileConfig.length;
		}
		for (int i = 0; i < this.tileConfig.length; i++) {
			this.tileConfig[i] = temp[i];
		}
		this.repaint();
		System.out.println("After rotating");
		for (int i = 0; i < this.tileConfig.length; i++)
		{
			System.out.println(i +  " connected with " + tileConfig[i]);
		}

	}

	public void rotateAntiClockwise() {
		for (int i = 0; i <= 2; i++) {
			this.rotateClockwise();
		}
	}

	public String toString() {
		String r = "";
		for (int i = 0; i < this.tileConfig.length; i++) {
			r = r + " start = " + i + " end = " + this.tileConfig[i] + "\n";
		}
		return r;
	}



}
