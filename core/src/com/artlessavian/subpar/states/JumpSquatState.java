package com.artlessavian.subpar.states;

import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class JumpSquatState extends GroundedActionableState
{
	float timer;
	final Entity entity;

	public JumpSquatState(Entity e)
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

		timer = 5 / 60f;

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

		if (timer <= 0.001)
		{
			sc.changeState(JumpState.class);
			return;
		}
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		timer -= delta;
	}

	@Override
	public void draw(float delta)
	{

	}
}
