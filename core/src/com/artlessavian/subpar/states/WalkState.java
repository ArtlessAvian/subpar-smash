package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class WalkState extends GroundedActionableState
{
	final Entity entity;

	public WalkState(Entity e)
	{
		super(e);
		super.friction = 0;
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState);
	}

	@Override
	public void reenter(State oldState)
	{

	}

	@Override
	public void exit(State newState)
	{

	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.horizontal == 0)
		{
			sc.changeState(StandState.class);
			return;
		}
		if (input.horizontal == -physC.facing)
		{
			physC.facing *= -1;
		}
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		if (physC.facing < 0)
		{
			physC.velocity.x = Math.max(physC.velocity.x - 180, -600);
		} else
		{
			physC.velocity.x = Math.min(physC.velocity.x + 180, 600);
		}
	}

	@Override
	public void draw(float delta)
	{

	}
}
