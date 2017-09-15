package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TrailFade extends GameObject {

	private float alpha = 1.0f;
	private float life;
	
	private Handler handler;
	private Color color;
	
	private int width, height;
	
	public TrailFade(float x, float y, int width, int height, float life, ID id, Color color, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

	@Override
	public void tick() {
		if(alpha > life) alpha -= (life - 0.001f);
		else handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(makeTransparent(alpha));
		
		g2.setColor(color);
		g2.fillRect((int)x, (int)y, width, height);
		
		g2.setComposite(makeTransparent(1));
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
