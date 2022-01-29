   package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer {
	
	private double height;
	private double width;
	private GameWorld gw;
	private FixedGameObject selectedObj;
	
	private GameObjectCollection objects;
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
	}
	
	public void update (Observable o, Object arg) {
		//code here to call the method in GameWorld (Observable)
		//that output the game object information to the console
		this.gw = (GameWorld) o;
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		gw.map();
		
		objects = gw.getObjects();
		
		this.repaint();
	}
	
	public double getMapWidth() {
		return width;
	}
	
	public double getMapHeight() {
		return height;
	}
	
	public void setPressed(boolean pressed) {
		gw.setPressed(pressed);
	}
	
	public boolean getPressed() {
		return gw.getPressed();
	}
	
	public boolean getPaused() {
		return gw.getPaused();
	}
	
	public void setPaused(boolean pause) {
		gw.setPaused(pause);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		GameObject next;
		
		IIterator iter = objects.getIterator();
		
		//Iterate through all game objects to draw all of them
		while(iter.hasNext()) {
			next = (GameObject) iter.getNext();
			next.draw(g, pCmpRelPrnt);
			
		}
		
		this.repaint();
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		if (this.getPaused()) {
										
			if (selectedObj == null && gw.getPressed() == false) {
	
				x = x - getParent().getAbsoluteX();
				y = y - getParent().getAbsoluteY();
	
				Point pPtrRelPrnt = new Point(x, y);
				Point pCmpRelPrnt = new Point(this.getX(), this.getY());
	
				IIterator iter = objects.getIterator();
				GameObject obj;
	
				while (iter.hasNext()) {
					obj = (GameObject) iter.getNext();
					if (obj instanceof FixedGameObject) {
						FixedGameObject FixedObj = (FixedGameObject) obj;
						if (((FixedGameObject) FixedObj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
							
							selectedObj = (FixedGameObject) obj;
							selectedObj.setSelected(true);
							System.out.println("You've selected a object: " + obj.toString());
						}
	
					}
				}
	
			} else if (selectedObj != null && gw.getPressed() == true) {
				selectedObj.setLocation(x - getParent().getAbsoluteX() - getX(), y - getParent().getAbsoluteY());
				selectedObj.setSelected(false);
				selectedObj = null;
				gw.setPressed(false);
			}
		
		}

		this.repaint();
	}
}
