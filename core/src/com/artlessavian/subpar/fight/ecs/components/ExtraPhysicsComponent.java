package com.artlessavian.subpar.fight.ecs.components;

import com.badlogic.ashley.core.Component;

public class ExtraPhysicsComponent implements Component
{
	public boolean grounded;
	public float passiveGravity;

	public boolean facingLeft; // flipped

	public boolean ignoreClamps = true;
	public float maxXSpeed = Float.POSITIVE_INFINITY;
	public float maxFallSpeed = Float.POSITIVE_INFINITY;

	public float frictionAcc = 0; // acts opposite to motion
}
