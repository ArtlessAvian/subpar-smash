package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.ExtraPhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.StateComponent;
import com.artlessavian.subpar.fight.fighterstates.StandState;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class CollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;

	@Override
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class, ExtraPhysicsComponent.class).get());
	}

	@Override
	public void update(float deltaTime)
	{
		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

			if (physicsC.pos.y < 0)
			{
				physicsC.vel.y = 0;
				physicsC.acc.y = 0;
				physicsC.pos.y = 0;

				ExtraPhysicsComponent extraPhysicsC = entity.getComponent(ExtraPhysicsComponent.class);
				extraPhysicsC.grounded = true;

				// TODO: Move to a collision behavior object for fighters
				StateComponent stateC = entity.getComponent(StateComponent.class);
				stateC.machine.gotoState(StandState.class);
			}
		}
	}
}
