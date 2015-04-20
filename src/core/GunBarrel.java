package core;

public class GunBarrel {
	
	int attackBonus;
	int accuracyBonus;
	Effect effect;
	String name;
	boolean equiped = false;

	public GunBarrel(String name, int attackBonus, int accuracyBonus, Effect effect){
		this.attackBonus = attackBonus;
		this.accuracyBonus = accuracyBonus;
		this.effect = effect;
		this.name = name;
	}
}
