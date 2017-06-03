package com.artlessavian.subpar.fight.fighterstates;

import com.artlessavian.common.State;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
			sm.gotoState(JumpState.class);
		}
		return false;
	}

	@Override
	public void doStuff()
	{
//		fighter.physicsC.friction(1);
	}

	@Override
	public void editSprite(Sprite sprite)
	{

	}
}
