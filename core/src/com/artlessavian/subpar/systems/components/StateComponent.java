package com.artlessavian.subpar.systems.components;

import com.artlessavian.subpar.states.State;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.HashMap;
import java.util.LinkedList;

public class StateComponent implements Component
{
	Entity owner;

	public State state;
	public LinkedList<State> stateHistory;
	HashMap<Class<? extends State>, State> stateHash;

	// Cool and useless!
	public LinkedList<String> stringHistory;

	public StateComponent(Entity entity)
	{
		owner = entity;

		state = State.emptyState;
		stateHistory = new LinkedList<State>();
		stateHistory.push(State.emptyState);

		stringHistory = new LinkedList<String>();
		stringHistory.add("");

		stateHash = new HashMap<Class<? extends State>, State>();
	}

	public void addState(State state)
	{
		stateHash.put(state.getClass(), state);
	}

	public void changeState(Class<? extends State> c)
	{
		// History Stuff
		stateHistory.push(state);
		if (stateHistory.size() > 5)
		{
			stateHistory.removeLast();
		}

		// Get new State
		State newState = stateHash.get(c);
		if (newState == null)
		{
			System.err.println("Please add " + c.getSimpleName() + " to the Fighter class. Thank you!");
			try
			{
				stateHash.put(c, c.getConstructor(Entity.class).newInstance(owner));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			// Welp
			newState = stateHash.get(c);
		}

		log(newState);

		// Entering and switching active State
		state.exit(newState);
		newState.enter(state);
		state = newState;
	}

	public void undoState()
	{
		// Get new State
		State lastState = stateHistory.getFirst();
		if (lastState == null)
		{
			// In case I choose to not reinsert
			lastState = State.emptyState;
			System.err.print("More than 5 state undos!");
		}

		// History (Do I want to reinsert? :/)
		stateHistory.push(state);
		if (stateHistory.size() > 5)
		{
			stateHistory.removeLast();
		}

		log(lastState);

		// Entering and switching active State
		// I dunno if I want enter/exit to be called
		state.exit(lastState);
		lastState.reenter(state);
		state = lastState;
	}

	private void log(State state)
	{
		if (stringHistory.size() > 5)
		{
			stringHistory.removeLast();
		}

		String out = state.getClass().getSimpleName();
		if (out.equals("StandState"))
		{
			System.out.println(stringHistory.getFirst());
			stringHistory.push("");
		} else
		{
			for (int i = 15 - out.length(); i >= 0; i--)
			{
				out += " ";
			}
			stringHistory.push(stringHistory.pop() + " > " + out);
		}
	}
}
