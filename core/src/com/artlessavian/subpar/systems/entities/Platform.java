package com.artlessavian.subpar.systems.entities;

import com.artlessavian.subpar.systems.components.PlatformComponent;
import com.artlessavian.subpar.systems.components.PositionComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class Platform extends Entity
{
	public Platform(float x, float y, float w, float h, boolean soft)
	{
		PositionComponent posComp = new PositionComponent(new Vector2(x, y));
		this.add(posComp);
		this.add(new PlatformComponent(x, y, w, h, soft));
	}
}
