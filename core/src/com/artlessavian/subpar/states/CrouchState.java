package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class CrouchState extends GroundedActionableState
{
	final Entity entity;

	public CrouchState(Entity e)
	{
		super(e);
		super.friction = 3000;
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.setSprite(2, 0);
	}

	@Override
	public void reenter(State oldState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.setSprite(2, 0);
	}


	@Override
	public void exit(State newState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.setSprite(0, 0);
	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();

		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.vertical == 0)
		{
			sc.changeState(StandState.class);
			return;
		}
	}

	@Override
	public void act(int delta)
	{
		super.act(delta);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.horizontal != 0)
		{
			physC.facing = input.horizontal;
		}
	}

	@Override
	public void draw(float delta)
	{

	}
}
