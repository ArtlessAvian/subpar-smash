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
		fighter.physicsC.vel.add(0, 1000);
	}

	@Override
	public boolean changeStateMaybe()
	{
		return false;
	}

	@Override
	public void doStuff()
	{

	}

	@Override
	public void editSprite(Sprite sprite)
	{

	}
}
