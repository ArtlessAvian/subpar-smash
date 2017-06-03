package com.artlessavian.subpar.fight.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class ExtraPhysicsComponent implements Component
{
	public Rectangle ground;

	public boolean facingLeft; // flipped

	public boolean ignoreClamps = true;
	public float maxXSpeed = Float.POSITIVE_INFINITY;
	public float maxFallSpeed = Float.POSITIVE_INFINITY;

	public float frictionAcc = 0; // acts opposite to motion
	public float gravityAcc = 2000; // acts down
}
