package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class RunState extends GroundedActionableState
{
	final Entity entity;

	public RunState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.setRotation(physC.facing * -40);
		physC.setSprite(1, 0);
	}

	@Override
	public void reenter(State oldState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.setRotation(physC.facing * -40);
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

		if (input.vertical == -1)
		{
			sc.changeState(CrouchState.class);
			return;
		} else if (input.horizontal != physC.facing)
		{
			sc.changeState(SkidState.class);
			return;
		}
	}

	@Override
	public void act(int delta)
	{
		super.act(delta);

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		if (physC.facing < 0)
		{
			physC.velocity.x = Math.max(physC.velocity.x - 420, -1200);
		} else
		{
			physC.velocity.x = Math.min(physC.velocity.x + 420, 1200);
		}
	}

	@Override
	public void draw(float delta)
	{

	}
}
