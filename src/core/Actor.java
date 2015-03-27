package core;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Actor {

	Animation current;
	  Animation movingUp;
	  Animation movingDown;
	  Animation movingLeft;
	  Animation movingRight;
	  Animation idleLeft;
	  Animation idleRight;
	  Image moveUp;
	  Image moveDown;
	  Image moveLeft;
	  Image moveRight;
	  Image stayLeft;
	  Image stayRight;
	  Boolean moveInProgress;
	  int currentMoveStep = 14;
	  Cell currentPosition;
	  Cell nextPosition;
	  Cell actionZone;
	  Cell[][] grid;
	  int actionList = 0;
	  int target = 0;
	  int actionPos = 0;
	  String action;
	  boolean chosenTarget = false;
	  boolean busy = false;
	  boolean startedMoving = false;
	  float timer = 0;
	  float timer2 = 0;
	  float timer3 = 0;
	  float constantTimer = 0;
	  String name;
	  int maxHP = 150;
	  int HP = 300;
	  int speed = 20;
	  int strength = 50;
	  int special = 80;
	  int maxEnergy = 300;
	  int maxTank = 2;
	  public ArrayList<EnergyTank> energyReserves;
	  public EnergyTank temp;
	  int currentTank;
	  int defence = 10;
	  int energy = 100;
	  private float movementSpeedX = 0.6F;
	  private float movementSpeedY = 0.6F;
	  Rectangle boundingBox, upSensor, downSensor, leftSensor, rightSensor;
	  boolean stopLeft = false, stopRight = false, stopUp = false, stopDown = false;
	  boolean finishedX = false;
	  boolean finishedY = false;
	  int positionX = 5;
	  int positionY = 5;
	  float x;
	  float y;
	  
	  boolean energyCutoff = false;
	  
	  Weapon weapon;
	  
	    //Character Flags
	    boolean actionA = false, actionB = false, 
	    		lingerA = false, lingerB = false,
	    		spellA = false, lSpellA = false,
	    		lingerConstant = false;
	    
	    Action aAction, bAction, spell, constantEffect;
	  
	  public Actor(String name, Cell[][] grid, String upImage, String downImage, String leftImage, String rightImage, String standLeft, String standRight, int tileWidth, int tileHeight)
	  throws SlickException{
		  
		  this.grid = grid;
		  
		  this.name = name;
		  
		  moveUp = new Image(upImage);
		    SpriteSheet up = new SpriteSheet(this.moveUp, tileWidth, tileHeight);
		  moveDown = new Image(downImage);
		    SpriteSheet down = new SpriteSheet(this.moveDown, tileWidth, tileHeight);
		  moveLeft = new Image(leftImage);
		    SpriteSheet left = new SpriteSheet(this.moveLeft, tileWidth, tileHeight);
		  moveRight = new Image(rightImage);
		    SpriteSheet right = new SpriteSheet(this.moveRight, tileWidth, tileHeight);
		    
		  stayLeft = new Image(standLeft);
		    SpriteSheet idleL = new SpriteSheet(standLeft, tileWidth, 110);
		  stayRight = new Image(standRight);
		    SpriteSheet idleR = new SpriteSheet(standRight, tileWidth, 110);
		    

		  movingUp = new Animation(true);
		    for (int frame = 6; frame >= 0; frame--) {
		    	movingUp.addFrame(up.getSprite(frame, 0), 130);
		    }
		  movingDown = new Animation(true);
		    for (int frame = 6; frame >= 0; frame--) {
		    	movingDown.addFrame(down.getSprite(frame, 0), 130);
		    }
		  movingLeft = new Animation(true);
		    for (int frame = 6; frame >= 0; frame--) {
		    	movingLeft.addFrame(left.getSprite(frame, 0), 130);
		    }
		  movingRight = new Animation(true);
		    for (int frame = 6; frame >= 0; frame--) {
		    	movingRight.addFrame(right.getSprite(frame, 0), 130);
		    }
		  idleLeft = new Animation(true);
		    
		  idleLeft.addFrame(idleL.getSprite(0, 0), 130);
		    

		  idleRight = new Animation(true);
		    
		  idleRight.addFrame(idleR.getSprite(0, 0), 130);
		    
		  current = new Animation();
		  current = this.idleRight;

		  currentPosition = grid[this.positionX][this.positionY];
		    
		  nextPosition = grid[this.positionX][this.positionY];
		  actionZone = grid[(this.positionX - 1)][this.positionY];
		    
		  x = (this.currentPosition.rect.getX() - 60.0F);
		  y = (this.currentPosition.rect.getY() - 100.0F);
		    
		  boundingBox = new Rectangle(this.x, this.y, 32.0F, 32.0F);
		  upSensor = new Rectangle(x + 5, y - 2, 22, 2);
		  downSensor = new Rectangle(x + 5, y + 32, 22, 2);
		  leftSensor = new Rectangle(x - 2, y + 5, 2, 22);
		  rightSensor = new Rectangle(x + 32, y + 5, 2, 22);
		  
	  }
	  
	  public Actor(){		  
		  
		  energyReserves = new ArrayList<EnergyTank>();
	  
//	  for(int e = 0; e < maxTank; e++){
		  energyReserves.add(new EnergyTank(30, 125));
		  energyReserves.add(new EnergyTank(30, 125 + 60));
//		  System.out.println(energyReserves[e].full + "/" + energyReserves[e].energyLevel);
//	  }
	  
	  System.out.println("Array Size on Creation: " + energyReserves.size());
	  System.out.println("Array Empty?: " + energyReserves.isEmpty());
	  System.out.println("Array: " + energyReserves.toString());
	  currentTank = energyReserves.size() - 1;
	  }
	  
	  public void setPosition(float x, float y){
		  this.x = x;
		  this.y = y;
	  }
	  
	  public Rectangle getBox()
	  {
	    return this.boundingBox;
	  }
	  
	  public void moveLeft(Cell[][] grid)
	  {
		  x -= movementSpeedX;
		  this.current = this.movingLeft;
	  }
	  
	  public void moveRight(Cell[][] grid)
	  {
		  x += movementSpeedX;
	      current = movingRight;
	  }
	  
	  public void moveUp(Cell[][] grid)
	  {
		  if(!stopUp){
			  y -= movementSpeedY;
		      current = movingUp;
		  }
	  }
	  
	  public void moveDown(Cell[][] grid)
	  {
		  y += movementSpeedY;
	      current = movingDown;
	  }
	  
		/**
		 * Do A Action
		 * @param e
		 * @param delta
		 */
		public void doAAction(Actor p, Enemy e, int delta){
			if(!actionB){
		        aAction.actionEffect(p, e, delta);
			}
		}
		
		public void doSpell(Actor p, Enemy e, int delta){
			if(!actionB){
		        spell.actionEffect(p, e, delta);
			}
		}
		
		/**
		 * Do B Action
		 * @param e
		 * @param delta
		 */
		public void doBAction(Actor p, Enemy e, int delta){
			if(!spellA){
		        bAction.actionEffect(p, e, delta);
			}
		}
		
		/**
		 * Do Constant Effect
		 * @param e
		 * @param delta
		 */
		public void doConstantEffect(Actor p, Enemy e, int delta){
			constantEffect.actionEffect(p, e, delta);
		}
		
	    /**
	     * Lingering Actions Check
	     * Performs Lingering Actions Until they're turned off
	     * as well as the lingering effect of the Speed Modifier
	     * Control
	     * @param e
	     * @param delta
	     */
	    public void lingeringActionsCheck(Actor p, Enemy e, int delta){
	    	if(lingerA){
	    		aAction.lingeringEffect(p, e, delta);
	    	}
	    	if(lingerB){
	    		bAction.lingeringEffect(p, e, delta);
	    	}
	    	if(lSpellA){
	    		spell.lingeringEffect(p, e, delta);
	    	}
	    }
	    
	    /**
	     * Constant Lingering Actions Check
	     * Performs Lingering Actions Until they're turned off
	     * as well as the lingering effect of the Speed Modifier
	     * Control
	     * @param e
	     * @param delta
	     */
	    public void constantLingeringActionsCheck(Actor p, Enemy e, int delta){
	    	if(lingerConstant){
	    		constantEffect.lingeringEffect(p, e, delta);
	    	}
	    }
	    
	    /**
	     * Add A Action
	     * @param a
	     */
	    public void addSpell(Action a){
	    	spell = a;
	    }
	    
	    /**
	     * Add A Action
	     * @param a
	     */
	    public void addAAction(Action a){
	    	aAction = a;
	    }
	    
	    /**
	     * Add A Action
	     * @param b
	     */
	    public void addBAction(Action b){
	    	bAction = b;
	    }
	    
	    /**
	     * Add Constant Effect
	     * @param b
	     */
	    public void addConstantEffect(Action cE){
	    	constantEffect = cE;
	    }
	    
	    public void addWeapon(Weapon w){
	    	weapon = w;
	    }
	    
	    public Weapon getWeapon(){
	    	return weapon;
	    }
	    
	    public void causeDamage(float attack, boolean defend){
	    	
	    	int d = 1;
	    	
	    	if(defend){
	    		d = 0;
	    	}

	    	HP -= (attack - defence) * d;
	    }
	    
	    public boolean energyTankCheck(){
	    	boolean empty = false;
	    	
	    	if(currentTank == 0){
	    		if(energyReserves.get(currentTank).empty){
	    			empty = true;
	    		}
	    	}
	    	
			return empty;	
	    }
	    
	    public boolean fullTankCheck(){
	    	boolean full = false;
	    	
	    	if(currentTank == maxTank - 1){
	    		if(energyReserves.get(currentTank).full){
	    			full = true;
	    		}
	    	}
	    	
			return full;	
	    }
	    
	    public boolean spendEnergy(int cost){
	    	
	    	boolean costAccepted = false;
	    	
	    	if(energyReserves.get(currentTank).energyLevel - cost > 0){
	    		energyReserves.get(currentTank).energyLevel -= cost;
	    		costAccepted = true;
	    	}
	    	
	    	if(energyReserves.get(currentTank).energyLevel - cost <= 0){	    		
	    		if(currentTank != 0){
	    			currentTank -= 1;
	    			
	    			energyReserves.get(currentTank).energyLevel -= cost;
	    			costAccepted = true;
	    		}
	    	}
	    	
	    	return costAccepted;
	    }
	    
	    public void addEnergy(int amount){
	    	if(!energyReserves.get(currentTank).full){
	    		energyReserves.get(currentTank).energyLevel += amount;
	    	}
	    	
	    	if(energyReserves.get(currentTank).full){
	    		energyReserves.get(currentTank).energyLevel = energyReserves.get(currentTank).maxEnergyLevel;
	    		
	    		if(currentTank != maxTank - 1){
	    			currentTank += 1;
	    			energyReserves.get(currentTank).energyLevel += amount;
	    		}
	    	}
	    }
	    
	  public float lerp(float a, float b, float t)
	  {
	    if (t < 0.0F) {
	      return a;
	    }
	    return a + t * (b - a);
	  }
	  
	  public void updateCharacter(Cell[][] grid, int delta, Boolean movingLeft, Boolean movingRight, Boolean movingUp, Boolean movingDown)
	  {
	    float time = 0.015F;
	    
	    boolean tempLeft = movingLeft.booleanValue();
	    boolean tempRight = movingRight.booleanValue();
	    boolean tempUp = movingUp.booleanValue();
	    boolean tempDown = movingDown.booleanValue();
	    if (!busy)
	    {
	      positionX = nextPosition.position.x;
	      positionY = nextPosition.position.y;
	      busy = true;
	    }
	    if ((busy) && (
	      (x != grid[positionX][positionY].getX()) || (y != grid[positionX][positionY].getY())))
	    {
	      x = lerp(x, grid[positionX][positionY].getX(), time);
	      y = lerp(y, grid[positionX][positionY].getY(), time);
	      if ((movingLeft.booleanValue()) || (movingRight.booleanValue()) || (movingUp.booleanValue()) || (movingDown.booleanValue()))
	      {
	        if (((x < grid[positionX][positionY].getX()) && (x > grid[positionX][positionY].getX())) || (
	          (y < grid[positionX][positionY].getY()) && (y > grid[positionX][positionY].getY())))
	        {
	          x = grid[positionX][positionY].getX();
	          y = grid[positionX][positionY].getY();
	        }
	        grid[positionX][positionY].cellType = 0;
	        currentPosition = grid[positionX][positionY];
	        grid[positionX][positionY].cellType = 5;
	      }
	      else
	      {
	        time = 1.0F;
	      }
	    }
	    if (((!movingLeft.booleanValue()) || (!movingRight.booleanValue()) || (!movingUp.booleanValue()) || (!movingDown.booleanValue())) && (busy))
	    {
	      if ((tempLeft) || (tempUp)) {
	        current = idleLeft;
	      }
	      if ((tempRight) || (tempDown)) {
	        current = idleRight;
	      }
	    }
	    timer += delta;
	    if (timer > 250L)
	    {
	      timer = 0L;
	      busy = false;
	    }
	  }
	  
	  public Cell getCurrentLocation(Cell[][] grid)
	  {
	    return grid[positionX][positionY];
	  }
	  
	  public Cell getActionZone()
	  {
	    return actionZone;
	  }
	  
	  public int getSpeed()
	  {
	    return speed;
	  }
	  
	  public int getStrength()
	  {
	    return strength;
	  }
	  
	  public int getSpecial()
	  {
	    return special;
	  }
	  
	  public void increaseEnergy(int eModifier)
	  {
	    if (energy < maxEnergy)
	    {
	      if (energy + eModifier < maxEnergy) {
	        energy += eModifier;
	      }
	      if (energy + eModifier > maxEnergy) {
	        energy = maxEnergy;
	      }
	    }
	  }
	  
	  public void decreaseEnergy(int eModifier)
	  {
	    energy -= eModifier;
	  }
	  
	  public int getEnergy()
	  {
	    return energy;
	  }
	  
	  public void setEnergy(int e)
	  {
	    energy = e;
	  }
	  
	  public String getPlayerName()
	  {
	    return name;
	  }
	  
	  public int getHP()
	  {
	    return HP;
	  }
	  
	  public int getMaxHP()
	  {
	    return maxHP;
	  }
	  
	  public void increaseHP(int hpModifier)
	  {
	    if ((HP < maxHP) && 
	      (HP + hpModifier <= maxHP)) {
	      HP += hpModifier;
	    }
	  }
	  
	  public void decreaseHP(int hpModifier)
	  {
	    HP -= hpModifier;
	  }
	  
	  public void setHP(int newHP)
	  {
	    HP = newHP;
	  }
	  
	  public void render(Graphics g, int x, int y)
	  {
	    g.drawAnimation(current, x, y);
	  }
	  
	  public void render(Graphics g)
	  {
	    boundingBox.setLocation(x, y);
	    
		float a = boundingBox.getCenterX() - 50;
		float b = boundingBox.getCenterY() - 90;
		
		g.drawAnimation(current, a, b);
	    
	    upSensor.setLocation(x + 5, y - 2);
		downSensor.setLocation(x + 5, y + 32);
		leftSensor.setLocation(x - 2, y + 5);
		rightSensor.setLocation(x + 32, y + 5);
		
		g.draw(boundingBox);
		g.draw(upSensor);
		g.draw(downSensor);
		g.draw(leftSensor);
		g.draw(rightSensor);
	  }
	  
	  public void renderEnergyTanks(Graphics g){
		  for(int i = 1; i < maxTank; i++){
			  energyReserves.get(i).render(g);
		  }
	  }
	  
	  public void updateEnergyTanks(){
		  
		  for(EnergyTank tank : energyReserves) {
		      tank.update();
		  }
	  }
	  
	  public int getActionList()
	  {
	    return actionList;
	  }
	  
	  public void setActionList(int a)
	  {
	    actionList = a;
	  }
	  
	  public int getTarget()
	  {
	    return target;
	  }
	  
	  public void setTarget(int a)
	  {
	    target = a;
	  }
	  
	  public void resetActionPos()
	  {
	    actionPos = 0;
	  }
	  
	  public void setTargetFlag(boolean a)
	  {
	    chosenTarget = a;
	  }
	  
	  public boolean getTargetFlag()
	  {
	    return chosenTarget;
	  }
	
}
