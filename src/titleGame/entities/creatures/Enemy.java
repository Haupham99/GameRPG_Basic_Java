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
	private double maxFollowDistance = 300.0d;
	
	private long lastTime, timer;
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		speed = 2.0f;
		
		timer = 0;
		lastTime = System.currentTimeMillis();
		
		bounds.x = 15;
		bounds.y = 10;
		bounds.width = 45;
		bounds.height = 40;
		
		animationDown = new Animation(200, Assets.enemy_down);
		animationUp = new Animation(200, Assets.enemy_up);
		animationLeft = new Animation(200, Assets.enemy_left);
		animationRight = new Animation(200, Assets.enemy_right);
	}
	
	public void moveX() {
//		if(timer < 2000) {
//			xMove = speed;
//			int tx = (int)(x + xMove + bounds.width)/Tile.TILE_WIDTH;			
//			if(!collisionWithTile(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
//					!collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT)) {
//				x += xMove;
//				timer += System.currentTimeMillis() - lastTime;
//			}	else {
//				x = tx *Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
//				timer = 2000;
//			}
//		}else if(timer>=2000 && timer <= 4000) {
//			xMove = -speed;
//			int tx = (int)(x + xMove)/Tile.TILE_WIDTH;
//			if(!collisionWithTile(tx, (int)y/Tile.TILE_WIDTH) &&
//					!collisionWithTile(tx, (int)(y+bounds.height)/Tile.TILE_HEIGHT)) {
//				x += xMove;
//				timer += System.currentTimeMillis() - lastTime;
//			}else {
//				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH ;
//				timer = 0;
//			}
//		}else {
//			timer = 0;
//		}
//		lastTime = System.currentTimeMillis();
		double distance;
		Player player=handler.getWorld().getEntityManager().getPlayer();
		
		distance = Math.hypot(Math.abs(this.getCenterX() - player.getCenterX()), Math.abs(this.getCenterY() - player.getCenterY()));
		
		if(x < player.getX()&&distance<=maxFollowDistance) {xMove=speed;
			int tx = (int)(x + xMove + bounds.width)/Tile.TILE_WIDTH;			
			if(!collisionWithTile(tx, (int)(y + bounds.y)/Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int)(y + bounds.y + bounds.height)/Tile.TILE_HEIGHT)) {
				x += xMove;
			}	else {
				x = tx *Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
				x+=xMove;
			}
		}else if(x > player.getX()&&distance<=maxFollowDistance){xMove=-speed;
			int tx = (int)(x + xMove)/Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int)y/Tile.TILE_WIDTH) &&
					!collisionWithTile(tx, (int)(y+bounds.height)/Tile.TILE_HEIGHT)) {
				x += xMove;
			}else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
				x+=xMove;
			}
		}else {xMove=0;}
	
	}
	
	public void moveY() {
		double distance;
		Player player=handler.getWorld().getEntityManager().getPlayer();
		
		distance = Math.hypot(Math.abs(this.getCenterX() - player.getCenterX()), Math.abs(this.getCenterY() - player.getCenterY()));
		if(y < player.getY()&&distance<=maxFollowDistance) {yMove=+speed;
			int ty = (int)(y + yMove + bounds.height)/Tile.TILE_HEIGHT;
			if(!collisionWithTile((int)(x + bounds.x)/Tile.TILE_WIDTH, ty) &&
					!collisionWithTile((int)(x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILE_HEIGHT - bounds.height - bounds.y - 1; 
				y+=yMove;
			}
		}else if(y > player.getY()&&distance<=maxFollowDistance){yMove=-speed;
			int ty = (int)(y + yMove)/Tile.TILE_HEIGHT;
			if(!collisionWithTile((int)x/Tile.TILE_HEIGHT, ty) &&
					!collisionWithTile((int)(x+bounds.width)/Tile.TILE_WIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
				y+=yMove;
			}
		}else{yMove=0;}
	}
	
	@Override
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
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
