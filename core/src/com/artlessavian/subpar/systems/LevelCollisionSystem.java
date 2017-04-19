package com.artlessavian.subpar.systems;

import com.artlessavian.subpar.states.CrouchState;
import com.artlessavian.subpar.states.EmptyLandState;
import com.artlessavian.subpar.states.FallThroughState;
import com.artlessavian.subpar.states.JumpState;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.PlatformComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.artlessavian.subpar.systems.entities.Platform;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

public class LevelCollisionSystem extends IteratingSystem
{
	ImmutableArray<Entity> platforms;

	public LevelCollisionSystem()
	{
		super(Family.all(PhysicsComponent.class).get());
	}
	
	@Override
	public void addedToEngine(Engine engine)
	{
		super.addedToEngine(engine);
		// Temporary
		{
			engine.addEntity(new Platform(0, 0, 2000, 20, true));
			engine.addEntity(new Platform(600, 400, 400, 20, false));
			engine.addEntity(new Platform(-600, 400, 400, 20, false));
			engine.addEntity(new Platform(0, 800, 400, 20, false));
		}
		
		platforms = engine.getEntitiesFor(Family.all(PlatformComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		StateComponent sc = entity.getComponent(StateComponent.class); // Possibly null!!!!

		if (physC.grounded)
		{
			// if no floor
			if (physC.position.x < physC.lastPlatform.collision.x || physC.position.x > physC.lastPlatform.collision.x + physC.lastPlatform.collision.width)
			{
				physC.grounded = false;

				if (sc != null)
				{
					sc.changeState(JumpState.class);
				}
			}

			if (sc != null && sc.state.getClass() == CrouchState.class && !physC.lastPlatform.isSoft)
			{
				physC.grounded = false;
				sc.changeState(FallThroughState.class);
			}
		} else
		{
			// 1) Collide Sides
			// 2) Collide Feet (return;)
			// 3) Collide Head

			for (Entity platEntity : platforms)
			{
				PlatformComponent platform = platEntity.getComponent(PlatformComponent.class);
				if (platform.collision.overlaps(physC.movementRect) || platform.collision.contains(physC.movementRect) || physC.movementRect.contains(platform.collision))
				{
					if (physC.velocity.y < 0) // Temporary
					{
						if (sc == null || sc.state.getClass() != FallThroughState.class) // Dang, this is a weird statement
						{
							collideFeet(entity, platform);
							return;
						}
					}
				}

				physC.movementRect.y += physC.sprite.getHeight();
				if (platform.isSoft && platform.collision.overlaps(physC.movementRect))
				{
					collideHead(entity, platform);
				}
				physC.movementRect.y -= physC.sprite.getHeight();
			}
		}
	}

	private void collideFeet(Entity entity, PlatformComponent platform)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.position.y = platform.collision.y + platform.collision.height;
		physC.velocity.y = 0;
		physC.grounded = true;
		physC.lastPlatform = platform;

		StateComponent sc = entity.getComponent(StateComponent.class);
		if (sc != null)
		{
			sc.changeState(EmptyLandState.class);
		}
	}

	private void collideHead(Entity entity, PlatformComponent platform)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.position.y = platform.collision.y - physC.sprite.getHeight();
		physC.velocity.y *= -0.2;
	}
}
