package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.fight.ecs.components.InputComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class InputSystem extends IteratingSystem
{
	public InputSystem()
	{
		super(Family.all(InputComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		InputComponent inputC = entity.getComponent(InputComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
		physicsC.acc.set(inputC.inputs.main);
		physicsC.acc.scl(100);
	}
}
