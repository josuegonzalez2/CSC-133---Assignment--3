package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Dimension;

import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable {
	private GameObjectCollection objects;
	
	private int elapsedTime, playerLives;
	private boolean sound, pause, pressed;
	private double height, width;
	private Sound flagCollision, fsCollision, spiderCollision;
	private BGSound background;
	
	
	
	public GameWorld() {
		//code here to create initial game objects/setup
		this.elapsedTime = 0;
		this.playerLives = 3;
		this.sound = false;
		this.pause = false;
		this.pressed = false;
		
		//create collection of objects
		objects = new GameObjectCollection();
	}
	
	public void init() {
		
		//add objects
		createObjects();
	}
	
	/* additional methods here to manipulate world objects 
	 * and related game state data 
	 */
	
	public GameObjectCollection getObjects() {
		return objects;
	}
	
	public BGSound getBackground() {
		return background;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	public int getPlayerLives() {
		return playerLives;
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public boolean getPaused() {
		return pause;
	}
	
	public double getMapWidth() {
		return width;
	}
	
	public double getMapHeight() {
		return height;
	}
	
	public void setElapsedTime(int t) {
		this.elapsedTime = t;
		this.setChanged();
	}
	
	public void setMapWidth(double width) {
		this.width = width;
		
		setChanged();
	}
	
	public void setMapHeight(double height) {
		this.height = height;
		
		setChanged();
	}
	
	public void setSound(boolean sound) {
		this.sound = sound;
		
		setChanged();
	}
	
	public void setPaused(boolean pause) {
		this.pause = pause;
		setChanged();
	}
	
	public void setPlayerLives(int playerLives) {
		this.playerLives = playerLives;
		setChanged();
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
		setChanged();
	}
	
	public boolean getPressed() {
		return pressed;
	}
	
	
	//create gameObjects
	public void createObjects() {
		Random r = new Random();
		
		
		Spider spider = new Spider(25, ColorUtil.rgb(0, 0, 0), (float)r.nextInt(1001), (float)r.nextInt(1001));
		Spider spider2 = new Spider(20, ColorUtil.rgb(0, 0, 0), (float)r.nextInt(1001), (float)r.nextInt(1001));
		
		FoodStation foodStation = new FoodStation(15, ColorUtil.rgb(0, 255, 0), (float)350.8, (float)350.6);
		FoodStation foodStation2 = new FoodStation(20, ColorUtil.rgb(0, 255, 0), (float)900.0, (float)700.4);
		
		Flags flag1 = new Flags(1, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag2 = new Flags(2, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag3 = new Flags(3, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag4 = new Flags(4, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag5 = new Flags(5, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag6 = new Flags(6, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag7 = new Flags(7, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag8 = new Flags(8, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		Flags flag9 = new Flags(9, 30, ColorUtil.rgb(0, 0, 255), (float)r.nextInt(1001),(float)r.nextInt(1001));
		
		//Ant ant = new Ant(40, ColorUtil.rgb(255, 0, 0), flag1.getValueX(), flag1.getValueY());
		float antx = flag1.getLocation().getX();
		float anty = flag1.getLocation().getY();
		
		Ant ant = Ant.getAnt();
		
		ant.setLocation(antx, anty);
		
		//add objects
		objects.add(ant);
		objects.add(spider);
		objects.add(spider2);
		objects.add(foodStation);
		objects.add(foodStation2);
		objects.add(flag1);
		objects.add(flag2);
		objects.add(flag3);
		objects.add(flag4);
		objects.add(flag5);
		objects.add(flag6);
		objects.add(flag7);
		objects.add(flag8);
		objects.add(flag9);
		
		//update
		setChanged();
	}
	
	/**
	 * create sounds with selected files.
	 */
	public void createSounds() {
		background = new BGSound("background.wav");
		
		flagCollision = new Sound("flagCollision.wav");
		fsCollision = new Sound("foodStationCollision.wav");
		spiderCollision = new Sound("spiderCollision.wav");
	}

	
	//find objects
	public Ant findAnt() {
		IIterator iterator = objects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject obj = (GameObject) iterator.getNext();
			
			if(obj instanceof Ant) {
				setChanged();
				return (Ant) obj;
			}
		}
		
		return null;
	}
	
	public Spider findSpider() {
		IIterator iterator = objects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject obj = (GameObject) iterator.getNext();
			
			if(obj instanceof Spider) {
				return (Spider) obj;
			}
		}
		
		return null;
	}

	public FoodStation findFoodStation() {
		IIterator iterator = objects.getIterator();
		int pointer = 0;
		
		while(iterator.hasNext()) {
			GameObject obj = (GameObject) iterator.getNext();
			
			if(obj instanceof FoodStation) {
				//return (FoodStation) obj;
				FoodStation fs = (FoodStation) objects.getObjAt(pointer);
				if(fs.getCapacity() != 0) {
					return (FoodStation) objects.getObjAt(pointer);
				}

			}
			pointer++;

		}
		
		return null;
	}
	
	public Flags findFlags() {
		IIterator iterator = objects.getIterator();
		int pointer = 0;
		int seqNum = 0;
		Flags flag = new Flags();
		Flags f = new Flags();
		
		while(iterator.hasNext()) {
			GameObject obj = (GameObject) iterator.getNext();
			
			if(obj instanceof Flags) {
				f = (Flags) objects.getObjAt(pointer);
				int temp = f.getSequenceNumber();
				if(seqNum < temp) {
					flag = (Flags) objects.getObjAt(pointer);
					if(!(iterator.hasNext())) {					
						return flag;
					}
					seqNum = temp;
				}
				
			}
			pointer++;
		}
		
		return null;
	}
	
	public Flags getLastFlag() {
		for(int i=objects.getSize(); i >= 1; i--) {			
			GameObject obj = objects.getObjAt(i-1);
						
			if(obj instanceof Flags) {
				return (Flags) obj;
			}
		}
		return null;
	}
	
	private void checkCollision() {
		
		IIterator iter = objects.getIterator();
		ICollider currentObject;
		IIterator secondIter;
		
		
		while (iter.hasNext()) {
			
			currentObject = (ICollider) iter.getNext();
			secondIter = objects.getIterator();
			ICollider otherObject;

				while (secondIter.hasNext()) {

					otherObject = (ICollider) secondIter.getNext();
					
					if (currentObject != otherObject) {
						
						if (currentObject.collidesWith((GameObject) otherObject)) {
							
							currentObject.handleCollision((GameObject) otherObject, this);

						}
					}
				}
			}
		
		this.setChanged();
	}
	
	//ant movement methods
	/**
	 * The ant accelerates by a tiny amount
	 */
	public void accelerate() {
		Ant ant = Ant.getAnt();
		//Ant ant = findAnt();
		ant.accelerate();
		ant.checkMaxSpeed();
		setChanged();
	}
	
	/**
	 * The ant decreases its speed by a tiny amount
	 */
	public void brake() {
		Ant ant = Ant.getAnt();
		ant.brake();

		setChanged();
	}
	 
	/**
	  * Turns the ant to the left by 5 degrees
	  */
	public void turnLeft() {
		Ant ant = Ant.getAnt();
		ant.steer(true);

		setChanged();
	}
	  
	  /**
	   * Turns the ant to the right by 5 degrees
	   */
	public void turnRight() {
		Ant ant = Ant.getAnt();
		ant.steer(false);

		setChanged();
	}
	   
	//gameObject Collisions
	/**
	  * Ant has collided with a food station
	  * - Ant's food level adds capacity of food station
	  * - Food Station:
	  * 	+ capacity decreases
	  * 	+ size decreases
	  * 	+ color fades
	  * - New food station is created randomly
	  */
	public void foodStationCollision(FoodStation foodStation) {
		//find game objects
		Ant ant = Ant.getAnt();
		//FoodStation foodStation = findFoodStation();
		
		if(foodStation.getCapacity() != 0) {
		
		
			//Ants food level is increased according to food station capacity
			ant.setFoodLevel(ant.getFoodLevel() + foodStation.getCapacity());
		
			//decreases food station attributes
			foodStation.setCapacity(0);
			foodStation.setColor(144,238,144);
		
			//create new food station with random size and location
			Random r = new Random();
			int cap = r.nextInt(16)+5;
			//int objId = foodStation.getObjId();
		
			//create new food station
			//FoodStation newFS = new FoodStation(cap, ColorUtil.rgb(0, 255, 0), (float)r.nextInt(700), (float)r.nextInt(700));
			//objects.add(newFS);
			if (this.getSound()) {
				fsCollision.play();
			}
		}
		
		
		
		//update game
		setChanged();
	}
	   
	/**
	  * The ant has collided with a spider
	  * - health level decreases
	  * - fade color of ant
	  * - reduce speed of ant
	  * - if ant can no longer move then ant looses
	  * - if game ends the player loses a life
	  * - if player loses all three lives then player loses 
	  */
	public void spiderCollision(Spider spider) {
		Ant ant = Ant.getAnt();
		
		ant.tookDamage();
		System.out.println("You have hit a spider!");
		
		//objects.remove(spider);
		
		if (this.getSound()) {
			spiderCollision.play();
		}
		
		checkLives();
		setChanged();
	}
	
	/**
	 * The ant has reached a flag
	 * - If ant reaches the last flag then end the game
	 * - If flag reached is the next one after lastFlagReached
	 * 		then setLastFlagReached to flagNum
	 * 
	 * @param flagNum
	 */
	public void flagReached(int flagNum) {
		Ant ant = Ant.getAnt();
		Flags flag = getLastFlag();
						
		int flagReached = ant.getLastFlagReached();
		int seqNum = flag.getSequenceNumber();

		if(flagReached+1 == flagNum) {
			
			if (this.getSound()) {
				flagCollision.play();
			} 
			
			if(flagNum == seqNum) {
				System.out.println("Congratulations, you won!");
				System.exit(0);
			}
			
			if(flagNum-1 == flagReached) {
				ant.setLastFlagReached(flagNum);
			}
		}
		
		setChanged();
	}
	
	public void checkLives() {
		Ant ant = Ant.getAnt();
		
		if (ant.getHealthLevel() == 0 || ant.getFoodLevel() == 0 && playerLives > 0) {
			
			this.setPlayerLives(getPlayerLives() - 1);

			if (playerLives == 0) {
				System.out.println("Game over! You have no more lives. \n Time passed: " + elapsedTime);
				System.exit(0);

			} else {
				objects.clearObjs();
				ant.clear();
				init();
			}

		}
		this.setChanged();
	}  
	
	//game attributes
	/**
	  * The game world has ticked
	  * - ant moves
	  * - food level decreases according to consumptionRate
	  * - elapsedTime increases
	  * - draw object figures every tick
	  */
	public void worldTick(int elapsedTime, Dimension dCmpSize) {
		Ant ant = Ant.getAnt();
		
		setMapWidth(dCmpSize.getWidth());
		setMapHeight(dCmpSize.getHeight());
		
		ant.move(elapsedTime, dCmpSize);
		ant.setFoodLevel(ant.getFoodLevel() - ant.getConsumptionRate());
		
		IIterator iter = objects.getIterator();
		Random r = new Random();
		GameObject obj;
		
		while(iter.hasNext()) {
			obj = (GameObject) iter.getNext();
			
			
			if(obj instanceof Flags) {
				//System.out.println("Width: " + getMapWidth() + "\nHeight: " + getMapHeight());
				if(obj.getLocation().getX() >= getMapWidth()) {
					obj.setLocation((float) r.nextInt((int)getMapWidth()+50), obj.getLocation().getY());	
					if(((Flags) obj).getSequenceNumber() == 1) {
						ant.setLocation(obj.getLocation().getX(), obj.getLocation().getY());
					}
				} else if(obj.getLocation().getY() >= getMapHeight()) {
					obj.setLocation(obj.getLocation().getX(), (float) r.nextInt((int)getMapHeight()+50));
					if(((Flags) obj).getSequenceNumber() == 1) {
						ant.setLocation(obj.getLocation().getX(), obj.getLocation().getY());
					}
				}
				
				
			} else if(obj instanceof FoodStation) {
				if(obj.getLocation().getX() >= getMapWidth()) {
					obj.setLocation((float) r.nextInt((int)getMapWidth()+50), obj.getLocation().getY());
				} else if(obj.getLocation().getY() >= getMapHeight()) {
					obj.setLocation(obj.getLocation().getX(), (float) r.nextInt((int)getMapHeight()+50));
				}
			} else if(obj instanceof Spider) {
				Spider spider = (Spider) obj;
				spider.move(elapsedTime, dCmpSize);
				
				if(obj.getLocation().getX() >= getMapWidth()) {
					obj.setLocation((float) r.nextInt((int)getMapWidth()+50), obj.getLocation().getY());
				} else if(obj.getLocation().getY() >= getMapHeight()) {
					obj.setLocation(obj.getLocation().getX(), (float) r.nextInt((int)getMapHeight()+50));
				}
			} 
		}
		
		if(getSound()) {
			background.play();
		}
		
		checkCollision();		
		setElapsedTime(this.elapsedTime+1);
		setChanged();
	}

	   
	/**
	  * Displays all current game values
	  * - Number of lives left
	  * - The current clock value (elapsedTime)
	  * - The highest flag number the ant has reached
	  * - The ants current food level
	  * - The ants current health level
	  */
	public void display() {
		Ant ant = Ant.getAnt();
		
		System.out.println("\nLives: " + playerLives
				+ "\nElapsedTime:  " + elapsedTime
				+ "\nHighest flag reached: " + ant.getLastFlagReached()
				+ "\nFood level: " + ant.getFoodLevel()
				+ "\nHealth Level: " + ant.getHealthLevel());		
	}
	   
	/**
	  * Output the map
	  * - displays current objects in the game and their values
	  */
	public void map() {
		IIterator iterator = objects.getIterator();
		
		System.out.println("\n-----------------------------------------------------------------------------");
		
		while(iterator.hasNext()) {
			GameObject obj = (GameObject) iterator.getNext();
			if(obj instanceof Ant) {
				System.out.println(obj.toString());
			} else if(obj instanceof Spider) {
				System.out.println(obj.toString());
			} else if(obj instanceof FoodStation) {
				System.out.println(obj.toString());
			} else if(obj instanceof Flags) {
				System.out.println(obj.toString());
			}
		}
		
		System.out.println("-----------------------------------------------------------------------------\n");
	}
	   
	/**
	  * Exits and ends the game
	  * 
	  * @param exit
	  */
	public void gameExit(boolean exit) {
		if(exit == true) {
			System.out.println("Thank you for playing!");
			System.exit(0);
		}
	}
}
