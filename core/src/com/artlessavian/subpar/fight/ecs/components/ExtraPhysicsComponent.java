package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.Polygon;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class ExtraPhysicsComponent implements Component
{
	public Rectangle ground;
	public Polygon.Segment ground2;

	public boolean facingLeft; // flipped

	public boolean ignoreClamps = true;
	public float maxXSpeed = Float.POSITIVE_INFINITY;
	public float maxFallSpeed = Float.POSITIVE_INFINITY;

	public float frictionAcc = 0; // acts opposite to motion
	public float gravityAcc = 3600; // acts down
}
