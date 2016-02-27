package com.artlessavian.subpar.systems;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.states.JumpState;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PhysicsSystem extends IntervalIteratingSystem
{
	final float interval;
	final float timeDilation;

	final float temporaryXBounds = 1920;
	final float temporaryYBounds = 1920;

	public PhysicsSystem(float interval, float timeDialation)
	{
		super(Family.all(PhysicsComponent.class).get(), interval);
		this.interval = interval;
		this.timeDilation = timeDialation;
	}

	@Override
	protected void processEntity(Entity entity)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.position.x += physC.velocity.x * interval * timeDilation;
		physC.position.y += physC.velocity.y * interval * timeDilation;

		physC.movementRect.x = Math.min(physC.position.x, physC.lastPosition.x);
		physC.movementRect.y = Math.min(physC.position.y, physC.lastPosition.y);
		physC.movementRect.width = Math.abs(physC.position.x - physC.lastPosition.x);
		physC.movementRect.height = Math.abs(physC.position.y - physC.lastPosition.y);

		physC.lastPosition.x = physC.position.x;
		physC.lastPosition.y = physC.position.y;

		// Remove this section later, replace with despawn/blastzone
		{
			while (physC.position.x < -temporaryXBounds)
			{
				physC.position.x += temporaryXBounds * 2;
				physC.lastPosition.x += temporaryXBounds * 2;
			}
			while (physC.position.x > temporaryXBounds)
			{
				physC.position.x -= temporaryXBounds * 2;
				physC.lastPosition.x -= temporaryXBounds * 2;
			}

			while (physC.position.y < -temporaryYBounds)
			{
				physC.position.y += temporaryYBounds * 2;
				physC.lastPosition.y += temporaryYBounds * 2;
			}
			while (physC.position.y > temporaryYBounds)
			{
				physC.position.y -= temporaryYBounds * 2;
				physC.lastPosition.y -= temporaryYBounds * 2;
			}

			if (physC.velocity.y < -2560 / interval / 8)
			{
				StateComponent sc = entity.getComponent(StateComponent.class);
				if (sc != null)
				{
					sc.changeState(JumpState.class);
				}
				physC.position.x = 0;
				physC.position.y = 0;
			}
		}

		{
			if (!physC.grounded)
			{
				physC.velocity.y -= 3600 * interval * timeDilation;
			}
		}
	}
}
