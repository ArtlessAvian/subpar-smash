package com.artlessavian.subpar.fight.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class PhysicsComponent implements Component
{
	public Vector2 lastPos;
	public Vector2 pos;
	public Vector2 vel;
	public Vector2 acc;

	public PhysicsComponent()
	{
		lastPos = new Vector2(0, 0);
		pos = new Vector2(0, 0);
		vel = new Vector2(0, 0);
		acc = new Vector2(0, 0);
	}
}
