package com.artlessavian.subpar.fight.ecs.entities;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.*;
import com.artlessavian.subpar.fight.fighterstates.JumpState;
import com.artlessavian.subpar.fight.fighterstates.StandState;
import com.artlessavian.subpar.fight.fighterstates.WalkState;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Fighter extends Entity
{
	SpriteComponent spriteC;
	public PhysicsComponent physicsC;
	public ExtraPhysicsComponent extraPhysicsC;
	public InputComponent inputC;
	private StateComponent stateC;
	private CollisionComponent collisionC;

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
		f.extraPhysicsC.maxXSpeed = 1000;
		f.extraPhysicsC.maxFallSpeed = 1000;
		f.add(f.extraPhysicsC);

		f.stateC = new StateComponent();
		addStates(f, f.stateC);
		f.add(f.stateC);

		f.collisionC = new CollisionComponent(new FighterCollisionBehavior(), 64, 64);
		f.add(f.collisionC);

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

	public static class FighterCollisionBehavior implements CollisionComponent.CollisionBehavior
	{
		@Override
		public void onTouchCeil(Rectangle rectangle, Entity thisEntity, Entity platform)
		{
			Fighter f = (Fighter)thisEntity;

			f.physicsC.vel.y = 0;
			f.physicsC.acc.y = 0;
			f.physicsC.pos.y = rectangle.y - f.collisionC.diamond.topY;
		}

		@Override
		public void onTouchFloor(Rectangle rectangle, Entity thisEntity, Entity platform)
		{
			Fighter f = (Fighter)thisEntity;

			f.physicsC.vel.y = 0;
			f.physicsC.acc.y = 0;

			f.physicsC.pos.y = rectangle.y + rectangle.height - f.collisionC.diamond.bottomY;

			f.extraPhysicsC.ground = platform.getComponent(PlatformComponent.class).rectangle;

			// TODO: Replace with Landing/LandingLag
			f.stateC.machine.gotoState(StandState.class);
		}

		@Override
		public void onTouchLeft(Rectangle rectangle, Entity thisEntity, Entity platform)
		{
			// TODO: Testing, Replace me
			Fighter f = (Fighter)thisEntity;

			f.physicsC.vel.x = 10;
			f.physicsC.acc.x = 0;
			f.physicsC.pos.x = rectangle.x + rectangle.width - f.collisionC.diamond.leftX;
		}

		@Override
		public void onTouchRight(Rectangle rectangle, Entity thisEntity, Entity platform)
		{
			// TODO: Testing, Replace me
			Fighter f = (Fighter)thisEntity;

			f.physicsC.vel.x = -10;
			f.physicsC.acc.x = 0;
			f.physicsC.pos.x = rectangle.x - f.collisionC.diamond.rightX;
		}

		@Override
		public void onEdge(Rectangle rectangle, Entity thisEntity)
		{
			// TODO: Testing, Replace me
			((Fighter)thisEntity).extraPhysicsC.ground = null;
		}
	}
}
