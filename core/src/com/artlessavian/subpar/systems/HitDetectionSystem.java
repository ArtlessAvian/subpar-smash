package com.artlessavian.subpar.systems;

import com.artlessavian.subpar.Hitbox;
import com.artlessavian.subpar.states.HitstunState;
import com.artlessavian.subpar.systems.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;

public class HitDetectionSystem extends IntervalSystem
{
	private ImmutableArray<Entity> entitiesWHitbox;
	private ImmutableArray<Entity> entitiesWHurtbox;

	public HitDetectionSystem(float v)
	{
		super(v);
	}

	public void addedToEngine(Engine engine)
	{
		entitiesWHitbox = engine.getEntitiesFor(Family.all(HitboxComponent.class).get());
		entitiesWHurtbox = engine.getEntitiesFor(Family.all(HurtboxComponent.class).get());
	}

	@Override
	protected void updateInterval()
	{
		// Dang this really sucks

		for (Entity hit : entitiesWHitbox)
		{
			HitboxComponent hitC = hit.getComponent(HitboxComponent.class);
			hitC.moveToPosition(hit.getComponent(PhysicsComponent.class).position, hit.getComponent(PhysicsComponent.class).facing < 0);

			for (Entity hurt : entitiesWHurtbox)
			{
				if (!hit.equals(hurt))
				{
					HurtboxComponent hurtC = hurt.getComponent(HurtboxComponent.class);
					hurtC.moveToPosition(hurt.getComponent(PhysicsComponent.class).position, hit.getComponent(PhysicsComponent.class).facing < 0);

					for (Hitbox hitbox : hitC.hitboxes)
					{
						if (!hitbox.gotRekt.contains(hurt))
						{
							Rectangle rekt = hitbox.collision;

							for (Rectangle hurtbox : hurtC.hurtboxes)
							{
								// Why cant you just collide rectangles normally ;___;
								if (rekt.overlaps(hurtbox) || rekt.contains(hurtbox) || hurtbox.contains(rekt))
								{
									applyHitbox(hitbox, hit, hurt);
									hitbox.gotRekt.add(hurt);
									hurtC.moveToOriginal();
									hitC.moveToOriginal();
									return;
								}
							}
						}
					}
					hurtC.moveToOriginal();
				}
			}
			hitC.moveToOriginal();
		}
	}

	private void applyHitbox(Hitbox hitbox, Entity hit, Entity hurt)
	{
		// TODO: make legit
		PhysicsComponent physC = hurt.getComponent(PhysicsComponent.class);
		PercentComponent perC = hurt.getComponent(PercentComponent.class);
		StateComponent sC = hurt.getComponent(StateComponent.class);

		perC.percent += hitbox.damage;

		// Crudely based on Smash Formula
		physC.velocity.y = perC.percent/10f + perC.percent*hitbox.damage/20;
		physC.velocity.y *= 100; // Find a good magic number
		physC.velocity.x = 0;
		physC.velocity.setAngle(hitbox.angle);

		sC.changeState(HitstunState.class);
	}
}
