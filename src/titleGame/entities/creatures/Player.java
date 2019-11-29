package titleGame.entities.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import titleGame.Handler;
import titleGame.entities.Entity;
import titleGame.gfx.Animation;
import titleGame.gfx.Assets;
import titleGame.worlds.World;

public class Player extends Creature{
	
	//Animation
	private Animation playerStatic;
	
	private Animation animationDown;
	private Animation animationUp;
	private Animation animationLeft;
	private Animation animationRight;
	
	private Animation animationAttackDown;
	private Animation animationAttackUp;
	private Animation animationAttackLeft;
	private Animation animationAttackRight;
	
	//Attack time
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	private long timer, lastTime, timer1, lastTime1;
	private int count;
	private static final int DEFAULT_HEALTH =30;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		
		health = DEFAULT_HEALTH;
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 60;
		bounds.height =60;
		
		count = 0;
		
		timer = 0;
		timer1 = 0;
		lastTime = System.nanoTime();
		lastTime1 = System.nanoTime();
		
		playerStatic = new Animation(200, Assets.player_static);
		
		animationDown = new Animation(200, Assets.player_down);
		animationUp = new Animation(200, Assets.player_up);
		animationLeft = new Animation(200, Assets.player_left);
		animationRight = new Animation(200, Assets.player_right);
		
		animationAttackDown = new Animation(70, Assets.player_attackDown);
		animationAttackUp = new Animation(70, Assets.player_attackUp);
		animationAttackLeft = new Animation(70, Assets.player_attackLeft);
		animationAttackRight = new Animation(70, Assets.player_attackRight);
	}

	public void tick() {
		//Movement
		playerStatic.tick();
		animationDown.tick();
		animationUp.tick();
		animationLeft.tick();
		animationRight.tick();
		animationAttackDown.tick();
		animationAttackUp.tick();
		animationAttackLeft.tick();
		animationAttackRight.tick();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkHurt();
		checkAttack();
	}
	
	private void checkHurt() {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e == this)
				continue;
			else {
				Rectangle RecPlayer = new Rectangle();
				RecPlayer.x = (int)x;
				RecPlayer.y = (int)y;
				RecPlayer.width = 64;
				RecPlayer.height = 64;
				
				Rectangle RecEnemy = new Rectangle();
				RecEnemy.x = (int)e.getX();
				RecEnemy.y = (int)e.getY();
				RecEnemy.width = 64;
				RecEnemy.height = 64;
				if(RecPlayer.intersects(RecEnemy)) {
					
					timer += System.nanoTime() - lastTime;
					if(timer >= 1000000000) {
						health -= 1;
						timer = 0;
					}
					lastTime = System.nanoTime();
				}
			}
		}
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
				if(health < 0) {
					active = false;
					die();
				}
				return;
			}
		}
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
		if(getCurrentAnimationAttack() != null) {
			g.drawImage(getCurrentAnimationAttack(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		}else {
			g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		}
		if(health < 1) {
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
			g.drawString("Game Over!", 500, 500);
			timer1 += System.nanoTime() - lastTime1;
			if(timer1 >= 1000000000) {
				count += 1;
				timer1 = 0;
			}
			if(count > 50) {
				x=80;y=80;
				health =DEFAULT_HEALTH;			
				handler.getGame().setMenuState(null);
			}
		}
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
		}else if(yMove >0) {
			return animationDown.getCurrentFrame();
		}else {
			return playerStatic.getCurrentFrame();
		}
	}
	
	private BufferedImage getCurrentAnimationAttack() {
		if(handler.getKeyManager().aDown) {
			return animationAttackDown.getCurrentFrame();
		}else if (handler.getKeyManager().aUp) {
			return animationAttackUp.getCurrentFrame();
		}else if (handler.getKeyManager().aLeft) {
			return animationAttackLeft.getCurrentFrame();
		}else if (handler.getKeyManager().aRight) {
			return animationAttackRight.getCurrentFrame();
		}else {
			return null;
		}
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
}
