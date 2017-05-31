package com.artlessavian.subpar.fight.ecs.entities;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;

public class Fighter extends Entity
{
	SpriteComponent spriteC;
	PhysicsComponent physicsC;

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
		f.physicsC.pos.set(10, 0);
		f.physicsC.vel.set(100, 100);
		f.add(f.physicsC);

		f.spriteC = new SpriteComponent(main.assetManager.get("icon.png", Texture.class));
		f.add(f.spriteC);

		return f;
	}
}
