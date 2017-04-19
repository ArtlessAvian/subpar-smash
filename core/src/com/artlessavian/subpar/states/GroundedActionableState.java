package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public abstract class GroundedActionableState extends State
{
	final Entity entity;
	float friction = 0;
	boolean letGo;

	public GroundedActionableState(Entity e)
	{
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;
		letGo = input.vertical == 0;
	}


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
			sc.changeState(NormalState.class);
			return;
		}
		if (input.b)
		{
			sc.changeState(SpecialState.class);
			return;
		}

		if (input.vertical == 0) {letGo = true;}

		if (input.vertical == 1 && letGo && sc.state.getClass() != JumpSquatState.class)
		{
			sc.changeState(JumpSquatState.class);
			return;
		}
	}

	@Override
	public void act(int delta)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.velocity.x = Math.signum(physC.velocity.x) * Math.max(Math.signum(physC.velocity.x) * physC.velocity.x - friction * 1/60f, 0);
	}

	@Override
	public abstract void draw(float delta);
}
