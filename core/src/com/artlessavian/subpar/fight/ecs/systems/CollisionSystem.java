//package com.artlessavian.subpar.fight.ecs.systems;
//
//import com.artlessavian.common.Polygon;
//import com.artlessavian.subpar.fight.ecs.components.*;
//import com.badlogic.ashley.core.Engine;
//import com.badlogic.ashley.core.Entity;
//import com.badlogic.ashley.core.EntitySystem;
//import com.badlogic.ashley.core.Family;
//import com.badlogic.ashley.utils.ImmutableArray;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//
//import java.util.ArrayList;
//
//public class CollisionSystem extends EntitySystem
//{
//	private ImmutableArray<Entity> entities;
//	private ImmutableArray<Entity> platforms;
//	private ImmutableArray<Entity> polygons;
//
//	private Rectangle dirtyRectangle = new Rectangle();
//
//	@Override
//	public void addedToEngine(Engine engine)
//	{
//		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class, CollisionComponent.class).get());
//		platforms = engine.getEntitiesFor(Family.all(PlatformComponent.class).get());
//		polygons = engine.getEntitiesFor(Family.all(PolygonComponent.class).get());
//	}
//
//	@Override
//	public void update(float deltaTime)
//	{
//		for (Entity entity : entities)
//		{
//			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
//			ExtraPhysicsComponent extraPhysicsC = entity.getComponent(ExtraPhysicsComponent.class);
//			CollisionComponent collisionC = entity.getComponent(CollisionComponent.class);
//
//			for (Entity platform : platforms)
//			{
//				doPlatformCollision(platform, collisionC, entity, extraPhysicsC);
//			}
//
//			for (Entity polygon : polygons)
//			{
//				doPolygonCollision(polygon, collisionC, entity, extraPhysicsC);
//			}
//
//			if (collisionC.movementRect.y < -100)
//			{
//				entity.getComponent(PhysicsComponent.class).pos.set(0, 400);
//			}
//		}
//	}
//
////	private boolean yInside(Vector2 vec, Rectangle rectangle)
////	{
////		return vec.y >= rectangle.y && vec.y <= rectangle.y + rectangle.getHeight();
////	}
////
////	private ArrayList<Vector2> getPossibleCollisions(Polygon p, Rectangle rectangle)
////	{
////		ArrayList<Polygon.Segment> special = new ArrayList<Polygon.Segment>();
////		ArrayList<Vector2> possible = new ArrayList<Vector2>();
////
////		boolean previousInside = yInside(p.edges.get(0).previousPoint, rectangle);
////
////		for (int i = 0; i < p.edges.size(); i++)
////		{
////			Polygon.Segment s = p.edges.get(i);
////			if (yInside(s.nextPoint, rectangle))
////			{
////				possible.add(s.nextPoint);
////				if (!previousInside)
////				{
////					special.add(s);
////					previousInside = true;
////				}
////			}
////			else
////			{
////				if (previousInside)
////				{
////					special.add(s);
////					previousInside = false;
////				}
////			}
////		}
////
////		for (Polygon.Segment s : special)
////		{
////			s.
////		}
////
////
////		return possible;
////	}
////
////	private void doPolygonCollision(Entity polygon, CollisionComponent collisionC, Entity entity, ExtraPhysicsComponent extraPhysicsC)
////	{
////		PolygonComponent polygonC = polygon.getComponent(PolygonComponent.class);
////
////		// floor collision
////		// wall collision
////		collisionC.diamond.fitRectangle(dirtyRectangle);
////		dirtyRectangle.setCenter(collisionC.movementRect.x, collisionC.movementRect.y);
////		dirtyRectangle.width += collisionC.movementRect.width;
////		dirtyRectangle.height += collisionC.movementRect.height;
////
////		Vector2 leftCollide = null;
////		Vector2 rightCollide = null;
////
////		ArrayList<Vector2> vecs = getPossibleCollisions(polygonC.p, dirtyRectangle);
////		for (Vector2 vec : vecs)
////		{
////			if (dirtyRectangle.contains(vec))
////			{
////				if (vec.x < dirtyRectangle.x + dirtyRectangle.width/2)
////				{
////					if (leftCollide == null || leftCollide.x < vec.x)
////					{
////						leftCollide = vec;
////					}
////				}
////				else
////				{
////					if (rightCollide == null || rightCollide.x > vec.x)
////					{
////						rightCollide = vec;
////					}
////				}
////			}
////		}
////
////		if (leftCollide != null)
////		{
////			System.out.println("left");
////		}
////		else if (rightCollide != null)
////		{
////			System.out.println("right");
////		}
////	}
//
//	private void doPolygonCollision(Entity polygon, CollisionComponent collisionC, Entity entity, ExtraPhysicsComponent extraPhysicsC)
//	{
//
//	}
//
//	// TODO: fix argument order
//	public void doPlatformCollision(Entity platform, CollisionComponent collisionC, Entity entity, ExtraPhysicsComponent extraPhysicsC)
//	{
//		PlatformComponent platformC = platform.getComponent(PlatformComponent.class);
//
//		// Left Side
//		dirtyRectangle.set(collisionC.movementRect);
//		dirtyRectangle.x += collisionC.diamond.leftX;
//		dirtyRectangle.y += collisionC.diamond.bottomHorizontalY;
//		if (dirtyRectangle.overlaps(platformC.bounds))
//		{
//			System.out.println("onTouchLeft");
//			collisionC.behavior.onTouchLeft(platformC.bounds, entity, platform);
//			collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
//		}
//		dirtyRectangle.set(collisionC.movementRect);
//		dirtyRectangle.x += collisionC.diamond.leftX;
//		dirtyRectangle.y += collisionC.diamond.topHorizontalY;
//		if (dirtyRectangle.overlaps(platformC.bounds))
//		{
//			System.out.println("onTouchLeft");
//			collisionC.behavior.onTouchLeft(platformC.bounds, entity, platform);
//			collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
//		}
//
//		// Right Side
//		dirtyRectangle.set(collisionC.movementRect);
//		dirtyRectangle.x += collisionC.diamond.rightX;
//		dirtyRectangle.y += collisionC.diamond.bottomHorizontalY;
//		if (dirtyRectangle.overlaps(platformC.bounds))
//		{
//			System.out.println("onTouchRight");
//			collisionC.behavior.onTouchRight(platformC.bounds, entity, platform);
//			collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
//		}
//		dirtyRectangle.set(collisionC.movementRect);
//		dirtyRectangle.x += collisionC.diamond.rightX;
//		dirtyRectangle.y += collisionC.diamond.topHorizontalY;
//		if (dirtyRectangle.overlaps(platformC.bounds))
//		{
//			System.out.println("onTouchRight");
//			collisionC.behavior.onTouchRight(platformC.bounds, entity, platform);
//			collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
//		}
//
//		// Check ground for falling off
//		if (extraPhysicsC != null && extraPhysicsC.ground != null)
//		{
//			dirtyRectangle.set(collisionC.movementRect);
//			dirtyRectangle.y += collisionC.diamond.bottomY - 1;
//			if (!dirtyRectangle.overlaps(extraPhysicsC.ground))
//			{
//				System.out.println("onEdge");
//				collisionC.behavior.onEdge(extraPhysicsC.ground, entity);
//			}
//		}
//
//		// Bottom Side
//		dirtyRectangle.set(collisionC.movementRect);
//		dirtyRectangle.y += collisionC.diamond.bottomY + 1;
//		if (dirtyRectangle.overlaps(platformC.bounds))
//		{
//			System.out.println("onTouchFloor");
//			collisionC.behavior.onTouchFloor(platformC.bounds, entity, platform);
//			collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
//		}
//
//		// Top Side
//		dirtyRectangle.set(collisionC.movementRect);
//		dirtyRectangle.y += collisionC.diamond.topY;
//		if (dirtyRectangle.overlaps(platformC.bounds))
//		{
//			System.out.println("onTouchCeil");
//			collisionC.behavior.onTouchCeil(platformC.bounds, entity, platform);
//			collisionC.behavior.onAnyCollision(platformC.bounds, entity, platform);
//		}
//	}
//}
