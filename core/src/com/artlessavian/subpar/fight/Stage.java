package com.artlessavian.subpar.fight;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.entities.Platform;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Stage
{
//	Vector2[] importantLocations
	private Platform[] platforms;

	Sprite s;

	public Stage(SubparMain main, FightScreen game)
	{
		s = new Sprite(main.assetManager.get("Prototype/map2 - Copy.png", Texture.class));
//		s.setScale(2/3f);
		s.setCenter(0,0);

		platforms = new Platform[2];
		platforms[0] = new Platform(main);
		platforms[1] = new Platform(main);
		platforms[1].platformC.rectangle.setSize(100, 100);
		platforms[1].platformC.rectangle.setPosition(100, 100);
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
