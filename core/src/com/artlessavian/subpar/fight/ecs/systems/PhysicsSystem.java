package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.CollisionComponent;
import com.artlessavian.subpar.fight.ecs.components.ExtraPhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

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
			CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);

//			physicsC.acc.set((float)Math.cos(Gdx.graphics.getFrameId() / 60f) * 60, (float)Math.sin(Gdx.graphics.getFrameId() / 60f) * 60);
//			physicsC.acc.set(0, -100);

//			physicsC.vel.add((float)(Math.random() * 100 - 50), 0);

			physicsC.lastPos.set(physicsC.pos);

			if (collisionC == null || (collisionC.ground == null && collisionC.ground2 == null))
			{
				doAirMovement(deltaTime, physicsC, extraPhysicsC);
			}
			else
			{
				doGroundMovement(deltaTime, entity, physicsC, extraPhysicsC, collisionC);
			}

			physicsC.acc.set(0, 0);
		}
	}

	private static void doAirMovement(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
		// Will underestimate acceleration, and overestimate deceleration
		float projectedXVel = projectXVel(deltaTime, physicsC, extraPhysicsC);

		physicsC.pos.x += (physicsC.vel.x + projectedXVel) / 2f * deltaTime;
		physicsC.vel.x = projectedXVel;

		// Will underestimate acceleration, and overestimate deceleration
		float projectedYVel = physicsC.vel.y + physicsC.acc.y * deltaTime;

		if (extraPhysicsC != null)
		{
			projectedYVel -= extraPhysicsC.gravityAcc * deltaTime;
			if (projectedYVel < -extraPhysicsC.maxFallSpeed) {projectedYVel = -extraPhysicsC.maxFallSpeed;}
		}

		physicsC.pos.y += (physicsC.vel.y + projectedYVel) / 2f * deltaTime;
		physicsC.vel.y = projectedYVel;
	}

	private void doGroundMovement(float deltaTime, Entity entity, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC, CollisionComponent collisionC)
	{
		float projectedXVel = projectXVel(deltaTime, physicsC, extraPhysicsC);
		float deltaAlong = (physicsC.vel.x + projectedXVel) / 2f * deltaTime;
		physicsC.vel.x = projectedXVel;

		Vector2 feet = physicsC.pos.cpy().add(0, collisionC.diamond.bottomY);

		if (deltaAlong < 0)
		{
			deltaAlong *= -1;
			do
			{
				if (deltaAlong > feet.dst(collisionC.ground2.previousPoint))
				{
					deltaAlong -= feet.dst(collisionC.ground2.previousPoint);
					feet.set(collisionC.ground2.previousPoint);
					collisionC.ground2 = collisionC.ground2.previous;
				}
				else
				{
					Vector2 helper = new Vector2();
					helper.set(collisionC.ground2.previousPoint);
					helper.sub(collisionC.ground2.nextPoint);
					helper.setLength(deltaAlong);
					feet.add(helper);
					deltaAlong = 0;
				}

				// check for walls
				// check for ledges
				if (collisionC.ground2 == null)
				{
					collisionC.behavior.onEdge(null, entity);
					deltaAlong = 0;
				}


			} while (deltaAlong != 0);
		}
		else
		{
			do
			{
				if (deltaAlong > feet.dst(collisionC.ground2.nextPoint))
				{
					deltaAlong -= feet.dst(collisionC.ground2.nextPoint);
					feet.set(collisionC.ground2.nextPoint);
					collisionC.ground2 = collisionC.ground2.next;
				}
				else
				{
					Vector2 helper = new Vector2();
					helper.set(collisionC.ground2.nextPoint)
						.sub(collisionC.ground2.previousPoint)
						.setLength(deltaAlong);
					feet.add(helper);
					deltaAlong = 0;
				}

				// check for walls
				// check for ledges
				if (collisionC.ground2 == null)
				{
					collisionC.behavior.onEdge(null, entity);
					deltaAlong = 0;
				}

			} while (deltaAlong != 0);
		}

		physicsC.pos.set(feet).sub(0,collisionC.diamond.bottomY);
//
//		while (collisionC.ground2 != null && physicsC.pos.x < collisionC.ground2.previousPoint.x)
//		{
//			collisionC.ground2 = collisionC.ground2.previous;
//		}
//		while (collisionC.ground2 != null && collisionC.ground2.nextPoint.x < physicsC.pos.x)
//		{
//			collisionC.ground2 = collisionC.ground2.next;
//		}
//		if (collisionC.ground2 != null)
//		{
//			// TODO: aefj;alefjajflaj
//			physicsC.pos.y = collisionC.ground2.intersectVertical(physicsC.pos.x) - collisionC.diamond.bottomY;
//		}
	}

	private static float projectXVel(float deltaTime, PhysicsComponent physicsC, ExtraPhysicsComponent extraPhysicsC)
	{
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

		return projectedXVel;
	}
}
