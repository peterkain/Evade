package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1Bullet extends GameObject {
	
	private Handler handler;
	private Random r = new Random();

	public Boss1Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		vx = 20;
		vy = r.nextInt(5 + 8) - 8;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		//if(y <= 0 || y >= Game.HEIGHT - 40) vy *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) vx *= -1;
		
		if(x >= Game.WIDTH) handler.removeObject(this);
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new TrailFade(x, y, 8, 8, 0.095f, ID.TrailFade, new Color(128, 0, 128), handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(128, 0, 128));
		g.fillRect((int)x, (int)y, 8, 8);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

}
