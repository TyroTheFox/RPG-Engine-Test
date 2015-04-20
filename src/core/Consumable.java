package core;

public class Consumable {
	String name;
	Effect effect1;
	boolean consumed = false;
	
	public Consumable(String name, Effect effect){
		this.name = name;
		effect1 = effect;
	}
}
