package com.artlessavian.subpar.states;

import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;

public class HelplessState extends State
{
	final Entity entity;

	public HelplessState(Entity e)
	{
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.grounded = false;

		physC.setSprite(2, 0);
	}

	@Override
	public void reenter(State oldState)
	{
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
	}

	@Override
	public void act(float delta)
	{
	}

	@Override
	public void draw(float delta)
	{
	}
}
