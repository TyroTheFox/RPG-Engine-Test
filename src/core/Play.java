package core;

import java.io.InputStream;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

public class Play extends BasicGameState{
	
	private BlockMap worldMap;
	
	public Actor test;
	
	Cell[][] grid;
	Cell[] temp;
	TiledMap map;
	
	Room currentRoom;
	
	//Movement Controls
	Boolean movingUp = false;
	Boolean movingDown = false;
	Boolean movingLeft = false;
	Boolean movingRight = false;
	
	public static Logic logic;
	
	public Play(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		logic = new Logic();
		
		currentRoom = Logic.currentRoom;
		
		map = Logic.map;
		worldMap = Logic.worldMap;
		grid = worldMap.getGrid();
		
		test = Logic.currentPlayer;
		
//	    currentRoom = new Room("res/untitled.tmx", 0);
//		
//	    InputStream iS = ResourceLoader.getResourceAsStream(currentRoom.getMap());
//	    map = new TiledMap(iS, "res");
//	    
//	    worldMap = new BlockMap(map);
//	    worldMap.generateMap(300, 300);
//	    grid = worldMap.getGrid();
//		
//		test = new Actor("Toivo", grid, 
//				"res/Toivo SS Left.png", "res/Toivo SS Right.png", 
//				"res/Toivo SS Left.png", "res/Toivo SS Right.png", 
//				"res/Toivo SS Idle Left.png", "res/Toivo SS Idle Right.png", 
//				93, 100);	    
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		map.render(100, 100);
		
		for (int a = 0; a < map.getWidth(); a++) {
			for (int b = 0; b < map.getHeight(); b++) {
				if(grid[a][b].isWall()){
					g.draw(grid[a][b].rect);
				}
			}
		}
		
		test.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		sbg.enterState(2);
		//Controls
	      //Moves Left - Left Key
	      if(input.isKeyDown(Input.KEY_LEFT))
	      {
	         movingLeft = true;
	      }
	      else{
	    	  movingLeft = false;
	      }
	      //Moves Right - Right Key
	      if(input.isKeyDown(Input.KEY_RIGHT))
	      {
	         movingRight = true;
	      }
	      else{
	    	  movingRight = false;
	      }
	      //Moves Up - Up Key
	      if(input.isKeyDown(Input.KEY_UP))
	      {
	         movingUp = true;
	      }
	      else{
	    	  movingUp = false;
	      }
	      //Moves Down - Down Key
	      if(input.isKeyDown(Input.KEY_DOWN))
	      {
	         movingDown = true;
	      }
	      else{
	    	  movingDown = false;
	      }
	      
			for (int a = 0; a < map.getWidth(); a++) {
				for (int b = 0; b < map.getHeight(); b++) {
					if(grid[a][b].isWall()){
						if(test.upSensor.intersects(grid[a][b].rect)){
							movingUp = false;
						}
						if(test.downSensor.intersects(grid[a][b].rect)){
							movingDown = false;
						}
						if(test.leftSensor.intersects(grid[a][b].rect)){
							movingLeft = false;
						}
						if(test.rightSensor.intersects(grid[a][b].rect)){
							movingRight = false;
						}
					}
				}
			}
				      
	      
	      //Toggles each movement based on button presses
	      //Left
	      if(movingLeft)
	      {
	    	  test.moveLeft(grid);
	      }
	      //Right
	      if(movingRight)
	      {
	    	  test.moveRight(grid);
	      }
	      //Up
	      if(movingUp)
	      {
	    	  test.moveUp(grid);
	      }
	      //Down
	      if(movingDown)
	      {
	    	  test.moveDown(grid);
	      }
	}

	@Override
	public int getID() {
		return 0;
	}

}
