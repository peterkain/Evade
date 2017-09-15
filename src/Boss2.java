package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.util.Random;

public class Boss2 extends GameObject {
	
	private Handler handler;
	private int appearTimer;
	private int moveTimer;
	public static int bossTimeDuration;
	
	int size = 32;
	
	//private Random r = new Random();
	
	public boolean atNull;
	public static boolean fadeOut;
	public boolean begin;
	private HUD hud;

	public Boss2(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.hud = hud;
		this.handler = handler;
		bossTimeDuration = 1000;
		
		fadeOut = false;
		atNull = false;
		begin = false;
		
		appearTimer = 60;
		moveTimer = 60;
		
		vx = 60;
		vy = 60;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		if(!atNull) {
			if(appearTimer <= 0) atNull = true;
			else appearTimer--;
		}
		
		if(atNull) moveTimer--;
		if(moveTimer <= 0) {
			begin = true;
		}
		
		if(atNull && !fadeOut) {
			bossTimeDuration--;
			//System.out.println(bossTimeDuration);
		}
		
		if(bossTimeDuration <= 0) {
			fadeOut = true;
			hud.setLevel(hud.getLevel() + 1);
			handler.removeObject(this);
		}
		
		if(y <= 0 || y >= Game.HEIGHT - 32) vy *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) vx *= -1;
		
		handler.addObject(new TrailFade(x, y, size, size, 1.0f, ID.TrailFade, Color.BLACK, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, size, size);
		
		if(fadeOut) {
			g.setColor(new Color((int)Math.cos(x), (int)Math.cos(y), (int)Math.cos(x + y)));
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, size, size);
	}
	

}
