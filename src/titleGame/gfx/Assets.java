package titleGame.gfx;

import java.awt.image.BufferedImage;


//Cat nho cac anh trong SpriteSheet
public class Assets {
	
	private static final int width=32, height=32;
	
	public static BufferedImage dirt, grass, stone, tree, enemy, water;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_static;
	public static BufferedImage[] player_attackDown, player_attackUp, player_attackLeft, player_attackRight;
	public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right,enemy_static;
	public static BufferedImage[] btn_easy,btn_hard;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet sheetEnemy = new SpriteSheet(ImageLoader.loadImage("/textures/ene.png"));
		SpriteSheet character = new SpriteSheet(ImageLoader.loadImage("/textures/character.gif"));
		SpriteSheet character1 = new SpriteSheet(ImageLoader.loadImage("/textures/3_Actor1.png"));
		SpriteSheet playerAttack = new SpriteSheet(ImageLoader.loadImage("/textures/3_Actor1_7.png"));
		SpriteSheet waterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Water5.png"));
		
		//player move
		player_static =new BufferedImage[1];
		player_down = new BufferedImage[3];
		player_up = new BufferedImage[3];
		player_left = new BufferedImage[3];
		player_right = new BufferedImage[3];
		
		//player attack
		player_attackDown = new BufferedImage[9];
		player_attackUp = new BufferedImage[9];
		player_attackLeft = new BufferedImage[9];
		player_attackRight = new BufferedImage[9];
		
		//enemy move
		enemy_static =new BufferedImage[1];
		enemy_down = new BufferedImage[2];
		enemy_up = new BufferedImage[2];
		enemy_left = new BufferedImage[2];
		enemy_right = new BufferedImage[2];
		
		//enemy
		enemy_static[0]=character.crop(width*5, height*3, width, height);
		enemy_down[0] = character.crop(width*4, height*3, width, height);
		enemy_down[1] = character.crop(width*5, height*3, width, height);
		enemy_up[0] = character.crop(width*4, height*2, width, height);
		enemy_up[1] = character.crop(width*5, height*2, width, height);
		enemy_left[0] = character.crop(width*4, 0, width, height);
		enemy_left[1] = character.crop(width*5, 0, width, height);
		enemy_right[0] = character.crop(width*4, height, width, height);
		enemy_right[1] = character.crop(width*5, height, width, height);
		
		
		//player move
		player_static[0] = character1.crop(64*7, 64*4, 64, 64);
		player_down[0] = character1.crop(64*6, 64*4, 64, 64);
		player_down[1] = character1.crop(64*7, 64*4, 64, 64);
		player_down[2] = character1.crop(64*8, 64*4, 64, 64);
		player_right[0] = character1.crop(64*6, 64*6, 64, 64);
		player_right[1] = character1.crop(64*7, 64*6, 64, 64);
		player_right[2] = character1.crop(64*8, 64*6, 64, 64);
		player_left[0] = character1.crop(64*6, 64*5, 64, 64);
		player_left[1] = character1.crop(64*7, 64*5, 64, 64);
		player_left[2] = character1.crop(64*8, 64*5, 64, 64);
		player_up[0] = character1.crop(64*6, 64*7, 64, 64);
		player_up[1] = character1.crop(64*7, 64*7, 64, 64);
		player_up[2] = character1.crop(64*8, 64*7, 64, 64);
		
		//player attack
		for(int i=0; i<9; i++) {
			player_attackDown[i] = playerAttack.crop(96*i, 96*0, 96, 96);
			player_attackUp[i] = playerAttack.crop(96*i, 96*1, 96, 96);
			player_attackLeft[i] = playerAttack.crop(96*i, 96*2, 96, 96);
			player_attackRight[i] = playerAttack.crop(96*i, 96*3, 96, 96);
		}
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width*2, 0, width, height);
		stone = sheet.crop(width*3, 0, width, height);
		tree = sheet.crop(0, 0, width, height);
		water = waterSheet.crop(0, 96*2, 96, 96);
//		tree = character.crop(0, 0, width, height);
		
		btn_easy= new BufferedImage[2];
		btn_easy[0]=ImageLoader.loadImage("/textures/easyDefault.png");
		btn_easy[1]=ImageLoader.loadImage("/textures/easyEntered.png");
		
		btn_hard= new BufferedImage[2];
		btn_hard[0]=ImageLoader.loadImage("/textures/hardDefault.png");
		btn_hard[1]=ImageLoader.loadImage("/textures/hardEntered.png");
	}
	
}
