package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class EnergyTank {
	
	int energyLevel = 50;
	int maxEnergyLevel = 50;
	int rechargeRate = 2;
	boolean empty = false;
	boolean full = true;
	
	Rectangle tank, energy;
	
	public EnergyTank(int x, int y) {
		tank = new Rectangle(x, y, 10, maxEnergyLevel);
		energy = new Rectangle(x, y, 10, energyLevel);
	//	System.out.println("Energy Tank Created");
	}

	public void render(Graphics g){
		g.draw(tank);
		g.fill(energy);
	}
	
	public void update(){
		energy.setHeight(energyLevel);
		
		if(energyLevel <=0){
			empty = true;
		}
		
		if(energyLevel > 0){
			empty = false;
		}
		
		if(energyLevel >= maxEnergyLevel){
			full = true;
		}
		else{
			full = false;
		}
	}

}
