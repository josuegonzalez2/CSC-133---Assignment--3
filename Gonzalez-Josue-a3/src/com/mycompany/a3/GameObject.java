package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public abstract class GameObject implements IDrawable, ICollider{
	
	
	private Point location;
	private int color;
	private int size;
	
	private int red;
	private int green;
	private int blue;
	private float valueX;
	private float valueY;
		
	public GameObject() {		
	}
	
	public GameObject(int size, int color, float x, float y) {
		this.size = size;
		this.color = color;
		this.location = new Point(x,y);
		this.valueX = x;
		this.valueY = y;
	}
	
	//Getter methods
	public int getColor() { return color; }
	public int getSize() { return size; }
	public Point getLocation() { return location; }
	public float getValueX() { return valueX;}
	public float getValueY() { return valueY; }
	public int getRed() { return red; }
	public int getGreen() { return green; }
	public int getBlue() { return blue; }
	
	
	//Setter methods
	public void setColor(int r, int g, int b) { 
		color = ColorUtil.rgb(r,g,b);
		red = r;
		green = g;
		blue = b;
	}
	public void setLocation(float x, float y) { 
		location = new Point(x,y);
		valueX = x;
		valueY = y;
	}
	public void setValueX(float x) { valueX = x; }
	public void setValueY(float y) { valueY = y; }
	public void setRed(int r) { setColor(r, green, blue); }
	public void setGreen(int g) { setColor(red,g,blue); }
	public void setBlue(int b) { setColor(red,green, b); }
	
	/**
	 * - Will check if bother current objects
	 * 		are in the same position
	 * 
	 * @param otherObject
	 * @return boolean
	 */
	@Override
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		
		double thisCenterX = this.getLocation().getX() + (this.getSize()/2);
		double thisCenterY = this.getLocation().getY() + (this.getSize()/2);
		
		double otherCenterX = otherObject.getLocation().getX() + (otherObject.getSize()/2);
		double otherCenterY = otherObject.getLocation().getY() + (otherObject.getSize()/2);
		
		//find dist between centers (use square, to avoid taking roots)
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCentersSqr = (dx*dx + dy*dy);
		
		//find square of sum of radii
		double thisRadius = this.getSize()/2;
		double otherRadius = otherObject.getSize()/2;
		
		double radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if(distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		
		return result;
	}
	
	//toString method
	public String toString() {		
		return "loc = " + getLocation().getX() + ", " + getLocation().getY() +  
				" color = [" + ColorUtil.red(color) + ", " +  
							   ColorUtil.green(color) + ", " +
							   ColorUtil.blue(color) + "] ";                    
	}
	
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) {}
}
