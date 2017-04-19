package com.artlessavian.subpar.screens;

import com.artlessavian.subpar.InputInterface;
import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.systems.*;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.draw.CameraSystem;
import com.artlessavian.subpar.systems.draw.DebugDrawSystem;
import com.artlessavian.subpar.systems.draw.DrawSystem;
import com.artlessavian.subpar.systems.draw.GuiDrawSystem;
import com.artlessavian.subpar.systems.entities.Fighter;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class FightScreen implements Screen
{
	SubparMain main;
	Engine engine;

	CameraSystem camSys;
	DrawSystem drawSys;
	GuiDrawSystem guiDrawSys;
	DebugDrawSystem debugDrawSys;

	Vector2 playerPos;
	Vector2 followPos;
	InputInterface following;

	public FightScreen(SubparMain subparMain)
	{
		main = subparMain;
		engine = new Engine();

		following = new InputInterface();

		engine.addSystem(new HitDetectionSystem());
		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem());
		engine.addSystem(new LevelCollisionSystem());

		camSys = new CameraSystem(main);
		drawSys = new DrawSystem(main.assetManager.get("Prototype/map2 - Copy.png", Texture.class), main);
		guiDrawSys = new GuiDrawSystem(main);
		debugDrawSys = new DebugDrawSystem(main);
		engine.addSystem(camSys);
		engine.addSystem(drawSys);
		engine.addSystem(guiDrawSys);
		engine.addSystem(debugDrawSys);

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

	float rollover = 0;

	@Override
	public void render(float delta)
	{
		rollover += delta * 60;
		while(rollover >= 1)
		{
			rollover--;
			engineIterate();
		}

		camSys.update(rollover);
		drawSys.update(rollover);
		guiDrawSys.update(rollover);
		debugDrawSys.update(rollover);
	}

	public void engineIterate()
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

		engine.update(0);
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
