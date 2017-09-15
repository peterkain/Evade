package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pursuer extends GameObject {
	
	private Handler handler;
	private GameObject player;

	public Pursuer(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		player = handler.objects.get(0);

	}

	@Override
	public void tick() {
		
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		float distance = (float) Math.hypot(x - player.getX(), y - player.getY());
		
		vx = ((-1.0f / distance) * diffX);
		vy = ((-1.0f / distance) * diffY);
		
		x += vx;
		y += vy;
		
		if(y <= 0 || y >= Game.HEIGHT - 40) vy *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) vx *= -1;
		
		handler.addObject(new TrailFade(x, y, 16, 16, 0.07f, ID.TrailFade, new Color(64, 64, 64), handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(128, 128, 128));
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}