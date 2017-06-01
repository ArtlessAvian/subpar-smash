package com.artlessavian.subpar;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * One object per state, reused by the same state machine
 */
public abstract class State
{
	long lastEnter;
	long lastExit;
	public StateMachine sm;

	public abstract void exit();

	public abstract void enter();

	public abstract boolean changeStateMaybe();

	public abstract void doStuff();

	public abstract void editSprite(Sprite sprite);

	public long getTimeInState()
	{
		return sm.runs - lastEnter;
	}

	public static class NullState extends State
	{
		public static NullState singleton = new NullState();

		@Override
		public void exit()
		{

		}

		@Override
		public void enter()
		{

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
}