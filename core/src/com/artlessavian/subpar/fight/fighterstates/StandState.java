package com.artlessavian.subpar.fight.fighterstates;

import com.artlessavian.common.State;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;

public class StandState extends State
{
	final Fighter fighter;

	public StandState(Fighter fighter)
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
		fighter.extraPhysicsC.frictionAcc = 200;

		fighter.spriteC.sprite.setU(0/4f);
		fighter.spriteC.sprite.setU2(1/4f);
		fighter.spriteC.sprite.setV(0/4f);
		fighter.spriteC.sprite.setV2(1/4f);
	}

	@Override
	public boolean changeStateMaybe()
	{
		if (fighter.inputC.inputs.main.x != 0)
		{
			sm.gotoState(WalkState.class);
		}
		if (fighter.inputC.inputs.main.y == 1)
		{
			sm.gotoState(JumpSquatState.class);
		}
		return false;
	}

	@Override
	public void doStuff()
	{
//		fighter.physicsC.friction(1);
	}

}
