package com.artlessavian.subpar.fight;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.components.InputComponent;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;
import com.artlessavian.subpar.fight.ecs.entities.Platform;
import com.artlessavian.subpar.fight.ecs.entities.PlatformPolygon;
import com.artlessavian.subpar.fight.ecs.systems.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public class FightScreen implements Screen
{
	SubparMain main;
	Engine engine;

	DrawSystem drawSystem;
	GUIDrawSystem guiDrawSystem;
	DebugDrawSystem debugDrawSystem;

	public Stage stage;
	ArrayList<Fighter> fighters;

	public FightScreen(SubparMain main)
	{
		this.main = main;

		engine = new Engine();
//		engine.addSystem(new InputSystem());
		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem());
//		engine.addSystem(new CollisionSystem());
		engine.addSystem(new CollisionSystem2());

		drawSystem = new DrawSystem(main, this);
		engine.addSystem(drawSystem);
		guiDrawSystem = new GUIDrawSystem(main, this, drawSystem);
		engine.addSystem(guiDrawSystem);
		debugDrawSystem = new DebugDrawSystem(main, this, drawSystem);
		engine.addSystem(debugDrawSystem);

		stage = new Stage(main, this);
		Platform[] platforms = stage.getPlatforms();
		for (Platform p : platforms)
		{
			System.out.println("yee");
			engine.addEntity(p);
		}

		fighters = new ArrayList<Fighter>();

		Fighter f = new Fighter(main);
		engine.addEntity(f);
		fighters.add(f);

		engine.addEntity(new PlatformPolygon(main));
	}

	@Override
	public void show()
	{

	}

	boolean timeStop;
	float timeScale = 1;
	float rollover;
	float deltaTime = 1 / 60f;
	long engineRuns = 0;

	@Override
	public void render(float delta)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT_BRACKET)) {timeScale *= 2;
			System.out.println(timeScale);}
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT_BRACKET)) {timeScale /= 2;
			System.out.println(timeScale);}
		if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {timeStop = !timeStop;}
		if (!timeStop)
		{
			rollover += delta * timeScale;
		}
		else
		{
			if (Gdx.input.isKeyJustPressed(Input.Keys.EQUALS))
			{
				rollover += deltaTime;
			}
		}

		for (; rollover > 0; rollover -= deltaTime)
		{
			engineRuns++;
			engine.update(deltaTime);
		}

		drawSystem.update(0);
		guiDrawSystem.update(0);
		debugDrawSystem.update(0);
	}

	@Override
	public void resize(int width, int height)
	{
		drawSystem.resize();
	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

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
