package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.GAMESTATE;

public class Deathscreen extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	
	public Deathscreen(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font font = new Font("arial", 1, 32);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		
		if(Menu.mouseOver(Menu.mx, Menu.my, Game.WIDTH / 2 - 100, 500, 200, 64)) {
			g.setColor(Color.GREEN);
			g.fillRect(Game.WIDTH / 2 - 100, 500, 200, 64);
		} if(Menu.mouseOver(Menu.mx, Menu.my, Game.WIDTH / 2 - 100, 600, 200, 64)) {
			g.setColor(Color.GREEN);
			g.fillRect(Game.WIDTH / 2 - 100, 600, 200, 64);
		}
		
		g.setColor(Color.BLACK);
		
		g.drawRect(Game.WIDTH / 2 - 100, 500, 200, 64);
		g.drawRect(Game.WIDTH / 2 - 100, 600, 200, 64);
		
		g.drawString("New Game", Game.WIDTH / 2 - fm.stringWidth("New Game") / 2 - 1, 545);
		g.drawString("Menu", Game.WIDTH / 2 - fm.stringWidth("Menu") / 2 - 1, 645);
	}
	
	public void mousePressed(MouseEvent e) {
		if(game.gamestate == GAMESTATE.DEATH) {
			handler.removeEverything();
			System.out.println("DEATH2");
			if(Menu.mouseOver(Menu.mx, Menu.my, Game.WIDTH / 2 - 100, 600, 200, 64)) {
				game.gamestate = GAMESTATE.Game;
			} if(Menu.mouseOver(Menu.mx, Menu.my, Game.WIDTH / 2 - 100, 600, 200, 64)) {
				game.gamestate = GAMESTATE.Menu;
			}
		}
	}
}
