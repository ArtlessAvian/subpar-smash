package com.artlessavian.subpar.fight.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class PlatformComponent implements Component
{
	public Rectangle bounds;
	Sprite frontSprite;

	public PlatformComponent()
	{
		bounds = new Rectangle(-1000, -20, 2000, 20);
	}
}
