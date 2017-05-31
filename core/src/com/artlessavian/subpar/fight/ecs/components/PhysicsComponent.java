package com.artlessavian.subpar.fight.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class PhysicsComponent implements Component
{
	public Vector2 pos;
	public Vector2 vel;
	public Vector2 acc;

	public PhysicsComponent()
	{
		pos = new Vector2();
		vel = new Vector2();
		acc = new Vector2();
	}
}
