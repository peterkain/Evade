package main;

import java.awt.Color;
import java.awt.Graphics;

import main.Game.GAMESTATE;

public class HUD {
	
	public static float HEALTH = 100;
	int red, green;
	int timer = 0;
	int timeCap = 60; // SHOP: STAGES: 60 - default, 50 - 1, 40 - 2, 30 - 3
	
	private int score = 0;
	private int level = 1;
	
	private Game game;
	
	public HUD(Game game) {
		this.game = game;
	}
	
	public void tick() {
		red = (int)(255 - HEALTH * 2.5);
		green = (int)(0 + HEALTH * 2.5);
		
		if(timer == timeCap) {
			HEALTH++;
			timer = 0;
		}
		HEALTH = Game.clamp((float)HEALTH, -10.0f, 100.0f);
		if(HEALTH <= 0) {
			game.gamestate = GAMESTATE.DEATH;
			System.out.println("DEATH");
		}
		
		timer++;
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(Game.WIDTH / 2 - 115, 30, 200, 30);
		g.setColor(Color.BLACK);
		g.drawRect(Game.WIDTH / 2 - 115, 30, 200, 30);
		
		if(HEALTH < 0) {
			g.setColor(Color.RED);
			g.drawString("GameOver", Game.WIDTH / 2, Game.HEIGHT / 2);
			red = 250;
			green = 0;
		}
		
		if(HEALTH != 0) {
			g.setColor(new Color(red, green, 0));
			g.fillRect(Game.WIDTH / 2 - 115, 30, (int)HEALTH * 2, 30);
		}
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("Score: " + score, 10, 48);
		g.drawString("Level: " + level, 10, 64);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
