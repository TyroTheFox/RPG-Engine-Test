package core;

public class Staff {
	
	String name;
	int maxTankSize;
	Effect effect;
	boolean equiped = false;
	
	public Staff(String name, int maxTankSize, Effect effect){
		this.name = name;
		this.maxTankSize = maxTankSize;
		this.effect = effect;
	}

}
