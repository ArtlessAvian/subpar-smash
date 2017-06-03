package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.*;
import com.artlessavian.subpar.fight.fighterstates.StandState;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class CollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> platforms;

	@Override
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
		platforms = engine.getEntitiesFor(Family.all(PlatformComponent.class).get());
	}

	@Override
	public void update(float deltaTime)
	{
		for (Entity entity : entities)
		{
			CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);
			ExtraPhysicsComponent extraPhysicsC = entity.getComponent(ExtraPhysicsComponent.class);

			for (Entity platform : platforms)
			{
				PlatformComponent platformC = platform.getComponent(PlatformComponent.class);

				// Left Side
				collisionC.movementRect.x += collisionC.diamond.leftX;
				if (collisionC.movementRect.overlaps(platformC.rectangle))
				{
					System.out.println("onTouchLeft");
					collisionC.behavior.onTouchLeft(platformC.rectangle, entity, platform);
				}
				collisionC.movementRect.x -= collisionC.diamond.leftX;

				// Right Side
				collisionC.movementRect.x += collisionC.diamond.rightX;
				if (collisionC.movementRect.overlaps(platformC.rectangle))
				{
					System.out.println("onTouchRight");
					collisionC.behavior.onTouchRight(platformC.rectangle, entity, platform);
				}
				collisionC.movementRect.x -= collisionC.diamond.rightX;

				// Check ground for falling off
				if (extraPhysicsC != null && extraPhysicsC.ground != null)
				{
					collisionC.movementRect.y += collisionC.diamond.bottomY - 1;
					if (!collisionC.movementRect.overlaps(extraPhysicsC.ground))
					{
						System.out.println("onEdge");
						collisionC.behavior.onEdge(extraPhysicsC.ground, entity);
					}
					collisionC.movementRect.y -= collisionC.diamond.bottomY - 1;
				}

				// Bottom Side
				collisionC.movementRect.y += collisionC.diamond.bottomY + 1;
				if (collisionC.movementRect.overlaps(platformC.rectangle))
				{
					System.out.println("onTouchFloor");
					collisionC.behavior.onTouchFloor(platformC.rectangle, entity, platform);
				}
				collisionC.movementRect.y -= collisionC.diamond.bottomY + 1;

				// Top Side
				collisionC.movementRect.y += collisionC.diamond.topY;
				if (collisionC.movementRect.overlaps(platformC.rectangle))
				{
					System.out.println("onTouchCeil");
					collisionC.behavior.onTouchCeil(platformC.rectangle, entity, platform);
				}
				collisionC.movementRect.y -= collisionC.diamond.topY;


			}

			if (collisionC.movementRect.y < -100)
			{
				entity.getComponent(PhysicsComponent.class).pos.set(0, 100);
			}
		}
	}
}
