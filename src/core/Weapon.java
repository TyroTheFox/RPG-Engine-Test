package core;

public class Weapon {
	
	String name;
	
	int attack;
	float shotSpeed;
	int clipSize;
	int clip;
	GunCylinder gunCyl;
	Staff staff;
	GunBarrel gunBar;
	GunStock gunStock;
	

	public Weapon(String name, GunCylinder gunCyl, Staff staff, GunBarrel gunBar, GunStock gunStock){
		
		this.gunCyl = gunCyl;
		this.staff = staff;
		this.gunBar = gunBar;
		this.gunStock = gunStock;
		shotSpeed = gunCyl.shotSpeed;
		clipSize = gunCyl.maxSize;
		clip = clipSize;
		
		attack = gunBar.attackBonus + gunCyl.attackBonus;
	}
	
}
