package com.artlessavian.subpar.fight.fighterstates;

import com.artlessavian.common.State;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;

public class JumpSquatState extends State
{
	Fighter fighter;

	public JumpSquatState(Fighter fighter)
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
		fighter.spriteC.sprite.setU(2/4f);
		fighter.spriteC.sprite.setU2(3/4f);
		fighter.spriteC.sprite.setV(0/4f);
		fighter.spriteC.sprite.setV2(1/4f);
	}

	@Override
	public boolean changeStateMaybe()
	{
		if (getTimeInState() > 6)
		{
			sm.gotoState(JumpState.class);
		}
		return false;
	}

	@Override
	public void doStuff()
	{

	}

}
