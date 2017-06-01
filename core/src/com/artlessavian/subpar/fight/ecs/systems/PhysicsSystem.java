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

			// projectile motion with speed limits
			if (extraPhysicsC == null)
			{
				projectileMovementX(physicsC, deltaTime);
			}
			else
			{
				if (physicsC.vel.x > extraPhysicsC.maxXSpeed) {physicsC.vel.x = extraPhysicsC.maxXSpeed;}
				if (physicsC.vel.x < -extraPhysicsC.maxXSpeed) {physicsC.vel.x = -extraPhysicsC.maxXSpeed;}
				if (Math.abs(physicsC.vel.x + physicsC.acc.x * deltaTime) > extraPhysicsC.maxXSpeed)
				{
					float t = Math.abs(Math.abs(physicsC.vel.x) - extraPhysicsC.maxXSpeed) / Math.abs(physicsC.acc.x);
					projectileMovementX(physicsC, t);
					physicsC.vel.x = Math.signum(physicsC.acc.x * deltaTime + physicsC.vel.x) * extraPhysicsC.maxXSpeed;
					physicsC.acc.x = 0;
					projectileMovementX(physicsC, deltaTime - t);
				}
				else
				{
					projectileMovementX(physicsC, deltaTime);
				}
			}

			// repeat for y component, kinda

			if (extraPhysicsC == null)
			{
				projectileMovementY(physicsC, deltaTime);
			}
			else if (extraPhysicsC.grounded)
			{

			}
			else
			{
				if (physicsC.vel.y < -extraPhysicsC.maxFallSpeed) {physicsC.vel.x = -extraPhysicsC.maxFallSpeed;}
				if (physicsC.vel.y + physicsC.acc.y * deltaTime < -extraPhysicsC.maxFallSpeed)
				{
					float t = Math.abs(physicsC.vel.y + extraPhysicsC.maxFallSpeed) / Math.abs(physicsC.acc.y);
					projectileMovementY(physicsC, t);
					physicsC.vel.y = -extraPhysicsC.maxFallSpeed;
					physicsC.acc.y = 0;
					projectileMovementY(physicsC, deltaTime - t);
				}
				else
				{
					projectileMovementY(physicsC, deltaTime);
				}
			}

			physicsC.acc.set(0,0);
		}
	}

	public static void projectileMovementX(PhysicsComponent physicsC, float deltaTime)
	{
		physicsC.pos.x += physicsC.vel.x * deltaTime + physicsC.acc.x * deltaTime * deltaTime / 2f;
		physicsC.vel.x += physicsC.acc.x * deltaTime;
	}

	public static void projectileMovementY(PhysicsComponent physicsC, float deltaTime)
	{
		physicsC.pos.y += physicsC.vel.y * deltaTime + physicsC.acc.y * deltaTime * deltaTime / 2f;
		physicsC.vel.y += physicsC.acc.y * deltaTime;
	}
}
