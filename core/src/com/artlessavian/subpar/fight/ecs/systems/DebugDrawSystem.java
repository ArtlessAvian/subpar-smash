package com.artlessavian.subpar.fight.ecs.systems;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.FightScreen;
import com.artlessavian.subpar.fight.ecs.components.PhysicsComponent;
import com.artlessavian.subpar.fight.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class DebugDrawSystem extends EntitySystem
{
	SubparMain main;
	FightScreen game;
	DrawSystem drawSystem;

	ImmutableArray<Entity> entities;

	public DebugDrawSystem(SubparMain main, FightScreen game, DrawSystem drawSystem)
	{
		this.main = main;
		this.game = game;
		this.drawSystem = drawSystem;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		entities = engine.getEntities();
	}

	@Override
	public void update(float rollover)
	{
		main.spriteBatch.setProjectionMatrix(main.screenSpace.combined);
		int i = 0;
		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
			if (physicsC != null)
			{
				main.bitmapFont.draw(main.spriteBatch, physicsC.pos.x + "", 6 + 100 * (0 + (2 * i)), 18 + 12 * 0);
				main.bitmapFont.draw(main.spriteBatch, physicsC.pos.y + "", 6 + 100 * (1 + (2 * i)), 18 + 12 * 0);
				main.bitmapFont.draw(main.spriteBatch, physicsC.vel.x + "", 6 + 100 * (0 + (2 * i)), 18 + 12 * 1);
				main.bitmapFont.draw(main.spriteBatch, physicsC.vel.y + "", 6 + 100 * (1 + (2 * i)), 18 + 12 * 1);
				main.bitmapFont.draw(main.spriteBatch, physicsC.acc.x + "", 6 + 100 * (0 + (2 * i)), 18 + 12 * 2);
				main.bitmapFont.draw(main.spriteBatch, physicsC.acc.y + "", 6 + 100 * (1 + (2 * i)), 18 + 12 * 2);
			}
			i++;
		}
	}
}
