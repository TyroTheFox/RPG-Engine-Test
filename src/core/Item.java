package core;

public class Item {
	GunCylinder gc;
	Staff staff;
	GunBarrel gb;
	GunStock gs;
	Consumable c;
	String name;
	int itemType = 0;
	boolean equiped = false;
	boolean consumed = true;
	
	public Item(GunCylinder gc){
		this.gc = gc;
		name = gc.name;
		equiped = gc.equiped;
		itemType = 1;
	}
	
	public Item(Staff s){
		staff = s;
		name = s.name;
		equiped = s.equiped;
		itemType = 2;
	}
	
	public Item(GunBarrel gb){
		this.gb = gb;
		name = gb.name;
		equiped = gb.equiped;
		itemType = 3;
	}
	
	public Item(GunStock gs){
		this.gs = gs;
		name = gs.name;
		equiped = gs.equiped;
		itemType = 4;
	}
	
	public Item(Consumable c){
		this.c = c;
		name = c.name;
		consumed = c.consumed;
		itemType = 5;
	}
	
	public String returnName(){		
		return name;
	}
	
	public boolean getEquiped(){
		boolean temp = false;
		
		if(itemType == 1){
			temp = gc.equiped;
		}
		
		if(itemType == 2){
			temp = staff.equiped;
		}
		
		if(itemType == 3){
			temp = gb.equiped;
		}
		
		if(itemType == 4){
			temp = gs.equiped;
		}
		
		if(itemType == 5){
			temp = c.consumed;
		}
		
		return temp;
	}

}
