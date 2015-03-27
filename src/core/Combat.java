package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Combat extends BasicGameState{

	Rectangle playerPlaceholder, enemyPlaceholder, pAttackPlaceholder, eAttackPlaceholder;
	int pHP, eHP, energy, maxEnergy;
	int eEnergy, eMaxEnergy;
	int spellDamage;
	boolean attack, defend, cast;
	boolean eAttack, eDefend, think = false, eWarning = false;
	
	EnergyTank eT;
	
	Weapon testShotgun;
	
	Weapon weapon;
	
	int aCounter = 0;
	
	Actor test;
	Enemy enemy;
	
	boolean fired, fired2, eFired;
	
	public Combat(int state){}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		playerPlaceholder = new Rectangle(100, 200, 100, 600);
		enemyPlaceholder = new Rectangle(500, 100, 50, 300);
		pAttackPlaceholder = new Rectangle(270, 220, 50, 50);
		eAttackPlaceholder = new Rectangle(enemyPlaceholder.getCenterX() - 120, 220, 50, 50);
		
		energy = 500;
		maxEnergy = 500;
		
		eEnergy = 500;
		eMaxEnergy = 500;
		
		attack = false;
		defend = false;
		cast = false;
		
		eAttack = false;
		eDefend = false;
		
		test = new Actor();
		enemy = new Enemy();
		
//		eT = new EnergyTank(30, 125);
		
		enemy.addBrain(new Brain(){

			@Override
			public void stateCheck() {
				enemy.constantTimer = 0;
				think = false;
//				if(!defend){
					eAttack = true;
//				}
//				else{
//					eAttack = false;
			    	eWarning = false;
//				}
			}
			
		});
		
	  	  enemy.addAAction(new Action(){

				@Override
				public void actionEffect(Actor p, Enemy e, int delta) {
//					if(eFired && aCounter < 1){						
			         if(enemy.energy >= 0){
			        	 enemy.energy -= 10;
			        	 test.causeDamage(enemy.strength, test.actionB);
						aCounter += 1;
			         }
//					eFired = false;
					eAttack = false;
			    	eWarning = false;
//					think = false;
//					}
					
//			         if(!enemy.actionA){
//			        	eFired = true;
//			         }
//			         
					enemy.actionA = true;
					enemy.lingerA = true;
				}

				@Override
				public void lingeringEffect(Actor p, Enemy e, int delta) {					
			        			
//					if(eFired && aCounter < 1){						
//				         if(eEnergy >= 0){
//				        	eEnergy -= 10;
//							test.decreaseHP(3);
//							aCounter += 1;
//				         }
//						eFired = false;
//						eAttack = false;
//						think = false;
//					}
//					
					if(enemy.timer > 350){
						enemy.actionA = false;
						enemy.lingerA = false;
				        enemy.timer = 0;
					}
				}
	  		  
	  	  });
	  	  
	  	  testShotgun = new Weapon("Shotgun", 20, 3000, 2);
	  	  
	  	  test.addWeapon(testShotgun);
		
		test.addConstantEffect(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
				if(!test.lingerB || !test.spellA){
					test.lingerConstant = true;	
				}
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {
				
//				if(test.lingerConstant){
//					if(test.constantTimer > 140){
//						if(test.energy < test.maxEnergy){
//							test.energy += 7;
//						}
//						if(test.energy >= test.maxEnergy){
//							test.energy = test.maxEnergy;
//						}
//			        	test.constantTimer = 0;
//		        	}
//				}
//				
//				if(test.energy > test.maxEnergy){
//					test.lingerConstant = false;
//					test.constantTimer = 0;
//				}
				
//				if(test.lingerConstant){
//					if(test.constantTimer > 140){
//						if(eT.energyLevel < eT.maxEnergyLevel){
//							eT.energyLevel += 7;
//						}
//						if(eT.energyLevel >= eT.maxEnergyLevel){
//							eT.energyLevel = eT.maxEnergyLevel;
//						}
//			        	test.constantTimer = 0;
//		        	}
//				}
//				
//				if(eT.energyLevel > eT.maxEnergyLevel){
//					test.lingerConstant = false;
//					test.constantTimer = 0;
//				}
				
				
				if(test.lingerConstant && (!test.energyCutoff || !test.energyCutoff2)){
					if(test.constantTimer > 140){
						test.addEnergy(7);

			        	test.constantTimer = 0;
		        	}
				}
				
				if(test.fullTankCheck()){
					test.lingerConstant = false;
					test.constantTimer = 0;
				}
			}
			
		});
		
		test.addSpell(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
				
//		         if(test.energy >= 0){
//		        	 if(test.timer3 > 200){
//		        		 if(spellDamage < 30){
//		        			 spellDamage += 2;
//		        		 }
//		        		 if(spellDamage >= 30){
//		        			 spellDamage = 60;
//		        		 }
//		        		 test.timer3 = 0;
//		        	 }
//		         }
		         
//		         if(eT.energyLevel >= 0){
//		        	 if(test.timer3 > 200){
//		        		 if(spellDamage < 30){
//		        			 spellDamage += 2;
//		        		 }
//		        		 if(spellDamage >= 30){
//		        			 spellDamage = 60;
//		        		 }
//		        		 test.timer3 = 0;
//		        	 }
//		         }
		         
		         if(!test.energyTankCheck() && !test.energyCutoff){
		        	 if(test.timer3 > 200){
		        		 if(spellDamage < 30){
		        			 spellDamage += 2;
		        		 }
		        		 if(spellDamage >= 30){
		        			 spellDamage = 60;
		        		 }
		        		 test.timer3 = 0;
		        	 }
		         }
				test.spellA = true;
				test.lSpellA = true;
				
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {
			
//				if(!cast){
//					enemy.causeDamage(spellDamage);
//					spellDamage = 10;
//					test.energy -= 10;
//		        	test.timer3 = 0;
//					test.spellA = false;
//					test.lSpellA = false;
//					fired = false;
//				}
				
				if(test.energyReserves.get(test.currentTank).energyLevel >= 10){
					test.energyCutoff = false;
				}
				
				if(!cast && !test.energyTankCheck() && !test.energyCutoff){
					if(test.spendEnergy(50)){
						enemy.causeDamage(spellDamage);
						spellDamage = 10;
			        	test.timer3 = 0;
						test.spellA = false;
						test.lSpellA = false;
						fired = false;
					}
					else{
						 test.energyCutoff = true;
					}
				}
			}
		});
		
  	  test.addAAction(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
//		         if(!test.actionA){
//		        	 fired = true;
//		        	 test.timer = 0;
//		         }
//		         
//				test.actionA = true;
//				test.lingerA = true;
				if(!fired){
		         if(test.weapon.clip > 0){
		        	 enemy.causeDamage(test.getWeapon().attack);
		        	 enemy.constantTimer = 0;
					test.getWeapon().clip -= 1;
		         }
		         fired = true;
				
				attack = false;
				
				test.actionA = true;
				test.lingerA = true;
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
//				if(test.timer > 40){
//					test.actionA = false;
//					test.lingerA = false;
//				}
				
				if(test.timer > test.getWeapon().shotSpeed){
					test.actionA = false;
					test.lingerA = false;
					fired = false;
			        test.timer = 0;
				}
			}
  		  
  	  });
  	  
  	  test.addBAction(new Action(){

			@Override
			public void actionEffect(Actor p, Enemy e, int delta) {
		         
				if(!fired2){
		        	fired2 = true;
					test.timer2 = 0;
					test.lingerB = true;
		         }
		         
			}

			@Override
			public void lingeringEffect(Actor p, Enemy e, int delta) {
				
//					if(fired2 && !test.energyCutoff){
//				         if(eT.energyLevel > 0){
//				        	 if(test.timer2 > 70){
//				        		 eT.energyLevel -= 10;
//					        	 test.timer2 = 0;
//				        	 }
//				         }
//					}
//					
//			         if(eT.energyLevel <= 0){
//			        	 eT.energyLevel = 0;
//			        	 test.actionB = false;
//			        	 test.energyCutoff = true;
//			         }
			         
						if(fired2 && !test.energyCutoff2){
					         if(!test.energyTankCheck()){
					        	 if(test.timer2 > 70){
					        		 if(test.spendEnergy(10)){
					        			 test.timer2 = 0;
					        			 test.actionB = true;
					        		 }
					        		 else{
					        			 test.energyCutoff2 = true;
					        		 }
					        	 }
					         }
						}
						
				         if(test.energyTankCheck()){
				        	 test.actionB = false;
				        	 test.energyCutoff2 = true;
				         }
				
				if(!defend){
					test.actionB = false; 
					test.lingerB = false;
					fired2 = false;
					test.timer2 = 0;
					test.energyCutoff2 = false;
				}
			}
  		  
  	  });
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.fill(playerPlaceholder);	
		g.draw(enemyPlaceholder);
		if(test.actionA){
			g.draw(pAttackPlaceholder);
		}
		if(test.spellA){
			g.setColor(Color.red);
			g.fill(pAttackPlaceholder);
			g.setColor(Color.white);;
		}
		
		if(eWarning){
			g.draw(eAttackPlaceholder);
		}
		if(enemy.actionA){
			g.fill(eAttackPlaceholder);
		}
		
		g.drawString("HP: " + test.getHP(), 285, 550);
		test.renderEnergyTanks(g);
