package com.artlessavian.subpar.systems;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.states.State;
import com.artlessavian.subpar.systems.components.HitboxComponent;
import com.artlessavian.subpar.systems.components.HurtboxComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DrawSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private Texture map;
	SubparMain main;

	public DrawSystem(Texture map, SubparMain subparMain)
	{
		this.map = map;
		main = subparMain;
	}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
	}

	@Override
	public void update(float deltaTime)
	{
		main.spriteBatch.draw(map, -map.getWidth() / 2f, -map.getHeight() / 2f);

		for (Entity entity : entities)
		{
			PhysicsComponent physC = entity.getComponent(PhysicsComponent.class);
			StateComponent sc = entity.getComponent(StateComponent.class);

			sc.state.draw(deltaTime);

			physC.sprite.setCenter(physC.position.x, physC.position.y + physC.sprite.getHeight() / 2);
			physC.sprite.setFlip(physC.facing < 0, false);
			physC.sprite.draw(main.spriteBatch);
		}
	}
}
