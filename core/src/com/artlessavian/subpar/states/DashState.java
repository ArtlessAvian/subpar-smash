package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class DashState extends GroundedActionableState
{
	float timer;

	final Entity entity;

	public DashState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		timer = 10 / 60f;

		physC.facing = input.horizontal;

		physC.sprite.setRotation(-20 * physC.facing);
		physC.setSprite(1, 0);
	}

	@Override
	public void reenter(State oldState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		physC.facing = input.horizontal;

		physC.sprite.setRotation(-20 * physC.facing);
		physC.setSprite(1, 0);
	}

	@Override
	public void exit(State newState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.setRotation(0);
		physC.setSprite(0, 0);
	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.horizontal == -physC.facing)
		{
			enter(this);
		}

		if (input.vertical == -1)
		{
			sc.changeState(CrouchState.class);
			return;
		}

		if (timer <= 0.001)
		{
			if (input.horizontal == 0)
			{
				sc.changeState(StandState.class);
				return;
			} else
			{
				sc.changeState(RunState.class);
				return;
			}
		}
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);

		timer -= delta;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		if (physC.facing < 0)
		{
			physC.velocity.x = -1200;
		} else
		{
			physC.velocity.x = 1200;
		}
	}

	@Override
	public void draw(float delta)
	{

	}
}
