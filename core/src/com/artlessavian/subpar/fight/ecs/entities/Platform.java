package com.artlessavian.subpar.fight.ecs.entities;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PlatformComponent;
import com.badlogic.ashley.core.Entity;

public class Platform extends Entity
{
	public PhysicsComponent physicsC;
	public PlatformComponent platformC;

	public Platform(SubparMain main)
	{
		decorateEntity(main, this);
	}

	// Replace with a factory if this gets too hairy
	public static Entity decorateEntity(SubparMain main, Entity e)
	{
		Platform p = (Platform)e;

		// cut this if replacing with factory
//		p.physicsC = new PhysicsComponent();
//		p.physicsC.pos.set(0, 10);
//		p.physicsC.vel.set(0, 0);
//		p.add(p.physicsC);

		p.platformC = new PlatformComponent();
		p.add(p.platformC);

		return p;
	}
}
