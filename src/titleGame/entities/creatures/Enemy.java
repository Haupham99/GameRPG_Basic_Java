package titleGame.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import titleGame.Handler;
import titleGame.gfx.Animation;
import titleGame.gfx.Assets;
import titleGame.tiles.Tile;

public class Enemy extends Creature {
	
	private Animation animationDown;
	private Animation animationUp;
	private Animation animationLeft;
	private Animation animationRight;
	
	private long lastTime, timer;
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		speed = 4.0f;
		
		timer = 0;
		lastTime = System.currentTimeMillis();
		
		bounds.x = 15;
		bounds.y = 10;
		bounds.width = 30;
		bounds.height = 30;
		
		animationDown = new Animation(200, Assets.enemy_down);
		animationUp = new Animation(200, Assets.enemy_up);
		animationLeft = new Animation(200, Assets.enemy_left);
		animationRight = new Animation(200, Assets.enemy_right);
	}
	
	public void moveX() {
		if(timer < 2000) {
			xMove = speed;
			int tx = (int)(x + xMove + bounds.width)/Tile.TILE_WIDTH;			
			if(!collisionWithTile(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT)) {
				x += xMove;
				timer += System.currentTimeMillis() - lastTime;
			}	else {
				x = tx *Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
				timer = 2000;
			}
		}else if(timer>=2000 && timer <= 4000) {
			xMove = -speed;
			int tx = (int)(x + xMove)/Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int)y/Tile.TILE_WIDTH) &&
					!collisionWithTile(tx, (int)(y+bounds.height)/Tile.TILE_HEIGHT)) {
				x += xMove;
				timer += System.currentTimeMillis() - lastTime;
			}else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH ;
				timer = 0;
			}
		}else {
			timer = 0;
		}
		lastTime = System.currentTimeMillis();
	}
	
	public void moveY() {
		x -=2;
	}
	
	@Override
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
	}
	
	public void tick() {
		move();
		animationDown.tick();
		animationUp.tick();
		animationLeft.tick();
		animationRight.tick();
	}

	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animationLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animationRight.getCurrentFrame();
		}else if(yMove < 0) {
			return animationUp.getCurrentFrame();
		}else {
			return animationDown.getCurrentFrame();
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		
	}

}
