package core;

public class Weapon {
	
	String name;
	
	int attack;
	float shotSpeed;
	int clipSize;
	int clip;
	

	public Weapon(String name, int attack, float shotspeed, int clipSize){
		
		this.name = name;
		this.attack = attack;
		this.shotSpeed = shotspeed;
		this.clipSize = clipSize;
		
		clip = clipSize;
	}
	
}
