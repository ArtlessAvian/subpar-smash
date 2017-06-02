package com.artlessavian.subpar.fight.ecs.entities;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.*;
import com.artlessavian.subpar.fight.fighterstates.JumpState;
import com.artlessavian.subpar.fight.fighterstates.StandState;
import com.artlessavian.subpar.fight.fighterstates.WalkState;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;

public class Fighter extends Entity
{
	SpriteComponent spriteC;
	public PhysicsComponent physicsC;
	ExtraPhysicsComponent extraPhysicsC;
	public InputComponent inputC;
	private StateComponent stateC;

	public Fighter(SubparMain main)
	{
		decorateEntity(main, this);
	}

	// Replace with a factory if this gets too hairy
	public static Entity decorateEntity(SubparMain main, Entity e)
	{
		Fighter f = (Fighter)e;

		// cut this if replacing with factory
		f.physicsC = new PhysicsComponent();
		f.physicsC.pos.set(0, 10);
		f.physicsC.vel.set(0, 0);
		f.add(f.physicsC);

		f.extraPhysicsC = new ExtraPhysicsComponent();
		f.extraPhysicsC.maxXSpeed = 300;
		f.extraPhysicsC.maxFallSpeed = 300;
		f.add(f.extraPhysicsC);

		f.stateC = new StateComponent();
		addStates(f, f.stateC);
		f.add(f.stateC);

		f.inputC = new InputComponent();
		f.add(f.inputC);

		f.spriteC = new SpriteComponent(main.assetManager.get("Debug/Grid.png", Texture.class));
		f.add(f.spriteC);

		return f;
	}

	public static void addStates(Fighter f, StateComponent stateC)
	{
		stateC.machine.addState(new StandState(f));
		stateC.machine.addState(new WalkState(f));
		stateC.machine.addState(new JumpState(f));
		stateC.machine.gotoState(StandState.class);
	}
}
