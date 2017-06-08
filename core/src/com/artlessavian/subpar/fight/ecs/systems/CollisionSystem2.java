package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.common.Polygon;
import com.artlessavian.subpar.fight.ecs.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionSystem2 extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> platforms;
	private ImmutableArray<Entity> polygons;

	@Override
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class, CollisionComponent.class).get());
		platforms = engine.getEntitiesFor(Family.all(PlatformComponent.class).get());
		polygons = engine.getEntitiesFor(Family.all(PolygonComponent.class).get());
	}

	@Override
	public void update(float deltaTime)
	{
		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
			ExtraPhysicsComponent extraPhysicsC = entity.getComponent(ExtraPhysicsComponent.class);
			CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);

			// check that there is a collision with any wall
			boolean leftCollided;
			boolean rightCollided;
			// handle after every wall is checked

			boolean floorCollided;

			Vector2 feet = physicsC.pos.cpy().add(0, collisionC.diamond.bottomY);
			if (extraPhysicsC.ground2 != null)
			{
				// works assuming the ground is a floor and not a ceiling
				while (extraPhysicsC.ground2 != null && physicsC.pos.x < extraPhysicsC.ground2.previousPoint.x)
				{
					extraPhysicsC.ground2 = extraPhysicsC.ground2.previous;
				}
				while (extraPhysicsC.ground2 != null && extraPhysicsC.ground2.nextPoint.x < physicsC.pos.x)
				{
					extraPhysicsC.ground2 = extraPhysicsC.ground2.next;
				}
				if (extraPhysicsC.ground2 != null)
				{
					// TODO: aefj;alefjajflaj
					physicsC.pos.y = extraPhysicsC.ground2.intersectVertical(physicsC.pos.x) - collisionC.diamond.bottomY;
				}
			}
			if (extraPhysicsC.ground2 == null)
			{
				Vector2 feetPrevious = physicsC.lastPos.cpy().add(0, collisionC.diamond.bottomY);
				for (Entity polygon : polygons)
				{
					PolygonComponent polygonC = polygon.getComponent(PolygonComponent.class);
					for (Polygon.Segment s : polygonC.p.edges)
					{
//						if (Math.abs(s.normal - 90) > 45) {continue;}
						Vector2 v = s.intersect(feet, feetPrevious);
						if (v != null)
						{
							System.out.println(s.distance(feet) + " " + s.distance(feetPrevious));
							System.out.println("passed through " + s.previousPoint + " " + s.nextPoint);
							Vector2 aaa = s.intersect(feet, feetPrevious);
							System.out.println(aaa + " " + s.distance(aaa) + " " + s.pointOnSegment(aaa));
							System.out.println(feet + " " + feetPrevious);
							if (s.pointOnSegment(aaa))
							{
								collisionC.behavior.onTouchFloor2(s, entity, polygon);
							}
						}
					}
				}
			}

			boolean ceilingCollided;




			if (physicsC.pos.y < -1000)
			{
				physicsC.pos.set(0, 400);
			}
		}
	}
}
