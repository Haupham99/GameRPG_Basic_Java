package titleGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import titleGame.Display.Display;
import titleGame.gfx.Assets;
import titleGame.gfx.GameCamera;
import titleGame.gfx.ImageLoader;
import titleGame.gfx.SpriteSheet;
import titleGame.input.KeyManager;
import titleGame.input.MouseManager;
import titleGame.states.*;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//State
	public State gameState;
	public State menuState;
//	private BufferedImage testImage;
//	private SpriteSheet image;
//	private BufferedImage crop;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
//		testImage = ImageLoader.loadImage("/textures/sheet.png");
//		image = new SpriteSheet(testImage);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);

		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}

	int x = 0;
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screnn
		g.clearRect(0, 0, width, height);
		//Draw here
//		g.setColor(Color.red);
//		g.fillRect(50, 50, 50, 50);
//		g.setColor(Color.blue);
//		g.drawRect(0, 0, 20, 20);
		
//		g.drawImage(image.crop(32, 0, 32, 32), 0, 0, null);
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Draw		
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				delta--;
				ticks++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS : " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	} 

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
