package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

public class Spider extends MoveableGameObject { 
	Random r = new Random();
	
	public Spider() {
	}
	
	public Spider(int size, int color, float x, float y) {
		super(size, color, x, y);
		setSpeed((r.nextInt(5) + 1) * 10);
		setHeading(0);
	}
	
	
	//Getter methods
	public int getSize() { return super.getSize(); }
	public int getColor() { return super.getColor(); }
	public Point getLocation() { return super.getLocation(); }
	public int getSpeed() { return super.getSpeed(); }
	public int getHeading() { return super.getHeading(); }
	
	
	//Setter methods
	//public void setSize(int s) { super.setSize(s); }
	public void setColor(int r, int g, int b) { super.setColor(r, g, b); }
	public void setLocation(float x, float y) { super.setLocation(x,y); }
	public void setSpeed(int s) { super.setSpeed(s);}
	public void setHeading(int h) { super.setHeading(h); }
	
	/**
	 * Draw spider as an unfilled triangle
	 * 
	 * @param g
	 * @param pCmpRelPrnt
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (getLocation().getX() - (getSize() / 2) + pCmpRelPrnt.getX());
		int y = (int) (getLocation().getY() - (getSize() / 2) + pCmpRelPrnt.getY());
		int[] xPoints = {x - getSize()/2 , x + getSize()/2, x};
		int[] yPoints = {y - getSize()/2 , y - getSize()/2, y + getSize()/2 };
		int nPoints = 3;
		
		g.setColor(getColor());	//set spider color
		g.drawPolygon(xPoints, yPoints, nPoints); //draw triangle
	}
	
	//movement method
	@Override
	public void move(int elapsedTime, Dimension dCmpSize) {
		double width = dCmpSize.getWidth();
		double height = dCmpSize.getHeight();
		double theta = Math.toRadians(90 - getHeading());
		double distance = (getSpeed() * elapsedTime)/1000;

		float deltaX = (float) ((Math.cos(theta) * distance) + super.getValueX());
		float deltaY = (float) ((Math.sin(theta) * distance) + super.getValueY());

		if (deltaX < 0 || deltaX >= width) {
			setHeading(getHeading() + 180);
		}
		if(deltaY < 0 || deltaY >= height) {
			setHeading(getHeading() + 180);
		}

		setLocation(deltaX, deltaY);
		setHeading(getHeading() + (r.nextInt(2)));

	}
	
	
	//toString method 
	public String toString() {
		String parentString = super.toString();
		String thisString = " size = " + getSize() + " ";
		return "Spider: " + parentString + thisString;
		
	}
}
