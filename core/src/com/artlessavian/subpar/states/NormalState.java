package com.artlessavian.subpar.states;

import com.artlessavian.subpar.AttackHelper;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;

public class NormalState extends AttackState
{
	float timer;
	final Entity entity;

	public NormalState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState, AttackHelper.AttackType.NORMAL);
		timer = super.timer;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		if (super.direct == AttackHelper.AttackType.BACK)
		{
			physC.facing *= -1;
		}

		physC.setSprite(1, 0);
		physC.sprite.setRotation(physC.facing * -45);
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
		physC.sprite.setRotation(0);
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
	}

	@Override
	public void draw(float delta)
	{

	}
}
