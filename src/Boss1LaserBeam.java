package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1LaserBeam extends GameObject {
	
	private Handler handler;
	
	private Random r = new Random();

	public Boss1LaserBeam(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		vx = 50;
		vy = r.nextInt(5 + 5) - 5;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		if(x >= Game.WIDTH) handler.removeObject(this);
		
		handler.addObject(new TrailFade(x, y, 32, 32, 0.05f, ID.TrailFade, Color.GREEN, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
