package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.CollisionComponent;
import com.artlessavian.subpar.fight.ecs.components.ExtraPhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PlatformComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;

public class CollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> platforms;
	private Rectangle dirtyRectangle = new Rectangle();

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
				dirtyRectangle.set(collisionC.movementRect);
				dirtyRectangle.x += collisionC.diamond.leftX;
				dirtyRectangle.y += collisionC.diamond.bottomHorizontalY;
				if (dirtyRectangle.overlaps(platformC.bounds))
				{
					System.out.println("onTouchLeft");
					collisionC.behavior.onTouchLeft(platformC.bounds, entity, platform);
					collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
				}
				dirtyRectangle.set(collisionC.movementRect);
				dirtyRectangle.x += collisionC.diamond.leftX;
				dirtyRectangle.y += collisionC.diamond.topHorizontalY;
				if (dirtyRectangle.overlaps(platformC.bounds))
				{
					System.out.println("onTouchLeft");
					collisionC.behavior.onTouchLeft(platformC.bounds, entity, platform);
					collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
				}

				// Right Side
				dirtyRectangle.set(collisionC.movementRect);
				dirtyRectangle.x += collisionC.diamond.rightX;
				dirtyRectangle.y += collisionC.diamond.bottomHorizontalY;
				if (dirtyRectangle.overlaps(platformC.bounds))
				{
					System.out.println("onTouchRight");
					collisionC.behavior.onTouchRight(platformC.bounds, entity, platform);
					collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
				}
				dirtyRectangle.set(collisionC.movementRect);
				dirtyRectangle.x += collisionC.diamond.rightX;
				dirtyRectangle.y += collisionC.diamond.topHorizontalY;
				if (dirtyRectangle.overlaps(platformC.bounds))
				{
					System.out.println("onTouchRight");
					collisionC.behavior.onTouchRight(platformC.bounds, entity, platform);
					collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
				}

				// Check ground for falling off
				if (extraPhysicsC != null && extraPhysicsC.ground != null)
				{
					dirtyRectangle.set(collisionC.movementRect);
					dirtyRectangle.y += collisionC.diamond.bottomY - 1;
					if (!dirtyRectangle.overlaps(extraPhysicsC.ground))
					{
						System.out.println("onEdge");
						collisionC.behavior.onEdge(extraPhysicsC.ground, entity);
					}
				}

				// Bottom Side
				dirtyRectangle.set(collisionC.movementRect);
				dirtyRectangle.y += collisionC.diamond.bottomY + 1;
				if (dirtyRectangle.overlaps(platformC.bounds))
				{
					System.out.println("onTouchFloor");
					collisionC.behavior.onTouchFloor(platformC.bounds, entity, platform);
					collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
				}

				// Top Side
				dirtyRectangle.set(collisionC.movementRect);
				dirtyRectangle.y += collisionC.diamond.topY;
				if (dirtyRectangle.overlaps(platformC.bounds))
				{
					System.out.println("onTouchCeil");
					collisionC.behavior.onTouchCeil(platformC.bounds, entity, platform);
					collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
				}


			}

			if (collisionC.movementRect.y < -100)
			{
				entity.getComponent(PhysicsComponent.class).pos.set(0, 400);
			}
		}
	}
}
