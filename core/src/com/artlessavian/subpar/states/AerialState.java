package com.artlessavian.subpar.states;

import com.artlessavian.subpar.AttackHelper;
import com.artlessavian.subpar.Hitbox;
import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;

import java.util.LinkedList;

public class AerialState extends AttackState
{
	float timer;
	final Entity entity;

	public AerialState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState, AttackHelper.AttackType.AIR);
		timer = super.timer;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.setSprite(1, 0);
	}

	@Override
	public void reenter(State oldState)
	{

	}


	@Override
	public void exit(State newState)
	{
		super.exit(newState);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		physC.setSprite(0, 0);
	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		timer -= delta;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.horizontal != 0)
		{
			physC.velocity.x += input.horizontal * 60;
		}

		physC.velocity.x = Math.min(Math.max(physC.velocity.x, -600), 600);
	}

	@Override
	public void draw(float delta)
	{

	}
}
