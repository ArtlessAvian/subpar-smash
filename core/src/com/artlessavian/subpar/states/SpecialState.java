package com.artlessavian.subpar.states;

import com.artlessavian.subpar.AttackHelper;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.badlogic.ashley.core.Entity;

public class SpecialState extends AttackState
{
	int timer;
	final Entity entity;

	public SpecialState(Entity e)
	{
		super(e);
		entity = e;
	}

	@Override
	public void enter(State oldState)
	{
		super.enter(oldState, AttackHelper.AttackType.SPECIAL);
		timer = super.timer;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		if (super.direct == AttackHelper.AttackType.BACK)
		{
			physC.facing *= -1;
		}

		physC.setSprite(1, 0);
		physC.sprite.setRotation(180);
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

		physC.velocity.x = 0;

		physC.setSprite(0, 0);
		physC.sprite.setRotation(0);
	}

	@Override
	public void doStateChanges()
	{
		super.doStateChanges();
	}

	@Override
	public void act(int delta)
	{
		super.act(delta);
		timer -= delta;

		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		if (timer < 5)
		{
			physC.velocity.x = physC.facing * 10000;
		}
		physC.velocity.y = 0;
	}

	@Override
	public void draw(float delta)
	{
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		physC.sprite.rotate(91);
	}
}
