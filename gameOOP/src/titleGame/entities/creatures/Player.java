package titleGame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import titleGame.Game;
import titleGame.Handler;
import titleGame.entities.Entity;
import titleGame.gfx.Assets;

public class Player extends Creature{
	
	//Attack time
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 60;
		bounds.height =60;
	}

	public void tick() {
		//Movement
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
		g.drawImage(Assets.player, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
}
