package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.GAMESTATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	public static int mx, my;
	
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void tick() {

	}
	
	public void render(Graphics g) {
		Font title = new Font("arial", 1, 64);
		if(game.gamestate == GAMESTATE.Menu) {
			Font options = new Font("arial", 1, 32);
			g.setFont(title);
			FontMetrics fm = g.getFontMetrics();
	
			g.setColor(Color.BLACK);
			
			g.drawString("EVADE", Game.WIDTH / 2 - fm.stringWidth("EVADE") / 2 - 1, 100);
			g.setFont(options);
			
			g.drawRect(Game.WIDTH / 2 - 100, 250, 200, 64);
			g.drawRect(Game.WIDTH / 2 - 100, 350, 200, 64);
			g.drawRect(Game.WIDTH / 2 - 100, 450, 200, 64);
		
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) {
				g.setColor(Color.GREEN);
				g.fillRect(Game.WIDTH / 2 - 100, 250, 200, 64);
			} if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 64)) {
				g.setColor(Color.GREEN);
				g.fillRect(Game.WIDTH / 2 - 100, 350, 200, 64);
			} if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 450, 200, 64)) {
				g.setColor(Color.GREEN);
				g.fillRect(Game.WIDTH / 2 - 100, 450, 200, 64);
			}
			
			g.setColor(Color.BLACK);
			g.drawString("New Game", 430, 295);
			g.drawString("Help", 480, 395);
			g.drawString("Exit", 480, 495);

		} else if(game.gamestate == GAMESTATE.Help) {
			Font text = new Font("arial", 1, 32);
			g.setFont(title);
			FontMetrics fm = g.getFontMetrics();
			
			g.setColor(Color.BLACK);
			
			g.drawString("HELP", Game.WIDTH / 2 - fm.stringWidth("HELP") / 2 - 1, 100);
			g.setFont(text);
			fm = g.getFontMetrics();
			g.drawString("Use WASD to move and dodge enemy attacks.", fm.stringWidth("Use WASD to move and dodge enemy attacks.") / 4, 300);
			g.drawString("Try to survive as long as possible!", fm.stringWidth("Try to survive as long as possible!") / 2, 350);
			
			g.drawRect(Game.WIDTH / 2 - 100, 550, 200, 64);
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 550, 200, 64)) {
				g.setColor(Color.GREEN);
				g.fillRect(Game.WIDTH / 2 - 100, 550, 200, 64);
			}
			
			g.setColor(Color.BLACK);
			g.drawString("Back", Game.WIDTH / 2 - fm.stringWidth("Back") / 2 - 1, Game.HEIGHT - 175);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if(game.gamestate == GAMESTATE.Menu) {
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) {
				//System.out.println("Mouse over");
				game.gamestate = GAMESTATE.Game;
				handler.removeEnemies();
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
			} else if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 64)) {
				game.gamestate = GAMESTATE.Help;
			} else if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 450, 200, 64)) {
				System.exit(0);
			} 
		} if(game.gamestate == GAMESTATE.Help) {
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, 550, 200, 64)) {
				game.gamestate = GAMESTATE.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}
	
	public static boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
		} return false;
	}
}
