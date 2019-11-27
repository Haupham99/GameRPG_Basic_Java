package titleGame.gfx;

import java.awt.image.BufferedImage;


//Cat nho cac anh trong SpriteSheet
public class Assets {
	
	private static final int width=32, height=32;
	
	public static BufferedImage dirt, grass, stone, tree, enemy;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet sheetEnemy = new SpriteSheet(ImageLoader.loadImage("/textures/ene.png"));
		SpriteSheet character = new SpriteSheet(ImageLoader.loadImage("/textures/character.gif"));
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		enemy_down = new BufferedImage[2];
		enemy_up = new BufferedImage[2];
		enemy_left = new BufferedImage[2];
		enemy_right = new BufferedImage[2];
		
		//enemy
		enemy_down[0] = character.crop(width*4, height*3, width, height);
		enemy_down[1] = character.crop(width*5, height*3, width, height);
		enemy_up[0] = character.crop(width*4, height*2, width, height);
		enemy_up[1] = character.crop(width*5, height*2, width, height);
		enemy_left[0] = character.crop(width*4, 0, width, height);
		enemy_left[1] = character.crop(width*5, 0, width, height);
		enemy_right[0] = character.crop(width*4, height, width, height);
		enemy_right[1] = character.crop(width*5, height, width, height);
		
		
		//player
		player_down[0] = character.crop(0, height*3, width, height);
		player_down[1] = character.crop(width, height*3, width, height);
		player_up[0] = character.crop(0, height*2, width, height);
		player_up[1] = character.crop(width, height*2, width, height);
		player_left[0] = character.crop(0, 0, width, height);
		player_left[1] = character.crop(width, 0, width, height);
		player_right[0] = character.crop(0, height, width, height);
		player_right[1] = character.crop(width, height, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width*2, 0, width, height);
		stone = sheet.crop(width*3, 0, width, height);
		tree = sheet.crop(0, 0, width, height);
//		tree = character.crop(0, 0, width, height);
	}
	
}
