package main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	private short beginTimer = 100;
	private short enemies;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		if(beginTimer <= 0) {
			if(!(hud.getLevel() % 10 == 0)) {
				if(scoreKeep++ == 100) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() + 1);
					//System.out.println("Increasing level");
					processLevel(hud.getLevel());
				}
			}
		} else beginTimer--;
	}
	
	public void processLevel(int level) {
		if(level % 10 == 0) {
			int boss = r.nextInt(2);
			if(boss == 0) {
				handler.removeEnemies();
				handler.addObject(new Wall1(Game.WIDTH - 10, 0, ID.Wall, handler));
				handler.addObject(new Boss1(-96, Game.HEIGHT / 2 - 64, ID.Boss1, handler, hud));
			} else if(boss == 1) {
				handler.removeEnemies();
				handler.addObject(new Boss2(-4, Game.HEIGHT / 2 - 4, ID.Boss2, handler, hud));
			}
			
		} else {
			enemies = (short)r.nextInt(10);
			switch(enemies) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));
				break;
			case 6:
			case 7:
				handler.addObject(new HorizontalEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.HorizontalEnemy, handler));
				break;
			case 8:
			case 9:
				handler.addObject(new VerticalEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.VerticalEnemy, handler));
				break;
			case 10:
				handler.addObject(new Pursuer(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.Pursuer, handler));
			}
		}
	}

}
