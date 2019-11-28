package titleGame.states;

import java.awt.Color;
import java.awt.Graphics;

import titleGame.Game;
import titleGame.Handler;
import titleGame.gfx.Assets;
import titleGame.ui.ClickListener;
import titleGame.ui.UIImageButton;
import titleGame.ui.UIManager;

public class MenuState extends State {
	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager= new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 150, 200, 100, Assets.btn_start, new ClickListener() {

			@Override
			public void onClick() {
			
				State.setState(handler.getGame().gameState);
				
			}}));
	}
	
	public void tick() {
//		if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
//			State.setState((handler.getGame().gameState));
//		}
	}

	public void render(Graphics g) {
//		g.setColor(Color.GRAY);
//		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
	
		uiManager.render(g);
	}
}
