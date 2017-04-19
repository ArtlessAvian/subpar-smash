package com.artlessavian.subpar.systems.draw;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;

public class CameraSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	SubparMain main;

	final float lazyWeightX = 100;
	final float lazyWeightY = 100;


	public CameraSystem(SubparMain subparMain)
	{
		main = subparMain;
	}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
		setProcessing(false);
	}

	public void update(float rollover)
	{
//		float lowestX = 666666666;
//		float lowestY = 666666666;

		main.camera.position.x *= lazyWeightX;
		main.camera.position.y *= lazyWeightY;

		for (Entity e : entities)
		{
			PhysicsComponent physC = e.getComponent(PhysicsComponent.class);
			main.camera.position.x += physC.position.x;
			main.camera.position.y += physC.position.y;

//			lowestX = Math.min(physC.position.x, lowestX);
//			lowestY = Math.min(physC.position.y, lowestY);
		}

		main.camera.position.x /= (entities.size() + lazyWeightX);
		main.camera.position.y /= (entities.size() + lazyWeightY);

		// This doesnt work

//		lowestX = main.camera.position.x - lowestX;
//		lowestY = main.camera.position.y - lowestY;
//
//		if (lowestX < lowestY * main.screenRatio)
//		{
//			// Y defines bounds
//			main.camera.zoom = (lowestY*3)/2880;
//		} else
//		{
//			// X defines bounds
//			main.camera.zoom = (lowestX*3)/2880;
//		}

		main.camera.update();

		main.spriteBatch.setProjectionMatrix(main.camera.combined);
	}
}
