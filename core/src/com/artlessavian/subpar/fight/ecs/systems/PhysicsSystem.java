package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.ExtraPhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;

public class PhysicsSystem extends EntitySystem
{
	ImmutableArray<Entity> entities;

	public PhysicsSystem()
	{

	}

	@Override
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
	}

	@Override
	public void update(float deltaTime)
	{
		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
			ExtraPhysicsComponent extraPhysicsC = entity.getComponent(ExtraPhysicsComponent.class);

//			physicsC.acc.set((float)Math.cos(Gdx.graphics.getFrameId() / 60f) * 60, (float)Math.sin(Gdx.graphics.getFrameId() / 60f) * 60);
			physicsC.acc.set(0, -100);

			physicsC.lastPos.set(physicsC.pos);

			// projectile motion with speed limits

			physicsC.pos.x += physicsC.vel.x * deltaTime + physicsC.acc.x * deltaTime * deltaTime / 2f;
			physicsC.vel.x += physicsC.acc.x * deltaTime;

			// repeat for y component

			if (extraPhysicsC == null || !extraPhysicsC.grounded)
			{
				physicsC.pos.y += physicsC.vel.y * deltaTime + physicsC.acc.y * deltaTime * deltaTime / 2f;
				physicsC.vel.y += physicsC.acc.y * deltaTime;
			}

		}
	}
}