package com.artlessavian.subpar.fight.ecs.entities;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.PolygonComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class PlatformPolygon extends Entity
{
	private PolygonComponent polygonC;

	public PlatformPolygon(SubparMain main)
	{
		decorateEntity(main, this);
	}

	// Replace with a factory if this gets too hairy
	public static Entity decorateEntity(SubparMain main, Entity e)
	{
		PlatformPolygon p = (PlatformPolygon)e;

		// cut this if replacing with factory
//		p.physicsC = new PhysicsComponent();
//		p.physicsC.pos.set(0, 10);
//		p.physicsC.vel.set(0, 0);
//		p.add(p.physicsC);

		p.polygonC = new PolygonComponent();
		p.add(p.polygonC);

		return p;
	}
}
