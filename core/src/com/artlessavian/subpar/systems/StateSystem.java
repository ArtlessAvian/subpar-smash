package com.artlessavian.subpar.systems;

import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;

public class StateSystem extends IteratingSystem
{
	float interval;

	public StateSystem()
	{
		super(Family.all(StateComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float delta)
	{
		StateComponent sc = entity.getComponent(StateComponent.class);

		sc.state.doStateChanges();
		sc.state.act(1);
	}
}
