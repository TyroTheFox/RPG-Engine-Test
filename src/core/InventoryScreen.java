package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InventoryScreen extends BasicGameState {

	public InventoryScreen(int state){}
	
	Inventory temp;
	ArrayList<String> displayList;
	Rectangle cursor;
	int cursorPosition = 0;
	int cursorX = 40, cursorY = 55;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		temp = Logic.currentPlayer.inventory;
		displayList = new ArrayList<String>();
		
		  for(Item item : temp.inventory) {
			  if(item.itemType == 5){
				  if(!item.c.consumed){
					  displayList.add(item.returnName());
				  }
			  }
			  else{
				  displayList.add(item.returnName());
			  }
		  }
		
		cursor = new Rectangle(cursorX, cursorY  + (20 * cursorPosition), 5, 5);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		  int a = 0;
		  for(String s: displayList) {
			  g.drawString(s, 50, 50  + (20 * a));
			  a++;
		  }
		 
		  int b = 0;
		  for(Item item : temp.inventory) {
			  if(item.getEquiped()){
				  g.setColor(Color.red);
				  g.fillRect(35, 55 + (20 * b), 5, 5);
				  g.setColor(Color.white);
			  }
			  b++;
		  }
		  
		  g.drawString("Name: " + Logic.currentPlayer.name, 400, 200);
		  g.drawString("HP: " + Logic.currentPlayer.HP, 400, 220);
		  g.drawString("Maximum Tank: " + Logic.currentPlayer.maxTank, 400, 240);
		  g.drawString("Staff: " + Logic.currentPlayer.weapon.staff.name, 400, 260);
		  g.drawString("Gun Barrel: " + Logic.currentPlayer.weapon.gunBar.name, 400, 280);
		  g.drawString("Gun Cylinder: " + Logic.currentPlayer.weapon.gunCyl.name, 400, 300);
		  g.drawString("Gun Stock: " + Logic.currentPlayer.weapon.gunStock.name, 400, 320);
		  
		  g.drawString("Cursor: " + cursorPosition, 150, 20);
		  
		  g.fill(cursor);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		//Controls
	      //Moves Left - Left Key
	      if(input.isKeyPressed(Input.KEY_UP))
	      {
	         if(cursorPosition != 0){
	        	 cursorPosition -= 1;
	         }
	      }
	      //Moves Right - Right Key
	      if(input.isKeyPressed(Input.KEY_DOWN))
	      {
	         if(cursorPosition < displayList.size()-1){
	        	 cursorPosition += 1;
	         }
	      }
	      
	      //Moves Right - Right Key
	      if(input.isKeyPressed(Input.KEY_SPACE))
	      {
	         Logic.currentPlayer.causeDamage(30f, false);
	      }
	      
	      if(input.isKeyPressed(Input.KEY_ENTER))
	      {
	    	  temp.equipItem(cursorPosition);
	    	  
	    	  displayList.clear();
			  for(Item item : temp.inventory) {
				  if(item.itemType == 5){
					  if(!item.c.consumed){
						  displayList.add(item.returnName());
					  }
				  }
				  else{
					  displayList.add(item.returnName());
				  }
			  }
	      }
	      
	      cursor.setLocation(cursorX, cursorY  + (20 * cursorPosition));
	}

	@Override
	public int getID() {
		return 2;
	}

}
