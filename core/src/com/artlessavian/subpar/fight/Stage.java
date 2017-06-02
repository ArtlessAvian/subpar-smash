package com.artlessavian.subpar.fight;

import com.artlessavian.subpar.SubparMain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Stage
{
//	Vector2[] importantLocations

	Sprite s;

	public Stage(SubparMain main, FightScreen game)
	{
		s = new Sprite(main.assetManager.get("Prototype/map2 - Copy.png", Texture.class));
		s.setScale(2/3f);
		s.setCenter(0,0);
	}

	public void drawBackground(SubparMain main, FightScreen game)
	{
		s.draw(main.spriteBatch);
	}
}
