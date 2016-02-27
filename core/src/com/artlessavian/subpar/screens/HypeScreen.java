package com.artlessavian.subpar.screens;

import com.artlessavian.subpar.SubparMain;
import com.badlogic.gdx.Screen;

public class HypeScreen implements Screen
{
	SubparMain main;
	boolean done;

	float hyyyype;

	public HypeScreen(SubparMain subparMain)
	{
		hyyyype = 1;
		main = subparMain;
		done = false;
	}

	@Override
	public void show()
	{

	}

	@Override
	public void render(float delta)
	{
		if (done)
		{
			hyyyype -= delta;
			//if (hyyyype < 0)
			{
				main.setScreen(new FightScreen(main));
			}
		} else if (main.assetManager.update())
		{
			done = true;
		}
		main.bitmapFont.draw(main.spriteBatch, "oh yeahhhh " + Math.ceil(hyyyype * 100), 15, 60);
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
