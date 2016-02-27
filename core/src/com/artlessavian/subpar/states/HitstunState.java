package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class HitstunState extends State
{
	float noDecrementTimer;
	float timer;

	final Entity entity;

	Vector2 heldVelocity;

	public HitstunState(Entity e)
	{
		entity = e;
		heldVelocity = new Vector2();
	}

	@Override
	public void enter(State oldState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		timer = 1 / 60f * physC.velocity.len()/120;
		noDecrementTimer = timer;

		if (oldState.getClass() != HitstunState.class)
		{
			heldVelocity.x = physC.velocity.x;
			heldVelocity.y = physC.velocity.y;
		}

		physC.velocity.x = 0;
		physC.velocity.y = 0;

		physC.grounded = false;

		physC.sprite.setRotation(180);
	}

	@Override
	public void reenter(State oldState)
	{

	}

	@Override
	public void exit(State newState)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.velocity.x = heldVelocity.x;
		physC.velocity.y = heldVelocity.y;

		physC.sprite.setRotation(0);
	}

	@Override
	public void doStateChanges()
	{
		StateComponent sc = entity.getComponent(StateComponent.class);

		if (timer <= 0.001)
		{
			sc.changeState(LaunchState.class);
		}
	}

	@Override
	public void act(float delta)
	{
		timer -= delta;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		physC.velocity.x = 0;
		physC.velocity.y = 0;

		// TODO: Add SDI
		// Right now its SDI every frame, because why not
		physC.position.x += delta * 200 * input.horizontal;
		physC.position.y += delta * 200 * input.vertical;
	}

	@Override
	public void draw(float delta)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		physC.sprite.setRotation(timer/noDecrementTimer * 360f + 180);
	}
}
