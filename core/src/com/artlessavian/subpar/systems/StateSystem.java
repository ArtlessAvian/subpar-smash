package com.artlessavian.subpar.systems;

import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;

public class StateSystem extends IntervalIteratingSystem
{
	float interval;

	public StateSystem(float interval)
	{
		super(Family.all(StateComponent.class).get(), interval);
		this.interval = interval;
	}

	@Override
	protected void processEntity(Entity entity)
	{
		StateComponent sc = entity.getComponent(StateComponent.class);

		sc.state.doStateChanges();
		sc.state.act(interval);
	}
}
