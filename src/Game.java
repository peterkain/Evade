package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 3668702754141963574L;
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final String TITLE = "Evade";
	
	private Thread thread;
	private Random r = new Random();
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Deathscreen deathscreen;
	
	public enum GAMESTATE {
		Menu,
		Help,
		Game,
		DEATH
	};
	
	public GAMESTATE gamestate = GAMESTATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD(this);
		deathscreen = new Deathscreen(this, handler);
		spawner = new Spawn(handler, hud);
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler, this, hud));
		this.addMouseListener(menu);
		this.addMouseListener(deathscreen);
		this.addMouseMotionListener(menu);
		
		new Window(WIDTH, HEIGHT, TITLE, this);
		
		if(gamestate == GAMESTATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
		} else if(gamestate == GAMESTATE.Menu || gamestate == GAMESTATE.Help){
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH) - 32, r.nextInt(HEIGHT) - 32, ID.MenuParticle, handler));
			}
		}
	}
	
	private void tick() {
		handler.tick();
		if(gamestate == GAMESTATE.Game) {
			hud.tick();
			spawner.tick();
		} else if(gamestate == GAMESTATE.Menu) {
			menu.tick();
		} else if(gamestate == GAMESTATE.DEATH) {
			deathscreen.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gamestate == GAMESTATE.Game) {
			hud.render(g);
		} else if(gamestate == GAMESTATE.Menu || gamestate == GAMESTATE.Help) {
			menu.render(g);
		} else if(gamestate == GAMESTATE.DEATH) {
			deathscreen.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double targetFps = 60.0;
		double nanoseconds = 1000000000 / targetFps;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoseconds;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			} if(running) {
				render();
			} 
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) var = max;
		else if(var <= min) var = min;
		return var;
	}
	
	
	public static void main(String[] args) {
		new Game();
	}
}
