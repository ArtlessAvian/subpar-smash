package com.artlessavian.subpar;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KeyboardInput extends InputAdapter
{
	InputContainer inputs;

	public KeyboardInput(InputContainer input)
	{
		this.inputs = input;
	}

	@Override
	public boolean keyDown(int keycode)
	{
//		System.out.println(keycode);
		switch (keycode)
		{
			case Input.Keys.W:
			{
				inputs.main.y = (int)Math.signum(inputs.main.y + 1);
				return true;
			}
			case Input.Keys.A:
			{
				inputs.main.x = (int)Math.signum(inputs.main.x - 1);
				return true;
			}
			case Input.Keys.S:
			{
				inputs.main.y = (int)Math.signum(inputs.main.y - 1);
				return true;
			}
			case Input.Keys.D:
			{
				inputs.main.x = (int)Math.signum(inputs.main.x + 1);
				return true;
			}
			case Input.Keys.J:
			{
				inputs.aPressed = true;
				return true;
			}
			case Input.Keys.N:
			{
				inputs.bPressed = true;
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
				inputs.main.y = (int)Math.signum(inputs.main.y - 1);
				return true;
			}
			case Input.Keys.A:
			{
				inputs.main.x = (int)Math.signum(inputs.main.x + 1);
				return true;
			}
			case Input.Keys.S:
			{
				inputs.main.y = (int)Math.signum(inputs.main.y + 1);
				return true;
			}
			case Input.Keys.D:
			{
				inputs.main.x = (int)Math.signum(inputs.main.x - 1);
				return true;
			}
			case Input.Keys.J:
			{
				inputs.aPressed = false;
				return true;
			}
			case Input.Keys.N:
			{
				inputs.bPressed = false;
				return true;
			}
		}

		return false;
	}
}
