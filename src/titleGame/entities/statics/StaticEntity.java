package titleGame.entities.statics;

import java.awt.Graphics;

import titleGame.Handler;
import titleGame.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	public boolean isEnemy() {
		return true;
	}
	
}
