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

public class DrawSystem extends EntitySystem
{
	SubparMain main;
	FightScreen game;

	ImmutableArray<Entity> entities;

	Vector2 camPos;
	OrthographicCamera cam;

	public float screenShakeTime = 0;
	public float screenShakeAmount = 0;
	public float screenShakeMultiplier = 1f;

	public DrawSystem(SubparMain main, FightScreen game)
	{
		this.main = main;
		this.game = game;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		this.cam = new OrthographicCamera(2560, 1440);
		cam.update();
		camPos = new Vector2();

		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
	}

	public void resize()
	{
		cam.viewportWidth = 1440 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
	}

	public void shakeScreen(float time, float strength)
	{
		screenShakeTime = time;
		screenShakeAmount = strength;
	}

	@Override
	public void update(float rollover)
	{
		shakeScreen();
		main.spriteBatch.setProjectionMatrix(cam.combined);
//		game.stage.drawBackground(main.batch);

		game.stage.drawBackground(main, game);

		for (Entity entity : entities)
		{
			SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

			moveToPosition(spriteC, physicsC);
			spriteC.sprite.draw(main.spriteBatch);

//			main.debugLine(-300, 0, 300, 0);
		}

//		main.debugLine(0, 0, (float)Math.random() * 100, (float)Math.random() * 100);
	}

	public void shakeScreen()
	{
		cam.position.x = camPos.x;
		cam.position.y = camPos.y;
		if (screenShakeTime > 0)
		{
			cam.translate(
				(float)(Math.random() * 2 - 1) * screenShakeAmount * screenShakeMultiplier,
				(float)(Math.random() * 2 - 1) * screenShakeAmount * screenShakeMultiplier
			);
			screenShakeTime--;
		}
		cam.update();
	}

	public void moveToPosition(SpriteComponent spriteC, PhysicsComponent physicsC)
	{
		spriteC.sprite.setCenter(physicsC.pos.x, physicsC.pos.y);
	}
}