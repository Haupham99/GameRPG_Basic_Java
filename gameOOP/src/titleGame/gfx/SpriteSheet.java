package titleGame.gfx;

import java.awt.image.BufferedImage;


//Anh to bao gom nhieu anh nho
public class SpriteSheet {
	
	private BufferedImage sheet; 
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

}