//		g.drawString("Energy: " + test.energy + "/" + test.maxEnergy, 250, 570);
		g.drawString("Timer: " + test.timer, 30, 30);
		g.drawString("Timer 2: " + test.timer2, 30, 45);
		g.drawString("Timer 3: " + test.timer3, 30, 60);
		g.drawString("Spell Damage: " + spellDamage, 30, 75);
		g.drawString("Constant Timer: " + test.constantTimer, 30, 90);
		g.drawString("Clip: " + test.weapon.clip + "/" + test.weapon.clipSize, 30, 105);
		
		
		g.drawString("HP: " + enemy.getHP(), enemyPlaceholder.getCenterX() - 30, 50);
		g.drawString("Energy: " + enemy.energy + "/" + enemy.maxEnergy, enemyPlaceholder.getCenterX() - 30, 65);
		g.drawString("Timer: " + enemy.timer, enemyPlaceholder.getCenterX() - 30, 80);
		g.drawString("Attack: " + eAttack, enemyPlaceholder.getCenterX() + 30, enemyPlaceholder.getCenterY() - 30);
		g.drawString("Think: " + think, enemyPlaceholder.getCenterX() + 30, enemyPlaceholder.getCenterY() - 45);
		g.drawString("Constant Timer: " + enemy.constantTimer, enemyPlaceholder.getCenterX() + 30, enemyPlaceholder.getCenterY() - 60);
		
