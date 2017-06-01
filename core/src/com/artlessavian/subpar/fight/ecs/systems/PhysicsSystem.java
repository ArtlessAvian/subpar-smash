package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.ExtraPhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

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
//			physicsC.acc.set(0, -100);

			physicsC.lastPos.set(physicsC.pos);

			doMovement(deltaTime, physicsC, extraPhysicsC);

			physicsC.acc.set(0,0);
		}
	}

	public static void doMovement(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		projectileMovementX(deltaTime, physicsC, extraPhysicsC);
		projectileMovementY(deltaTime, physicsC, extraPhysicsC);
	}

	public static void projectileMovementX(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		float projectedXVel = physicsC.vel.x + physicsC.acc.x * deltaTime;

		if (extraPhysicsC != null)
		{
			// TODO: consider friction
			if (projectedXVel > extraPhysicsC.maxXSpeed) {projectedXVel = extraPhysicsC.maxXSpeed;}
			if (projectedXVel < -extraPhysicsC.maxXSpeed) {projectedXVel = -extraPhysicsC.maxXSpeed;}
		}

		physicsC.pos.x += (physicsC.vel.x + projectedXVel) / 2f * deltaTime;
		physicsC.vel.x = projectedXVel;
	}

	public static void projectileMovementY(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		float projectedYVel = physicsC.vel.y + physicsC.acc.y * deltaTime;

		if (extraPhysicsC != null)
		{
			if (!extraPhysicsC.grounded)
			{
				if (projectedYVel < -extraPhysicsC.maxFallSpeed) {projectedYVel = -extraPhysicsC.maxFallSpeed;}
			}
			else
			{
				physicsC.vel.y = 0;
			}
		}

		physicsC.pos.y += (physicsC.vel.x + projectedYVel) / 2f * deltaTime;
		physicsC.vel.y = projectedYVel;
	}
}
