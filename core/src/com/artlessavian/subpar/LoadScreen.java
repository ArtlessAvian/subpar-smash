package com.artlessavian.subpar;

import com.badlogic.gdx.Screen;

public class LoadScreen implements Screen
{
	private final SubparMain main;
	private final Screen redirect;

	public LoadScreen(SubparMain main, Screen redirect)
	{
		this.main = main;
		this.redirect = redirect;
		System.out.println("loading");
	}

	@Override
	public void show()
	{
	}

	@Override
	public void render(float delta)
	{
		boolean done = main.assetManager.update();
		if (done)
		{
			main.setScreen(redirect);
		}
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
