package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class PhysicsSystem extends IteratingSystem
{
	public PhysicsSystem()
	{
		super(Family.all(PhysicsComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		physicsC.pos.x += physicsC.vel.x * deltaTime + physicsC.acc.x * deltaTime * deltaTime / 2f;
		physicsC.pos.y += physicsC.vel.y * deltaTime + physicsC.acc.y * deltaTime * deltaTime / 2f;
		physicsC.vel.x += physicsC.acc.x * deltaTime;
		physicsC.vel.y += physicsC.acc.y * deltaTime;
	}
}
