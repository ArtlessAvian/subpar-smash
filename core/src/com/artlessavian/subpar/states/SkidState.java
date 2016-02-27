package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class SkidState extends GroundedActionableState
{
	float timer;

	final Entity entity;

	public SkidState(Entity e)
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

		timer = 30 / 60f;

		physC.facing *= -1;

		physC.sprite.setRotation(physC.facing * -10);
		physC.setSprite(0, 0);
	}

	@Override
	public void reenter(State oldState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.setRotation(physC.facing * -10);
		physC.setSprite(0, 0);
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

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.vertical == 1)
		{
			sc.changeState(JumpSquatState.class);
			return;
		}
		if (timer <= 0.001)
		{
			if (input.horizontal == physC.facing)
			{
				sc.changeState(RunState.class);
				return;
			} else
			{
				sc.changeState(StandState.class);
				return;
			}
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
