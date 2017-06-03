package com.artlessavian.subpar.fight.fighterstates;

import com.artlessavian.common.State;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class JumpState extends State
{
	Fighter fighter;

	public JumpState(Fighter fighter)
	{
		this.fighter = fighter;
	}

	@Override
	public void exit()
	{

	}

	@Override
	public void enter()
	{
		fighter.extraPhysicsC.ground = null;
		if (Math.random() < 0.5)
		{
			fighter.physicsC.vel.add(0, 2000);
		}
		else
		{
			fighter.physicsC.vel.add(0, 1500);
		}
	}

	@Override
	public boolean changeStateMaybe()
	{
		return false;
	}

	@Override
	public void doStuff()
	{
		fighter.physicsC.acc.add(fighter.inputC.inputs.main.x * 10 * 60, 0);
	}

	@Override
	public void editSprite(Sprite sprite)
	{

	}
}
