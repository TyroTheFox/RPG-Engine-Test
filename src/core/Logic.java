package core;

import java.io.InputStream;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

public class Logic {
	
	static Actor currentPlayer;
	
	static Actor toivo;
	
	static Enemy currentEnemy;
	
	Enemy test;
	
	Weapon shotgun;
	static GunCylinder noGC;
	static Staff noS;
	static GunBarrel noGB;
	static GunStock noGS;
	
	static Room currentRoom;
	Cell[][] grid;
	Cell[] temp;
	static TiledMap map;
	static BlockMap worldMap;
	
	Consumable cake;
	

	public Logic() throws SlickException{
		
	    currentRoom = new Room("res/untitled.tmx", 0);
		
	    InputStream iS = ResourceLoader.getResourceAsStream(currentRoom.getMap());
	    map = new TiledMap(iS, "res");
	    
	    worldMap = new BlockMap(map);
	    worldMap.generateMap(300, 300);
	    grid = worldMap.getGrid();
	    
		toivo = new Actor("Toivo", grid, 
				"res/Toivo SS Left.png", "res/Toivo SS Right.png", 
				"res/Toivo SS Left.png", "res/Toivo SS Right.png", 
				"res/Toivo SS Idle Left.png", "res/Toivo SS Idle Right.png", 
				93, 100);
		
		test = new Enemy();
		
		noGC = new GunCylinder("Empty", 0, 0, new Effect(){
			@Override
			public void effect() {}}, 0);
		noS = new Staff("Empty", 0, new Effect(){
			@Override
			public void effect() {}});
		noGB = new GunBarrel("Empty", 0, 0, new Effect(){
			@Override
			public void effect() {}});
		noGS = new GunStock("Empty", 0, 0, new Effect(){
			@Override
			public void effect() {}});
		
		shotgun = new Weapon("Shotgun", 
	  			  new GunCylinder("Standard Gun Cylinder", 2, 8000, new Effect(){

					@Override
					public void effect() {
					}
	  				  
	  			  }, 
	  			  7), 
	  			  new Staff("Standard Crystal", 4, new Effect(){

					@Override
					public void effect() {
					}
	  				  
	  			  }), 
	  			  new GunBarrel("Standard Gun Barrel", 5, 5, new Effect(){

					@Override
					public void effect() {
					}
	  				  
	  			  }), 
	  			  new GunStock("Standard Gun Stock", 1, 7, new Effect(){

					@Override
					public void effect() {
					}
				}));
		
		toivo.addWeapon(shotgun);
		
		cake = new Consumable("Cake", new Effect(){

			@Override
			public void effect() {
				toivo.HP += 10;
			}
			
		});
		
		test.addBrain(new Brain(){

			@Override
			public void stateCheck() {
				test.constantTimer = 0;
				test.think = false;
//				if(!defend){
					test.eAttack = true;
//				}
//				else{
//					eAttack = false;
			    	test.eWarning = false;
//				}
			}
			
		});
		
	  	  test.addAAction(new Action(){

				@Override
				public void actionEffect(Actor p, Enemy e, int delta) {
//					if(eFired && aCounter < 1){						
			         if(test.energy >= 0){
			        	 test.energy -= 10;
			        	 currentPlayer.causeDamage(test.strength, toivo.actionB);
			         }
//					eFired = false;
			         test.eAttack = false;
			         test.eWarning = false;
//					think = false;
//					}
					
//			         if(!enemy.actionA){
//			        	eFired = true;
//			         }
//			         
					test.actionA = true;
					test.lingerA = true;
				}

				@Override
				public void lingeringEffect(Actor p, Enemy e, int delta) {					
//					
					if(test.timer > 350){
						test.actionA = false;
						test.lingerA = false;
						test.timer = 0;
					}
				}
	  		  
	  	  });
		
		toivo.addConstantEffect(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
				if(!toivo.lingerB || !toivo.spellA){
					toivo.lingerConstant = true;	
				}
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {
				
				
				if(toivo.lingerConstant && (!toivo.energyCutoff || !toivo.energyCutoff2)){
					if(toivo.constantTimer > 140){
						toivo.addEnergy(7);

			        	toivo.constantTimer = 0;
		        	}
				}
				
				if(toivo.fullTankCheck()){
					toivo.lingerConstant = false;
					toivo.constantTimer = 0;
				}
			}
			
		});
		
