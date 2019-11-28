package titleGame.tiles;

import java.awt.image.BufferedImage;

import titleGame.gfx.Assets;

public class WaterTile extends Tile {

	public WaterTile(int ID) {
		super(Assets.water, ID);
		
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
