package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends FixedGameObject {
	private int capacity;
	
	public FoodStation() {
		
	}
	
	public FoodStation(int size, int color, float x, float y) {
		super(size, color, x, y);
		capacity = size;
	}
	
	//Getter methods
	public int getCapacity() { return capacity; }
	public int getSize() { return super.getSize(); }
	public Point getLocation() { return super.getLocation(); }
	public int getColor() { return super.getColor(); }
	
	//Setter methods
	public void setCapacity(int c) { capacity = c; }
	public void setLocation(float x, float y) { super.setLocation(x,y); }
	public void setColor(int r, int g, int b) { super.setColor(r,g,b); }
	
	/**
	 * - Draw Food Station as a filled square.
	 * - Food Station initial capacity is proportional to its size and the size
	 * 		of a Food Station remain the same even as its capacity decreases.
	 * - Food Station should include text showing their food capacity.
	 * 
	 * @param g
	 * @param pCmpRelPrnt
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (getLocation().getX() - (getSize() / 2) + pCmpRelPrnt.getX());
		int y = (int) (getLocation().getY() - (getSize() / 2) + pCmpRelPrnt.getY());
		int width = getSize();
		int height = getSize();
		String str = String.valueOf(getCapacity());
		
		g.setColor(g.getColor());	//set square color
		g.drawRect(x, y, width, height);	//draw square
		g.drawString(str, x, y);	//write capacity number on square
		
		if(!isSelected()) {
			g.fillRect(x, y, width, height);	//fill square
		}
	}
	
	
	//toString method
	public String toString() {
		String parentString = super.toString();
		String thisString = " size = " + getSize()
							+ " capacity = " + capacity + " ";
		return "FoodStation: " + parentString + thisString;
	}
}
