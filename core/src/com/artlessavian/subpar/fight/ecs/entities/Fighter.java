package com.artlessavian.subpar.fight.ecs.entities;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.ExtraPhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.InputComponent;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;

public class Fighter extends Entity
{
	SpriteComponent spriteC;
	PhysicsComponent physicsC;
	ExtraPhysicsComponent extraPhysicsC;
	InputComponent inputC;

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
		f.physicsC.vel.set(50, 100);
		f.add(f.physicsC);

		f.extraPhysicsC = new ExtraPhysicsComponent();
		f.extraPhysicsC.maxXSpeed = 40;
		f.extraPhysicsC.maxFallSpeed = 40;
		f.add(f.extraPhysicsC);

		f.inputC = new InputComponent();
		f.add(f.inputC);

		f.spriteC = new SpriteComponent(main.assetManager.get("icon.png", Texture.class));
		f.add(f.spriteC);

		return f;
	}
}
