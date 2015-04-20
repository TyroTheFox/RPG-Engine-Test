package core;

import java.util.ArrayList;

public class Inventory {
	
	ArrayList<Item> inventory;
	
	public Inventory(){
		inventory = new ArrayList<Item>();
	}
	
	public void addItem(GunCylinder gc){
		inventory.add(new Item(gc));
	}
	
	public void addItem(GunBarrel gb){
		inventory.add(new Item(gb));
	}
	
	public void addItem(GunStock gs){
		inventory.add(new Item(gs));
	}
	
	public void addItem(Staff s){
		inventory.add(new Item(s));
	}
	
	public void addItem(Consumable c){
		inventory.add(new Item(c));
	}
	
	public GunCylinder getItem(GunCylinder gc){
		  for(Item item : inventory) {
		      if(item.gc.equals(gc)){
		    	  return item.gc;
		      }
		  }
		  
		  return null;
	}
	
	public Staff getItem(Staff s){
		  for(Item item : inventory) {
		      if(item.staff.equals(s)){
		    	  return item.staff;
		      }
		  }
		  
		  return null;
	}
	
	public GunBarrel getItem(GunBarrel gb){
		  for(Item item : inventory) {
		      if(item.gb.equals(gb)){
		    	  return item.gb;
		      }
		  }
		  
		  return null;
	}
	
	public GunStock getItem(GunStock gs){
		  for(Item item : inventory) {
		      if(item.gs.equals(gs)){
		    	  return item.gs;
		      }
		  }
		  
		  return null;
	}
	
	public Consumable getItem(Consumable c){
		  for(Item item : inventory) {
		      if(item.c.equals(c)){
		    	  return item.c;
		      }
		  }
		  
		  return null;
	}
	
	public void equipItem(int i){
		
		if(inventory.get(i).itemType == 1){
			Logic.equip(inventory.get(i).gc);
		}
		
		if(inventory.get(i).itemType == 2){
			Logic.equip(inventory.get(i).staff);
		}
		
		if(inventory.get(i).itemType == 3){
			Logic.equip(inventory.get(i).gb);
		}
		
		if(inventory.get(i).itemType == 4){
			Logic.equip(inventory.get(i).gs);
		}
		
		if(inventory.get(i).itemType == 5){
			Logic.equip(inventory.get(i).c);
		}
	}

}
