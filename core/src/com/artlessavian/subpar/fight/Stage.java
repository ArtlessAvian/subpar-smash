package com.artlessavian.subpar.fight;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.entities.PlatformPolygon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Stage
{
	//	Vector2[] importantLocations
	private PlatformPolygon[] platforms;

	Sprite s;

	public Stage(SubparMain main, FightScreen game)
	{
		s = new Sprite(main.assetManager.get("Prototype/map2 - Copy.png", Texture.class));
//		s.setScale(2/3f);
		s.setCenter(0, 0);

		platforms = new PlatformPolygon[1];
		platforms[0] = new PlatformPolygon(main);
	}

	public void drawBackground(SubparMain main, FightScreen game)
	{
		s.draw(main.spriteBatch);
	}

	public PlatformPolygon[] getPlatforms()
	{
		return platforms;
	}
}
