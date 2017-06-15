package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.Polygon;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

public class CollisionComponent implements Component
{
	public static class CollisionThing
	{
		// Offsets from center/position
		public float topY;
		public float topHorizontalY;
		public float leftX;
		public float rightX;
		public float bottomHorizontalY;
		public float bottomY;

		public Rectangle fitRectangle(Rectangle r)
		{
			r.x = leftX;
			r.y = bottomY;
			r.width = rightX - leftX;
			r.height = topY - bottomY;
			return r;
		}
	}

	public abstract static class CollisionBehavior
	{
		public abstract void onTouchCeil(Polygon.Segment segment, Entity thisEntity, Entity platform);

		public abstract void onTouchFloor(Polygon.Segment segment, Entity thisEntity, Entity platform);

		public abstract void onTouchLeft(Polygon.Segment segment, Entity thisEntity, Entity platform);

		public abstract void onTouchRight(Polygon.Segment segment, Entity thisEntity, Entity platform);

		public abstract void onEdge(Polygon.Segment segment, Entity thisEntity);

		public abstract void onAnyCollision(Polygon.Segment segment, Entity thisEntity, Entity platform);
	}

	public Polygon.Segment ground;

	public CollisionThing diamond;
	public CollisionBehavior behavior;

	public CollisionComponent(CollisionBehavior behavior, float height, float width)
	{
		this.behavior = behavior;

		this.diamond = new CollisionThing();
		diamond.topY = height/2f;
		diamond.topHorizontalY = height/3f;
		diamond.bottomHorizontalY = -height/3f;
		diamond.bottomY = -height/2f;
		diamond.leftX = -width/2f;
		diamond.rightX = width/2f;
	}
}
