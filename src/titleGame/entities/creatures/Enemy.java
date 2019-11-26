package titleGame.entities.creatures;

import java.awt.Graphics;

import titleGame.Handler;
import titleGame.gfx.Assets;

public class Enemy extends Creature {
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 20;
		bounds.y = 15;
		bounds.width = 30;
		bounds.height = 30;
	}
	
	public void moveX() {
		x +=2;
	}
	
	public void moveY() {
		x -=2;
	}
	
	@Override
	
	public void move() {

	}
	
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.enemy, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		
	}

}
