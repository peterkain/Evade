package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall1 extends GameObject {
	
	private Handler handler;
	
	private int appearTimer = 81;

	public Wall1(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		vx = -2;
	}

	@Override
	public void tick() {
		x += vx;
		
		appearTimer--;
		if(appearTimer <= 0) vx = 0;
		
		if(Boss1.bossTimeDuration <= 0) {
			vx = 2;
			if(x > Game.WIDTH) handler.removeObject(this);
		}
	
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect((int)x, (int)y, Game.WIDTH / 5, Game.HEIGHT);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, Game.WIDTH / 5, Game.HEIGHT);
	}

}
