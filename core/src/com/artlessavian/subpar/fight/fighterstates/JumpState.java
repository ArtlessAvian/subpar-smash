package com.artlessavian.subpar.fight.fighterstates;

import com.artlessavian.common.State;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;

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
		fighter.collisionC.ground = null;
		fighter.collisionC.ground2 = null;
		if (fighter.inputC.inputs.main.y == 1)
		{
			fighter.physicsC.vel.add(0, 2000);
		}
		else
		{
			fighter.physicsC.vel.add(0, 1500);
		}

		fighter.spriteC.sprite.setU(0/4f);
		fighter.spriteC.sprite.setU2(1/4f);
		fighter.spriteC.sprite.setV(0/4f);
		fighter.spriteC.sprite.setV2(1/4f);
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

}
