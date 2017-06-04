package com.artlessavian.common;

/**
 * One object per state, reused by the same state machine
 */
public abstract class State
{
	long lastEnter;
	long lastExit;
	public com.artlessavian.common.StateMachine sm;

	public abstract void exit();

	public abstract void enter();

	public abstract boolean changeStateMaybe();

	public abstract void doStuff();

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

	}
}