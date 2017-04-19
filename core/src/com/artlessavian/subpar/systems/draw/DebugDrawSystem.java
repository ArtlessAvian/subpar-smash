package com.artlessavian.subpar.systems.draw;

import com.artlessavian.subpar.Hitbox;
import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.states.State;
import com.artlessavian.subpar.systems.components.HitboxComponent;
import com.artlessavian.subpar.systems.components.HurtboxComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DebugDrawSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	SubparMain main;

	public DebugDrawSystem(SubparMain subparMain)
	{
		main = subparMain;
	}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
		setProcessing(false);
	}

	@Override
	public void update(float rollover)
	{
		main.bitmapFont.setColor(1,1,1,0.5f);

		for (Entity entity : entities)
		{
			PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
			StateComponent sc = entity.getComponent(StateComponent.class);

			if (physC.debugYeehhhhhhhh)
			{
				// Draw movement Rectangle
				main.debugRect(physC.movementRect);

				main.bitmapFont.getData().setScale(3);

				// Display States
				main.bitmapFont.draw(main.spriteBatch, sc.state.getClass().getSimpleName(), physC.position.x + 128, physC.position.y + 300);

				int i = 1;
				for (State state : sc.stateHistory)
				{
					String name = state.getClass().getSimpleName();
					main.bitmapFont.draw(main.spriteBatch, name, physC.position.x + 128, physC.position.y + 300 + 36 * i);
					i++;
				}

				main.bitmapFont.draw(main.spriteBatch, physC.grounded + "", physC.position.x + 128, physC.position.y + 228);

				// Display Strings
				i = 2;
				main.bitmapFont.getData().setScale(2);
				// This would look cooler if monospaced
				for (String string : sc.stringHistory)
				{
					main.bitmapFont.draw(main.spriteBatch, string, main.camera.position.x - main.camera.viewportWidth / 2, main.camera.position.y + main.camera.viewportHeight / 2 - 36 * i);
					i++;
				}
			}

			HitboxComponent hitC = entity.getComponent(HitboxComponent.class);
			if (hitC != null)
			{
				hitC.moveToPosition(physC.position, physC.facing < 0);
				for (Hitbox hitbox : hitC.hitboxes)
				{
					Rectangle r = hitbox.collision;
					main.debugRect(r);
				}
				hitC.moveToOriginal();
			}

			HurtboxComponent hurtC = entity.getComponent(HurtboxComponent.class);
			if (hurtC != null)
			{
				hurtC.moveToPosition(physC.position, physC.facing < 0);
				for (Rectangle r : hurtC.hurtboxes)
				{
					main.debugRect(r);
				}
				hurtC.moveToOriginal();
			}
		}
	}
}
