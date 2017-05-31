package com.artlessavian.subpar;

import com.artlessavian.subpar.fight.FightScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public class TitleScreen implements Screen
{
	private SubparMain main;

	public TitleScreen(SubparMain main)
	{
		this.main = main;
	}

	@Override
	public void show()
	{

	}

	@Override
	public void render(float delta)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
		{
			main.setScreen(new FightScreen(main));
		}

		main.bitmapFont.draw(main.spriteBatch, "hit something", 100, 100);
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
