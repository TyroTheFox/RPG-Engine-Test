package core;

public class GunCylinder {
	String name;
	int maxSize;
	int size;
	float shotSpeed;
	int attackBonus;
	Effect effect1;
	boolean equiped = false;
	
	public GunCylinder(String name, int maxSize, float shotSpeed, Effect effect, int attackBonus){
		this.name = name;
		this.maxSize = maxSize;
		size = this.maxSize;
		effect1 = effect;
		this.attackBonus = attackBonus;
	}
}