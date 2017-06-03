package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.OffsetRectangle;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

public class CollisionComponent implements Component
{
	public static class CollisionDiamond
	{
		// Offsets from center/position
		public float topY;

		public float leftX;
		public float rightX;
		float horiziontalY;

		public float bottomY;
	}

	public interface CollisionBehavior
	{
		void onTouchCeil(Rectangle rectangle, Entity thisEntity, Entity platform);

		void onTouchFloor(Rectangle rectangle, Entity thisEntity, Entity platform);

		void onTouchLeft(Rectangle rectangle, Entity thisEntity, Entity platform);

		void onTouchRight(Rectangle rectangle, Entity thisEntity, Entity platform);

		void onEdge(Rectangle rectangle, Entity thisEntity);
	}

	// should form a plus sign, kinda
	public Rectangle movementRect;

	public CollisionDiamond diamond;
	public CollisionBehavior behavior;

	public CollisionComponent(CollisionBehavior behavior, float height, float width)
	{
		this.behavior = behavior;
		this.movementRect = new Rectangle(0, 0, 0, 0);

		this.diamond = new CollisionDiamond();
		diamond.topY = height/2f;
		diamond.bottomY = -height/2f;
		diamond.leftX = -width/2f;
		diamond.rightX = width/2f;
	}
}
