package com.artlessavian.subpar.fight;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.entities.Platform;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Stage
{
	//	Vector2[] importantLocations
	private Platform[] platforms;

	Sprite s;

	public Stage(SubparMain main, FightScreen game)
	{
		s = new Sprite(main.assetManager.get("Prototype/map2 - Copy.png", Texture.class));
//		s.setScale(2/3f);
		s.setCenter(0, 0);

		platforms = new Platform[1];
		platforms[0] = new Platform(main);
//		platforms[1] = new Platform(main);
//		platforms[1].platformC.bounds.setSize(200, 100);
//		platforms[1].platformC.bounds.setCenter((float)Math.random() * 500 + 500, (float)Math.random() * 400);
//		platforms[2] = new Platform(main);
//		platforms[2].platformC.bounds.setSize(200, 100);
//		platforms[2].platformC.bounds.setCenter((float)Math.random() * 500 + 500, (float)Math.random() * 400);
//		platforms[3] = new Platform(main);
//		platforms[3].platformC.bounds.setSize(200, 100);
//		platforms[3].platformC.bounds.setCenter((float)Math.random() * 500 + 500, (float)Math.random() * 400);
//		platforms[4] = new Platform(main);
//		platforms[4].platformC.bounds.setSize(200, 100);
//		platforms[4].platformC.bounds.setCenter((float)Math.random() * 500 + 500, (float)Math.random() * 400);
	}

	public Platform[] getPlatforms()
	{
		return platforms;
	}

	public void drawBackground(SubparMain main, FightScreen game)
	{
		s.draw(main.spriteBatch);
	}
}
