package com.artlessavian.subpar.states;

import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class FallThroughState extends UngroundedActionableState
{
	int timer;
	final Entity entity;

	public FallThroughState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		timer = 15;
	}

	@Override
	public void reenter(State oldState)
	{

	}

	@Override
	public void exit(State newState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.setRotation(0);
	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();

		StateComponent sc = entity.getComponent(StateComponent.class);

		if (timer <= 0.001)
		{
			sc.changeState(JumpState.class);
			return;
		}
	}

	@Override
	public void act(int delta)
	{
		super.act(delta);

		timer -= delta;
	}

	@Override
	public void draw(float delta)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		physC.sprite.rotate(1440 * delta);
	}
}
