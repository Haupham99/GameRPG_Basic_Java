package titleGame.states;

import java.awt.Color;
import java.awt.Graphics;

import titleGame.Game;
import titleGame.Handler;

public class MenuState extends State {

	public MenuState(Handler handler) {
		super(handler);
	}
	
	public void tick() {
		if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
			State.setState((handler.getGame().gameState));
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
	}
	
}
