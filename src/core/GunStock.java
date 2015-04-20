package core;

public class GunStock {

	int defenceBonus;
	int accuracyBonus;
	Effect effect;
	public String name;
	boolean equiped = false;
	
	public GunStock(String name, int defenceBonus, int accuracyBonus, Effect effect){
		this.defenceBonus = defenceBonus;
		this.accuracyBonus = accuracyBonus;
		this.effect = effect;
		this.name = name;
	}
}
