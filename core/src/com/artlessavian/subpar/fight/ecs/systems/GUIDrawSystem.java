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

public class GUIDrawSystem extends EntitySystem
{
	SubparMain main;
	FightScreen game;
	DrawSystem drawSystem;

	ImmutableArray<Entity> entities;

	public GUIDrawSystem(SubparMain main, FightScreen game, DrawSystem drawSystem)
	{
		this.main = main;
		this.game = game;
		this.drawSystem = drawSystem;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
	}

	@Override
	public void update(float rollover)
	{
		for (Entity entity : entities)
		{

		}
	}
}
