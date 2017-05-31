package com.artlessavian.subpar.fight;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.fight.ecs.entities.Fighter;
import com.artlessavian.subpar.fight.ecs.systems.DrawSystem;
import com.artlessavian.subpar.fight.ecs.systems.PhysicsSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public class FightScreen implements Screen
{
	SubparMain main;
	Engine engine;

	DrawSystem drawSystem;

	public FightScreen(SubparMain main)
	{
		this.main = main;

		engine = new Engine();
		engine.addSystem(new PhysicsSystem());

		drawSystem = new DrawSystem(main, this);
		engine.addSystem(drawSystem);

		Fighter f = new Fighter(main);
		engine.addEntity(f);
	}

	@Override
	public void show()
	{

	}

	boolean timeStop;
	float rollover;
	float deltaTime = 1/60f/4f;
	long engineRuns = 0;

	@Override
	public void render(float delta)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {timeStop = !timeStop;}
		if (!timeStop || Gdx.input.isKeyJustPressed(Input.Keys.EQUALS))
		{
			rollover += delta;
		}

		for (; rollover > 0; rollover -= deltaTime)
		{
			engineRuns++;
			engine.update(deltaTime);
		}

		drawSystem.update(0);
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
