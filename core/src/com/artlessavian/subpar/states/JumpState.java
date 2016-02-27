package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;

public class JumpState extends UngroundedActionableState
{
	final Entity entity;
	boolean thisIsDoubleJump = false;
	boolean jumpHeld = true;

	public JumpState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState) // Short Hop, Double
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		jumpHeld = true;
		physC.grounded = false;

		// This is kinda jank
		if (oldState.getClass() == JumpSquatState.class)
		{
			thisIsDoubleJump = false;
			physC.velocity.y = 2000;
			if (input.vertical == 0)
			{
				// Let go of up in jumpsquat
				physC.velocity.y = 1000;
			}
		} else if (oldState.getClass() == JumpState.class)
		{
			thisIsDoubleJump = true;
			physC.velocity.y = 1700;
			if (physC.facing == -input.horizontal)
			{
				physC.facing *= -1;
			}
		} else
		{
			thisIsDoubleJump = false;
		}

		if (input.horizontal != 0)
		{
			physC.velocity.x = 420 * input.horizontal;
		}
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

	public void doStateChanges()
	{
		super.doStateChanges();

		StateComponent sc = entity.getComponent(StateComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (!thisIsDoubleJump && !jumpHeld && input.vertical == 1)
		{
			sc.changeState(JumpState.class);
			return;
		}
	}

	public void act(float delta)
	{
		super.act(delta);

		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		if (input.vertical != 1)
		{
			jumpHeld = false;
		}
	}

	@Override
	public void draw(float delta)
	{
		if (thisIsDoubleJump)
		{
			PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
			physC.sprite.rotate((float)physC.facing * -1000 * delta);
		}
	}
}
