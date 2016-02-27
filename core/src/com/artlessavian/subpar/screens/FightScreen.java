package com.artlessavian.subpar.screens;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.systems.*;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.entities.Fighter;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class FightScreen implements Screen
{
	SubparMain main;
	Engine engine;

	Vector2 playerPos;
	Vector2 followPos;
	InputInterface following;

	public FightScreen(SubparMain subparMain)
	{
		main = subparMain;
		engine = new Engine();

		following = new InputInterface();

		engine.addSystem(new HitDetectionSystem(1 / 60f));
		engine.addSystem(new StateSystem(1 / 60f));
		engine.addSystem(new PhysicsSystem(1 / 60f, 1));
		engine.addSystem(new LevelCollisionSystem(1 / 60f));
		engine.addSystem(new CameraSystem(1 / 60f, main));
		engine.addSystem(new DrawSystem(main.assetManager.get("Prototype/map2 - Copy.png", Texture.class), main));
		engine.addSystem(new GuiDrawSystem(main));
		engine.addSystem(new DebugDrawSystem(main));

		Fighter player = new Fighter(new Vector2(-400, 0), main.playerInput, true, main, "Falco");
		Fighter follow = new Fighter(new Vector2(400, 0), following, false, main, "Fox");
		playerPos = player.getComponent(PhysicsComponent.class).position;
		followPos = follow.getComponent(PhysicsComponent.class).position;
		engine.addEntity(player);
		engine.addEntity(follow);
	}

	@Override
	public void show()
	{

	}

	@Override
	public void render(float delta)
	{
		// Try to match y pos
		if (Math.abs(playerPos.y - followPos.y) > 256)
		{
			following.vertical = (int)Math.copySign(1, playerPos.y - followPos.y);
		} else
		{
			following.vertical = 0;

			if (Math.abs(playerPos.x - followPos.x) > 600)
			{
				following.b = false;
			} else
			{
				following.b = Math.random() > 0.9999;
			}
		}

		// Try to get close
		if (Math.abs(playerPos.x - followPos.x) > 255)
		{
			following.horizontal = (int)Math.copySign(1, playerPos.x - followPos.x);
			following.a = false;
		} else
		{
			following.horizontal = 0;
			following.vertical = (int)Math.signum(Math.random() - 0.5f);
			following.a = Math.random() > 0.99;
		}

		engine.update(delta);
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{
		// Nope, break game you dunk.
	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose()
	{

	}
}
