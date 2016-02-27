package com.artlessavian.subpar.systems.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component
{
	public Vector2 position;

	public PositionComponent(Vector2 init)
	{
		position = init;
	}
}
