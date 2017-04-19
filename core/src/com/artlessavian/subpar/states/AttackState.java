package com.artlessavian.subpar.states;

import com.artlessavian.subpar.AttackHelper;
import com.artlessavian.subpar.Hitbox;
import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.Move;
import com.artlessavian.subpar.systems.components.*;
import com.badlogic.ashley.core.Entity;

import java.util.LinkedList;

public abstract class AttackState extends State
{
	int timer;
	final Entity entity;

	public LinkedList<Hitbox> hitboxes;

	AttackHelper.AttackType direct;

	public AttackState(Entity e)
	{
		entity = e;
	}

	public abstract void enter(State oldState);

	public void enter(State oldState, AttackHelper.AttackType attackType)
	{
		MovesetComponent moveC = entity.getComponent(MovesetComponent.class);
		InputInterface input = entity.getComponent(ControlComponent.class).inputInterface;
		PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);

		direct = AttackHelper.AttackType.NEUTRAL;
		if (input.vertical == 1) {direct = AttackHelper.AttackType.UP;}
		else if (input.vertical == -1) {direct = AttackHelper.AttackType.DOWN;}
		else if (input.horizontal == physC.facing) {direct = AttackHelper.AttackType.FORWARD;}
		else if (input.horizontal == -physC.facing) {direct = AttackHelper.AttackType.BACK;}

		System.out.println(attackType.name() + " " + direct.name());

		Move move = moveC.helper.getMove(moveC.name, attackType, direct);

		timer = move.time;
		hitboxes = move.data;

		for (Hitbox h : hitboxes)
		{
			h.prepare();
		}
	}

	@Override
	public abstract void reenter(State oldState);

	@Override
	public void exit(State newState)
	{
		HitboxComponent hc = entity.getComponent(HitboxComponent.class);
		hc.hitboxes.clear();
	}

	@Override
	public void doStateChanges()
	{
		StateComponent sc = entity.getComponent(StateComponent.class);

		if (timer <= 0.001)
		{
			sc.undoState();
			return;
		}
	}

	@Override
	public void act(int delta)
	{
		timer -= delta;

		HitboxComponent hc = entity.getComponent(HitboxComponent.class);

		for (Hitbox h : hitboxes)
		{
			h.update(delta, hc.hitboxes);
		}
	}

	@Override
	public abstract void draw(float delta);
}
