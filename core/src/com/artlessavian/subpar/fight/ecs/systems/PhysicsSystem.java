package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.CollisionComponent;
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

//			physicsC.vel.add((float)(Math.random() * 100 - 50), 0);

			physicsC.lastPos.set(physicsC.pos);

			doMovement(deltaTime, physicsC, extraPhysicsC);

			physicsC.acc.set(0, 0);

			CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);
			if (collisionC != null)
			{
				collisionC.movementRect.x = Math.min(physicsC.pos.x, physicsC.lastPos.x);
				collisionC.movementRect.y = Math.min(physicsC.pos.y, physicsC.lastPos.y);
				collisionC.movementRect.width = Math.abs(physicsC.pos.x - physicsC.lastPos.x);
				collisionC.movementRect.height = Math.abs(physicsC.pos.y - physicsC.lastPos.y);
			}
		}
	}

	private static void doMovement(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		projectileMovementX(deltaTime, physicsC, extraPhysicsC);
		projectileMovementY(deltaTime, physicsC, extraPhysicsC);
	}

	private static void projectileMovementX(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		// Will underestimate acceleration, and overestimate deceleration
		float projectedXVel = physicsC.vel.x + physicsC.acc.x * deltaTime;

		if (extraPhysicsC != null)
		{
			// TODO: Figure out if this lazy friction is ok
			float projectedXVelFriction = -Math.signum(projectedXVel) * extraPhysicsC.frictionAcc * deltaTime + projectedXVel;
			if (projectedXVel * projectedXVelFriction < 0)
			{
				projectedXVel = 0;
			}
			else
			{
				projectedXVel = projectedXVelFriction;
			}

			if (projectedXVel > extraPhysicsC.maxXSpeed) {projectedXVel = extraPhysicsC.maxXSpeed;}
			if (projectedXVel < -extraPhysicsC.maxXSpeed) {projectedXVel = -extraPhysicsC.maxXSpeed;}
		}

		physicsC.pos.x += (physicsC.vel.x + projectedXVel) / 2f * deltaTime;
		physicsC.vel.x = projectedXVel;
	}

	private static void projectileMovementY(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		// Will underestimate acceleration, and overestimate deceleration
		float projectedYVel = physicsC.vel.y + physicsC.acc.y * deltaTime;

		if (extraPhysicsC != null)
		{
			if (extraPhysicsC.ground != null || extraPhysicsC.ground2 != null)
			{
				physicsC.vel.y = 0;
			}
			else
			{
				projectedYVel -= extraPhysicsC.gravityAcc * deltaTime;
				if (projectedYVel < -extraPhysicsC.maxFallSpeed) {projectedYVel = -extraPhysicsC.maxFallSpeed;}
			}
		}

		physicsC.pos.y += (physicsC.vel.y + projectedYVel) / 2f * deltaTime;
		physicsC.vel.y = projectedYVel;
	}
}
