package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

public class Ant extends MoveableGameObject implements ISteerable {
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
			
	private static Ant ant;
	
	private Ant() {
		super(50, 15,ColorUtil.rgb(252, 3, 3),(int)randomNum(361),randomNum(1001), randomNum(1001));
		this.maximumSpeed = 50;
		this.foodLevel = 20000;
		this.foodConsumptionRate = 1;
		this.healthLevel = 10;
		this.lastFlagReached = 1;
	}
	
	public Ant(int size, int color, float x, float y) {
		super(size, color, x, y);
		maximumSpeed = 50;
		foodLevel = 20;
		foodConsumptionRate = 2;
		healthLevel = 10;
		lastFlagReached = 1;
		
		setSpeed(50);
		setHeading(0);
	}
	
	public static float randomNum(int num) {
		Random r = new Random();
		return (float) r.nextInt(num);
	}
	
	/**
	 * Draw the Ant as a filled circle at correct location
	 * 
	 * @param g
	 * @param pCmpRelPrnt
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		//size of object
		int size = getSize();
		
		//get position of Ant
		int x = (int) (getLocation().getX() - (size / 2) + pCmpRelPrnt.getX());
		int y = (int) (getLocation().getY() - (size / 2) + pCmpRelPrnt.getY());
		
		//width and height should be size to be the radius 
		int width = size;
		int height = size;
		
		//circle starts at 0 degrees and ends at 360 degrees
		int startAngle = 0;
		int arcAngle = 360;  
		
		g.setColor(getColor());	//set the color of the ant
		g.drawArc(x, y, width, height, startAngle, arcAngle);	//draw the circle
		g.fillArc(x, y, width, height, startAngle, arcAngle);	//fill the circle
	}
		
	/**
	 * - handle collision depending on object
	 *
	 * @param otherObject, gw
	 */
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		
		if(otherObject instanceof Spider) {
			
			gw.spiderCollision((Spider) otherObject);
			
		} else if(otherObject instanceof FoodStation) {
			
			gw.foodStationCollision((FoodStation) otherObject);
			
		} else if(otherObject instanceof Flags) {
			
			gw.flagReached(((Flags) otherObject).getSequenceNumber());
		}
		
	}
	
	//Singleton Design Pattern
	/**
	 * Singleton Design Pattern to verify single instance of Ant
	 * @return ant
	 */   
	public static Ant getAnt() {
		if(ant == null) {
			ant = new Ant();
		}
		return ant;
	}
	
	//Getter Methods
	public int getMaximumSpeed() { return maximumSpeed; }
	public int getFoodLevel() { return foodLevel; }
	public int getConsumptionRate() { return foodConsumptionRate; }
	public int getHealthLevel() { return healthLevel; }
	public int getLastFlagReached() { return lastFlagReached; }
	
	public int getSize() { return super.getSize(); }
	public int getColor() { return super.getColor(); }
	public int getSpeed() { return super.getSpeed(); }
	public int getHeading() { return super.getHeading(); }
	
	
	//Setter Methods
	public void setMaximumSpeed(int m) { maximumSpeed = m; }
	public void setFoodLevel(int f) { foodLevel = f; }
	public void setConsumptionRate(int c) {	foodConsumptionRate = c; }
	public void setHealthLevel(int h) { healthLevel = h; }
	public void setLastFlagReached(int f) { lastFlagReached = f; }
	
	public void setColor(int r, int g, int b) { super.setColor(r, g, b);}
	public void setSpeed(int s) { super.setSpeed(s);	}
	public void setHeading(int h) { super.setHeading(h); }
	
	
	//movement methods
	/**
	 * accelerate the ant
	 */
	public void accelerate() {
		int speed = getSpeed();
		int max = getMaximumSpeed();
		int food = getFoodLevel();
		
		if(max < 10 || food > 0) {
			setSpeed(speed+5);
		} else {
			System.out.println("Maximum speed reached!");
		}
		
	}
	
	public void checkMaxSpeed() {
		if (getSpeed() <= maximumSpeed) {
			return;
		} else {
			System.out
					.println("---------You're going too fast at" + getSpeed() + "speed. Slow down!------------");
			ant.setSpeed(10);
		}
	}
	
	/**
	 * brake then ant
	 */
	public void brake() {
		int speed = getSpeed();
		if(speed > 0) {
			if(speed-10 > 0) {
				setSpeed(speed-10);
			}
		}
	}
	
	
	/**
	 * steer the ant left or right
	 */
	public void steer(boolean turn) {
		int curDir = getHeading();
		int curDirLeft = curDir - 5;
		int curDirRight = curDir + 5;
		
		//if turn left decrease 5 degrees if turn right increase 5 degrees
		//check that it doesn't go under 0 or above 359
		if(turn) {
			if(curDirLeft < 0) {
				setHeading(359 + curDirLeft);
			} else {
				setHeading(curDir - 90);
			}
		} else {
			if(curDirRight > 359) {
				setHeading(curDirLeft - 359);
			} else {
				setHeading(curDir + 90);
			}
		}
	}
	
	/**
	 * the ant took damage
	 */
	public void tookDamage() {
		setHealthLevel(getHealthLevel() - 2);
		setColor(super.getRed()-5, 0, 0);
		brake();

		if (getHealthLevel() == 0) {
			setSpeed(0);
		}	
	}
	
	//move method
	/**
	 * this method moves the ant
	 */
	public void move(int elapsedTime, Dimension dCmpSize) { 
		super.move(elapsedTime, dCmpSize);
	}
	
	public void clear() {
		ant = null;
	}
	
	//toString method
	public String toString() {
		String parentString = super.toString();
		String thisString = "size = " + getSize() 
							+ " maxSpeed = " + getMaximumSpeed() 
							+ " foodConsumptionRate = " + getConsumptionRate() + " ";		
		return "Ant: " + parentString + thisString;
	}
}
