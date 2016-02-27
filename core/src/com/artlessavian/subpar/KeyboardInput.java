package com.artlessavian.subpar;

import com.badlogic.gdx.Input;

public class KeyboardInput extends DrawableInputAdapter
{
	InputInterface inputInterface;

	public KeyboardInput(InputInterface input)
	{
		inputInterface = input;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode)
		{
			case Input.Keys.W:
			{
				inputInterface.vertical = (int)Math.signum(inputInterface.vertical + 1);
				return true;
			}
			case Input.Keys.A:
			{
				inputInterface.horizontal = (int)Math.signum(inputInterface.horizontal - 1);
				return true;
			}
			case Input.Keys.S:
			{
				inputInterface.vertical = (int)Math.signum(inputInterface.vertical - 1);
				return true;
			}
			case Input.Keys.D:
			{
				inputInterface.horizontal = (int)Math.signum(inputInterface.horizontal + 1);
				return true;
			}
			case Input.Keys.J:
			{
				inputInterface.a = true;
				return true;
			}
			case Input.Keys.N:
			{
				inputInterface.b = true;
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		switch (keycode)
		{
			case Input.Keys.W:
			{
				inputInterface.vertical = (int)Math.signum(inputInterface.vertical - 1);
				return true;
			}
			case Input.Keys.A:
			{
				inputInterface.horizontal = (int)Math.signum(inputInterface.horizontal + 1);
				return true;
			}
			case Input.Keys.S:
			{
				inputInterface.vertical = (int)Math.signum(inputInterface.vertical + 1);
				return true;
			}
			case Input.Keys.D:
			{
				inputInterface.horizontal = (int)Math.signum(inputInterface.horizontal - 1);
				return true;
			}
			case Input.Keys.J:
			{
				inputInterface.a = false;
				return true;
			}
			case Input.Keys.N:
			{
				inputInterface.b = false;
				return true;
			}
		}

		return false;
	}

	@Override
	public void draw(SubparMain main)
	{
		main.bitmapFont.draw(main.spriteBatch, inputInterface.vertical + " " + inputInterface.horizontal + " " + inputInterface.a + inputInterface.b, 0, 0);
	}
}
