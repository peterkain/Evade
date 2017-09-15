package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import main.Game.GAMESTATE;

public class KeyInput extends KeyAdapter {
	
	public static int VELOCITY = 8;
	private Handler handler;
	private Game game;
	private HUD hud;
	private Random r = new Random();
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler, Game game, HUD hud) {
		this.handler = handler;
		this.game = game;
		this.hud = hud;
		
		keyDown[0] = false;		//w
		keyDown[1] = false;		//s
		keyDown[2] = false;		//d
		keyDown[3] = false;		//a
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if(temp.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) { temp.setVy(-VELOCITY); keyDown[0] 	= 	true; }
				if(key == KeyEvent.VK_S) { temp.setVy(VELOCITY);  keyDown[1] 	=  	true; }
				if(key == KeyEvent.VK_D) { temp.setVx(VELOCITY);  keyDown[2] 	= 	true; }
				if(key == KeyEvent.VK_A) { temp.setVx(-VELOCITY); keyDown[3] 	=  	true; }
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			if(game.gamestate == GAMESTATE.Menu) {
				System.exit(0);
			}
			handler.removeEverything();
			//System.out.println("Removed player");
			hud.setLevel(1);
			hud.setScore(0);
			game.gamestate = GAMESTATE.Menu;
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH) - 32, r.nextInt(Game.HEIGHT) - 32, ID.MenuParticle, handler));
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject temp = handler.objects.get(i);
			if(temp.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) keyDown[0] = false; //object.setVy(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false; //object.setVy(0);
				if(key == KeyEvent.VK_D) keyDown[2] = false; //object.setVx(0);
				if(key == KeyEvent.VK_A) keyDown[3] = false; //object.setVx(0);
				
				if(!keyDown[0] && !keyDown[1]) temp.setVy(0);
				if(!keyDown[2] && !keyDown[3]) temp.setVx(0);
			}
		}
	}
	
	
}