		toivo.addSpell(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
						         
		         if(!toivo.energyTankCheck() && !toivo.energyCutoff){
		        	 if(toivo.timer3 > 200){
		        		 if(toivo.spellDamage < 30){
		        			 toivo.spellDamage += 2;
		        		 }
		        		 if(toivo.spellDamage >= 30){
		        			 toivo.spellDamage = 60;
		        		 }
		        		 toivo.timer3 = 0;
		        	 }
		         }
				toivo.spellA = true;
				toivo.lSpellA = true;
				
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {
				
				if(toivo.energyReserves.get(toivo.currentTank).energyLevel >= 10){
					toivo.energyCutoff = false;
				}
				
				if(!toivo.cast && !toivo.energyTankCheck() && !toivo.energyCutoff){
					if(toivo.spendEnergy(50)){
						currentEnemy.causeDamage(toivo.spellDamage);
						toivo.spellDamage = 10;
			        	toivo.timer3 = 0;
						toivo.spellA = false;
						toivo.lSpellA = false;
						toivo.fired = false;
					}
					else{
						 toivo.energyCutoff = true;
					}
				}
			}
		});
		
	  toivo.addAAction(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
				if(!toivo.fired){
		         if(toivo.weapon.clip > 0){
		        	 currentEnemy.causeDamage(toivo.getWeapon().attack);
		        	 currentEnemy.constantTimer = 0;
					toivo.getWeapon().clip -= 1;
		         }
		         toivo.fired = true;
				
				toivo.attack = false;
				
				toivo.actionA = true;
				toivo.lingerA = true;
				}
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {					
//		        			
//				if(fired){
//					enemy.decreaseHP(10);
//			         if(energy >= 0){
//			        	 energy -= 10;
//			         }
//					fired = false;
//				}
//				
//				if(toivo.timer > 40){
//					toivo.actionA = false;
//					toivo.lingerA = false;
//				}
				
				if(toivo.timer > toivo.getWeapon().shotSpeed){
					toivo.actionA = false;
					toivo.lingerA = false;
					toivo.fired = false;
			        toivo.timer = 0;
				}
			}
		  
	  });
	  
	  toivo.addBAction(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
		         
				if(!toivo.fired2){
		        	toivo.fired2 = true;
					toivo.timer2 = 0;
					toivo.lingerB = true;
		         }
		         
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {
				
//					if(fired2 && !toivo.energyCutoff){
//				         if(eT.energyLevel > 0){
//				        	 if(toivo.timer2 > 70){
//				        		 eT.energyLevel -= 10;
//					        	 toivo.timer2 = 0;
//				        	 }
//				         }
//					}
//					
//			         if(eT.energyLevel <= 0){
//			        	 eT.energyLevel = 0;
//			        	 toivo.actionB = false;
//			        	 toivo.energyCutoff = true;
//			         }
			         
						if(toivo.fired2 && !toivo.energyCutoff2){
					         if(!toivo.energyTankCheck()){
					        	 if(toivo.timer2 > 70){
					        		 if(toivo.spendEnergy(10)){
					        			 toivo.timer2 = 0;
					        			 toivo.actionB = true;
					        		 }
					        		 else{
					        			 toivo.energyCutoff2 = true;
					        		 }
					        	 }
					         }
						}
						
				         if(toivo.energyTankCheck()){
				        	 toivo.actionB = false;
				        	 toivo.energyCutoff2 = true;
				         }
				
				if(!toivo.defend){
					toivo.actionB = false; 
					toivo.lingerB = false;
					toivo.fired2 = false;
					toivo.timer2 = 0;
					toivo.energyCutoff2 = false;
				}
			}
		  
	  });
	  
	  toivo.weapon.gunBar.equiped = true;
	  toivo.weapon.gunCyl.equiped = true;
	  toivo.weapon.gunStock.equiped = true;
	  toivo.weapon.staff.equiped = true;
	  toivo.inventory.addItem(toivo.weapon.gunBar);
	  toivo.inventory.addItem(toivo.weapon.gunCyl);
	  toivo.inventory.addItem(toivo.weapon.gunStock);
	  toivo.inventory.addItem(toivo.weapon.staff);
	  toivo.inventory.addItem(cake);
	  
	  currentPlayer = toivo;
	  currentEnemy = test;
	}
	
	public static void equip(GunCylinder gc){
		if(!gc.equals(currentPlayer.weapon.gunCyl)){
			currentPlayer.weapon.gunCyl = gc;
			toivo.weapon.gunCyl.equiped = true;
		}
		else{
			toivo.weapon.gunCyl.equiped = false;
			currentPlayer.weapon.gunCyl = noGC;
		}
	}
	
	public static void equip(Staff s){
		if(!s.equals(currentPlayer.weapon.staff)){
			currentPlayer.weapon.staff = s;
			toivo.weapon.staff.equiped = true;
		}
		else{
			toivo.weapon.staff.equiped = false;
			currentPlayer.weapon.staff = noS;
		}
	}
	
	public static void equip(GunBarrel gb){
		if(!gb.equals(currentPlayer.weapon.gunBar)){
			currentPlayer.weapon.gunBar = gb;
			toivo.weapon.gunBar.equiped = true;
		}
		else{
			toivo.weapon.gunBar.equiped = false;
			currentPlayer.weapon.gunBar = noGB;
		}
	}
	
	public static void equip(GunStock gs){
		if(!gs.equals(currentPlayer.weapon.gunStock)){
			currentPlayer.weapon.gunStock = gs;
			toivo.weapon.gunStock.equiped = true;
		}
		else{
			toivo.weapon.gunStock.equiped = false;
			currentPlayer.weapon.gunStock = noGS;
		}
	}
	
	public static void equip(Consumable c){
		c.consumed = true;
		c.effect1.effect();
		currentPlayer.inventory.inventory.remove(c);
	}
}