//		g.drawString("Constant Timer: " + enemy.constantTimer, enemyPlaceholder.getCenterX() - 30, 125);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		test.updateEnergyTanks();
  	  
		//Controls
	      //Moves Left - Left Key
	      if(input.isKeyDown(Input.KEY_Z))
	      {
	         cast = true;
	      }
	      else{
	    	 cast = false;
	      }
	      if(input.isKeyPressed(Input.KEY_X))
	      {
	         attack = true;
	      }
	      else{
	    	  attack = false;
	      }
	      //Moves Right - Right Key
	      if(input.isKeyDown(Input.KEY_C))
	      {
	         defend = true;
	      }
	      else{
	    	  defend = false;
	      }	      
	      
	      //Toggles each movement based on button presses
	      //Left
	      if(attack)
	      {
	    	  test.doAAction(test, enemy, delta);
	      }
	      //Right
	      if(defend)
	      {
	    	  test.doBAction(test, enemy, delta);
	      }
	      if(cast){
	    	  test.doSpell(test, enemy, delta);
	      }
	      
	      test.doConstantEffect(test, enemy, delta);
	      if(enemy.constantTimer > 3000){
	    	  think = true;
	    	  aCounter = 0;
	      }
	      
	      if(enemy.constantTimer > 2000){
	    	  eWarning = true;
	      }
	      
	      if(think){
	    	  enemy.doBrain();
	      }
	      
	      if(eAttack)
	      {
	    	  enemy.doAAction(test, enemy, delta);
	      }
	      //Right
	      if(eDefend)
	      {
	    	  enemy.doBAction(test, enemy, delta);
	      }
	      
	    	  test.constantTimer += delta;
	    	  enemy.constantTimer += delta;
	    	  test.constantLingeringActionsCheck(test, enemy, delta);
	      
			//Updates the timer for lingering effects
			if(test.lingerA || test.lingerB || test.spellA){
				if(test.lingerA){
					test.timer += delta;
				}
				if(test.lingerB){
					test.timer2 += delta;
				}
				if(test.spellA){
					test.timer3 += delta;
				}
				test.lingeringActionsCheck(test, enemy, delta);
			}
			
			if(enemy.lingerA || enemy.lingerB){
				enemy.timer += delta;
				enemy.lingeringActionsCheck(test, enemy, delta);
			}
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
