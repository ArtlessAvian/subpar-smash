package com.artlessavian.subpar.states;

/**
 * I have no idea what I am doing with these states.
 * There's a lot of redundancies, and I am not a fan of that.
 * However, abstraction makes things too complicated. I don't know how to structure around abstraction for this.
 * There could be a grounded state, but not all states have equal friction, for example.
 * I also feel if I wait too long to abstract these, it will get very unworkable. :C
 */

public abstract class State
{
	public static EmptyState emptyState = new EmptyState();

	public abstract void enter(State oldState);
	// Initialize fields
	// Do Physics stuff
	// Address Inputs
	// Do Sprite stuff

	public abstract void reenter(State oldState);

	public abstract void exit(State newState);
	// Do Physics Stuff
	// Undo Sprite stuff

	public abstract void doStateChanges();
	// Change State if necessary

	public abstract void act(float delta);
	// Do Physics stuff

	public abstract void draw(float delta);
}
