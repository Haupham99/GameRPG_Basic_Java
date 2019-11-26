package titleGame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import titleGame.Handler;
import titleGame.entities.Entity;
import titleGame.gfx.Animation;
import titleGame.gfx.Assets;

public class Player extends Creature{
	
	//Animation
	private Animation animationDown;
	private Animation animationUp;
	private Animation animationLeft;
	private Animation animationRight;
	
	//Attack time
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 60;
		bounds.height =60;
		
		animationDown = new Animation(200, Assets.player_down);
		animationUp = new Animation(200, Assets.player_up);
		animationLeft = new Animation(200, Assets.player_left);
		animationRight = new Animation(200, Assets.player_right);
	}

	public void tick() {
		//Movement
		animationDown.tick();
		animationUp.tick();
		animationLeft.tick();
		animationRight.tick();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkAttack();
	}
	
	private void checkAttack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
			
		Rectangle cb = getCollisionBound(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = 20;
		ar.height = 20;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width/2 - ar.width/2;
			ar.y = cb.y - ar.height;
		}else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width/2 - ar.width/2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - ar.width;
			ar.y = cb.y + cb.height/2 - ar.height/2;
		}else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - ar.height/2;
		}else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBound(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	public void die() {
		System.out.println("Die");
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
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
	
}
