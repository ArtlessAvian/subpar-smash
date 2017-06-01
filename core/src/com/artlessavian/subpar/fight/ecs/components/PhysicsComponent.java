package com.artlessavian.subpar.fight.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PhysicsComponent implements Component
{
	public Vector2 lastPos = new Vector2(0, 0);
	public Vector2 pos = new Vector2(0, 0);
	public Vector2 vel = new Vector2(0, 0);
	public Vector2 acc = new Vector2(0, 0);

//	public PhysicsComponent()
//	{
//	}
}
