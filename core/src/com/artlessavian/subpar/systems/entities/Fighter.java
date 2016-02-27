package com.artlessavian.subpar.systems.entities;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.states.*;
import com.artlessavian.subpar.systems.components.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Fighter extends Entity
{
	StateComponent sc;

	public Fighter(Vector2 startPos, InputInterface playerInput, boolean debugger, SubparMain main, String name)
	{
		this.add(new ControlComponent(playerInput));

		PositionComponent posC = new PositionComponent(startPos);
		this.add(posC);
		this.add(new PhysicsComponent(debugger, posC, false, main.assetManager.get("Prototype/" + name + ".png", Texture.class)));
		this.add(new HurtboxComponent());
		this.add(new HitboxComponent());
		this.add(new MovesetComponent(name));
		this.add(new PercentComponent());

		sc = new StateComponent(this);
		this.add(sc);
		sc.addState(new StandState(this));
		sc.addState(new WalkState(this));
		sc.addState(new CrouchState(this));
		sc.addState(new JumpSquatState(this));
		sc.addState(new EmptyLandState(this));
		sc.addState(new DashState(this));
		sc.addState(new RunState(this));
		sc.addState(new SkidState(this));

		sc.addState(new FallThroughState(this));
		sc.addState(new JumpState(this));
		sc.addState(new HelplessState(this));

		sc.addState(new HitstunState(this));
		sc.addState(new LaunchState(this));

		//sc.addState(new SmashState(this));
		sc.addState(new NormalState(this));
		sc.addState(new AerialState(this));
		sc.addState(new SpecialState(this));

		sc.changeState(StandState.class);
	}
}
