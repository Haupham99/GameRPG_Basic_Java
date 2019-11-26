package titleGame.entities.creatures;

import titleGame.Game;
import titleGame.Handler;
import titleGame.entities.Entity;
import titleGame.tiles.Tile;

public abstract class Creature extends Entity{

	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			xMove();
		if(!checkEntityCollisions(0f, yMove))
			yMove();
	}
	
	public void xMove() {
		if(xMove > 0) {
			int tx = (int)(x + xMove + bounds.width)/Tile.TILE_WIDTH;			
			if(!collisionWithTile(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT)) {
				x += xMove;
			}	else {
				x = tx *Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		}else {
			int tx = (int)(x + xMove)/Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int)y/Tile.TILE_WIDTH) &&
					!collisionWithTile(tx, (int)(y+bounds.height)/Tile.TILE_HEIGHT)) {
				x += xMove;
			}else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH -bounds.x;
			}
		}
	}
	
	public void yMove() {
		if(yMove > 0 ) {
			int ty = (int)(y + yMove + bounds.height)/Tile.TILE_HEIGHT;
			if(!collisionWithTile((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) &&
					!collisionWithTile((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILE_HEIGHT - bounds.height - bounds.y - 1; 
			}
		}else {
			int ty = (int)(y + yMove)/Tile.TILE_HEIGHT;
			if(!collisionWithTile((int)x/Tile.TILE_HEIGHT, ty) &&
					!collisionWithTile((int)(x+bounds.width)/Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}	
	
	//GETTERS AND SETTERS

	public float getxMove() {
		return xMove;
	}



	public void setxMove(float xMove) {
		this.xMove = xMove;
	}



	public float getyMove() {
		return yMove;
	}



	public void setyMove(float yMove) {
		this.yMove = yMove;
	}



	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	
}
