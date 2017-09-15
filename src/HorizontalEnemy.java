package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HorizontalEnemy extends GameObject {
	
	private Handler handler;

	public HorizontalEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		vx = 25;
		vy = 2;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		if(y <= 0 || y >= Game.HEIGHT - 40) vy *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) vx *= -1;
		
		handler.addObject(new TrailFade(x, y, 16, 16, 0.07f, ID.TrailFade, Color.BLUE, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}