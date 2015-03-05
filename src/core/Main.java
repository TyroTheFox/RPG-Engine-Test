package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

	public static final String gamename = "The Path Less Travelled Alpha V0.3";
	public static final int play = 0;
	public static final int combat = 1;
	
	public Main(String name) {
		super(name);
		addState(new Play(play));
		addState(new Combat(combat));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		getState(play).init(gc, this);
		getState(combat).init(gc, this);
		
		enterState(combat);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		
		try{
			appgc = new AppGameContainer(new Main(gamename));
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
			
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}
