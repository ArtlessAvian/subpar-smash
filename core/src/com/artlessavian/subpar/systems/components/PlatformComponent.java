package com.artlessavian.subpar.systems.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class PlatformComponent implements Component
{
	public Rectangle collision;
	public boolean isSoft;

	public PlatformComponent(float x, float y, float w, float h, boolean soft)
	{
		collision = new Rectangle(0, 0, w, h);
		collision.setCenter(x, y - h / 2);
		this.isSoft = soft;
	}
}
