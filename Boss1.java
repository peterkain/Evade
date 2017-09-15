package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1 extends GameObject {
	
	private Handler handler;
	private int appearTimer;
	private int moveTimer;
	public static int bossTimeDuration;
	
	int size = 96;
	
	private Random r = new Random();
	
	boolean atNull;
	static boolean fadeOut;
	private HUD hud;

	public Boss1(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.hud = hud;
		this.handler = handler;
		bossTimeDuration = 1050;
		
		fadeOut = false;
		atNull = false;
		
		appearTimer = 60;
		moveTimer = 60;
		
		vx = 2;
		vy = 0;
	}

	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		if(!atNull) {
			if(appearTimer <= 0) { vx = 0; atNull = true; }
			else appearTimer--;
		}
		
		if(atNull) moveTimer--;
		if(moveTimer <= 0) {
			if(vy == 0) vy = 2;
		}
		
		if(atNull && !fadeOut) {
			int addBullet = r.nextInt(5);
			if(addBullet == 0) {
				handler.addObject(new Boss1Bullet((int)x + size / 2, (int)y + size / 2, ID.Boss1Bullet, handler));
			} 
			
			int addLaserBeam = r.nextInt(80);
			if(addLaserBeam == 0) {
				handler.addObject(new Boss1LaserBeam((int)x + size / 2, (int)y + size / 2, ID.Boss1LaserBeam, handler));
			}
			
			bossTimeDuration--;
			//System.out.println(bossTimeDuration);
		}
		
		if(bossTimeDuration <= 0) {
			fadeOut = true;
			vy = 0;
			vx = -2;
			
			if(x < -size) { hud.setLevel(hud.getLevel() + 1); handler.removeObject(this); }
		}
		
		if(y <= 0 || y >= Game.HEIGHT - 122) vy *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) vx *= -1;
		
		handler.addObject(new TrailFade(x, y, size, size, 1.0f, ID.TrailFade, Color.RED, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, size, size);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, size, size);
	}
	

}
