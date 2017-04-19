package com.artlessavian.subpar.states;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.systems.components.ControlComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class LaunchState extends State
{
	int timer;

	final Entity entity;

	public LaunchState(Entity e)
	{
		entity = e;
	}

	Vector2 diHelper = new Vector2(0,0);

	@Override
	public void enter(State oldState)
	{
		timer = 30;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;

		diHelper.x = input.horizontal;
		diHelper.y = input.vertical;

		float deltaAngle = diHelper.angle() - physC.velocity.angle();
		if (deltaAngle > 180) {deltaAngle -= 360;}

		physC.velocity.setAngle(physC.velocity.angle() + Math.signum(deltaAngle) * (Math.min(Math.signum(deltaAngle) * deltaAngle,15)));

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

		physC.sprite.setRotation(0);
	}

	@Override
	public void doStateChanges()
	{
		StateComponent sc = entity.getComponent(StateComponent.class);

		if (timer <= 0)
		{
			sc.changeState(JumpState.class);
		}
	}

	@Override
	public void act(int delta)
	{
		timer -= delta;
	}

	@Override
	public void draw(float delta)
	{

	}
}
