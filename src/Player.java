package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		handler.addObject(new TrailFade(x, y, 32, 32, 0.2f, ID.Player, Color.BLACK, handler));
		
		collide();
	}
	
	private void collide() {
		for(GameObject object : handler.objects) {
			if(object.getId() == ID.BasicEnemy || object.getId() == ID.HorizontalEnemy || object.getId() == ID.VerticalEnemy ||
			   object.getId() == ID.Pursuer) {
				if(getBounds().intersects(object.getBounds())) {
					HUD.HEALTH -= 2;
				}
			} if(object.getId() == ID.Boss1) {
				if(getBounds().intersects(object.getBounds())) {
					HUD.HEALTH -= 9999;
				}
			} if(object.getId() == ID.Boss1Bullet) {
				if(getBounds().intersects(object.getBounds())) {
					HUD.HEALTH -= 5;
				}
			} if(object.getId() == ID.Boss1LaserBeam) {
				if(getBounds().intersects(object.getBounds())) {
					HUD.HEALTH -= 30;
				}
			} if(object.getId() == ID.Wall) {
				x = Game.clamp(x, 0, Game.WIDTH - Game.WIDTH / 5 + 1);
			} if(object.getId() == ID.Boss2) {
				if(getBounds().intersects(object.getBounds())) {
					HUD.HEALTH -= 40;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
