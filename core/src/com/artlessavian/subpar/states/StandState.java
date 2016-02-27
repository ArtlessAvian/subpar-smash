package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class StandState extends GroundedActionableState
{
	float timeInState;
	final Entity entity;

	public StandState(Entity e)
	{
		super(e);
		super.friction = 12000;
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		timeInState = 0;

		physC.sprite.setAlpha(0.6f);
	}

	@Override
	public void reenter(State oldState)
	{

	}

	@Override
	public void exit(State newState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.setAlpha(1f);
	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.vertical == -1)
		{
			sc.changeState(CrouchState.class);
			return;
		}

		if (input.horizontal != 0)
		{
			if (timeInState < 10 / 60f && sc.stateHistory.getFirst().getClass() == WalkState.class && physC.facing == input.horizontal)
			{
				sc.changeState(DashState.class);
				return;
			} else
			{
				physC.facing = input.horizontal;
				sc.changeState(WalkState.class);
				return;
			}
		}
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		timeInState += delta;
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.velocity.x = physC.facing * Math.max(physC.facing * physC.velocity.x - 12000f * delta, 0);
	}

	@Override
	public void draw(float delta)
	{

	}
}
