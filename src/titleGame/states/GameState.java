package titleGame.states;

import java.awt.Graphics;

import titleGame.Handler;
import titleGame.worlds.World;

public class GameState extends State{

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world2.txt");
		handler.setWorld(world);
//		handler.getGameCamera().move(100, 200);
	}
	
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getX() >= 1216 && handler.getWorld().getEntityManager().getPlayer().getY() >= 1216-64 ) {
			world = new World(handler, "res/worlds/world1.txt");
			handler.setWorld(world);
		}
		world.tick();
//		game.getGameCamera().move(1, 1);
	}

	public void render(Graphics g) {
		world.render(g);
	}
	
}
