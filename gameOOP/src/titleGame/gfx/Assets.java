package titleGame.gfx;

import java.awt.image.BufferedImage;


//Cat nho cac anh trong SpriteSheet
public class Assets {
	
	private static final int width=32, height=32;
	
	public static BufferedImage player, dirt, grass, stone, tree, enemy;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet sheetEnemy = new SpriteSheet(ImageLoader.loadImage("/textures/ene.png"));
		
		enemy = sheetEnemy.crop(0, 0, 400, 400);
		player = sheet.crop(width*4, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width*2, 0, width, height);
		stone = sheet.crop(width*3, 0, width, height);
		tree = sheet.crop(0, 0, width, height);
	}
	
}
