package titleGame.states;

import java.awt.Graphics;

import titleGame.Handler;
import titleGame.worlds.World;

public class GameState extends State{

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
//		handler.getGameCamera().move(100, 200);
	}
	
	public void tick() {
		world.tick();
//		game.getGameCamera().move(1, 1);
	}

	public void render(Graphics g) {
		world.render(g);
	}
	
}
