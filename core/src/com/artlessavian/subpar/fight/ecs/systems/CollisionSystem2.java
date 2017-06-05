package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.common.Polygon;
import com.artlessavian.subpar.fight.ecs.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;

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

			// check that there is a collision with any wall
			boolean leftCollided;
			boolean rightCollided;
			// handle after every wall is checked

			boolean floorCollided;
			for (Entity polygon: polygons)
			{
				PolygonComponent polygonC = polygon.getComponent(PolygonComponent.class);
				for (Polygon.Segment s : polygonC.p.edges)
				{
					if (s.distance(physicsC.pos) * s.distance(physicsC.lastPos) < 0 && s.projectionOnSegment(physicsC.pos))
					{
						physicsC.pos.set(s.projection(physicsC.pos));
						physicsC.vel.setAngle(s.normal);
						System.out.println(s.normal);
					}
				}
			}
			boolean ceilingCollided;
		}
	}
}
