package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.common.Polygon;
import com.artlessavian.subpar.fight.ecs.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

public class CollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> polygons;

	@Override
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class, CollisionComponent.class).get());
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
			if (collisionC.ground == null)
			{
				Vector2 feetPrevious = physicsC.lastPos.cpy().add(0, collisionC.diamond.bottomY);
				for (Entity polygon : polygons)
				{
					PolygonComponent polygonC = polygon.getComponent(PolygonComponent.class);
					for (Polygon.Segment s : polygonC.p.edges)
					{
//						if (Math.abs(s.normal - 90) > 45) {continue;}
						if (s.pointOnSegment(feetPrevious)) {continue;}
						Vector2 v = s.intersect(feet, feetPrevious);
						if (v != null)
						{
							Vector2 aaa = s.intersect(feet, feetPrevious);
							if (s.pointOnSegment(aaa))
							{
								physicsC.pos.set(aaa).sub(0, collisionC.diamond.bottomY);
								collisionC.behavior.onTouchFloor(s, entity, polygon);
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
