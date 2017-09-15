package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	private Handler handler;
	
	private Color c;
	private Random r = new Random();
	private int dir;

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		dir = r.nextInt(4);
		
		if(dir == 3) vx = -r.nextInt(8) - 2;
		if(dir == 2) vx = -r.nextInt(8) - 2;
		if(dir == 1) vx = r.nextInt(8) + 2;
		if(dir == 0) vy = r.nextInt(8) + 2;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		if(y <= 0 || y >= Game.HEIGHT - 40) { 
			handler.removeObject(this);
			handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH) - 32, r.nextInt(Game.HEIGHT) - 32, ID.MenuParticle, handler));
		}
		if(x <= 0 || x >= Game.WIDTH - 16) { 
			handler.removeObject(this);
			handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH) - 32, r.nextInt(Game.HEIGHT) - 32, ID.MenuParticle, handler));
		}
		
		handler.addObject(new TrailFade(x, y, 16, 16, 0.07f, ID.TrailFade, c, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}