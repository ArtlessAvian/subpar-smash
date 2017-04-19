package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public abstract class UngroundedActionableState extends State
{
	final Entity entity;
	float horizontalMobility = 600;

	public UngroundedActionableState(Entity e)
	{
		entity = e;
	}

	@Override
	public abstract void enter(State oldState);

	@Override
	public abstract void reenter(State oldState);

	@Override
	public abstract void exit(State newState);

	@Override
	public void doStateChanges()
	{
		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.a)
		{
			sc.changeState(AerialState.class);
			return;
		}
		if (input.b)
		{
			sc.changeState(SpecialState.class);
			return;
		}
	}

	@Override
	public void act(int delta)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.horizontal != 0)
		{
			physC.velocity.x += input.horizontal * 60;
		}

		physC.velocity.x = Math.min(Math.max(physC.velocity.x, -horizontalMobility), horizontalMobility);
	}


	@Override
	public abstract void draw(float delta);
}
