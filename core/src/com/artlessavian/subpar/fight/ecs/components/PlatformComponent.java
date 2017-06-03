package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.OffsetRectangle;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class PlatformComponent implements Component
{
	public Rectangle rectangle;
	boolean isSoft = false;
	Sprite frontSprite;

	public PlatformComponent()
	{
		rectangle = new Rectangle(-1000, -20, 2000, 20);
	}
}
