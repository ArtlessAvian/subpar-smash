package com.artlessavian.subpar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TouchInput extends DrawableInputAdapter
{
	InputInterface inputInterface;
	Vector2 total;
	int startX;
	int startY;

	int movementPointer;
	int aPointer;
	int bPointer;

	public TouchInput(InputInterface input)
	{
		inputInterface = input;
		total = new Vector2(0, 0);

		movementPointer = -1;
		aPointer = -1;
		bPointer = -1;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (screenX < Gdx.graphics.getWidth() / 2)
		{
			startX = screenX;
			startY = screenY;
			total.x = 0;
			total.y = 0;
			inputInterface.horizontal = 0;
			inputInterface.vertical = 0;
			movementPointer = pointer;
			return true;
		} else if (screenY < Gdx.graphics.getHeight() / 2)
		{
			inputInterface.a = true;
			aPointer = pointer;
			return true;
		} else
		{
			inputInterface.b = true;
			bPointer = pointer;
			return true;
		}
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (movementPointer == pointer)
		{
			movementPointer = -1;
			total.set(0,0);
			inputInterface.horizontal = 0;
			inputInterface.vertical = 0;
			return true;
		}
		if (aPointer == pointer)
		{
			aPointer = -1;
			inputInterface.a = false;
			return true;
		}
		if (bPointer == pointer)
		{
			bPointer = -1;
			inputInterface.b = false;
			return true;
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		if (pointer == movementPointer)
		{
			total.x = screenX - startX;
			total.y = -screenY + startY;

			if (total.len2() > 2500)
			{
				float angle = total.angle();

				// UP
				if (angle > 22.5 && angle < 157.5)
				{
					inputInterface.vertical = 1;
				}
				// LEFT
				if (angle >= 112.5 && angle <= 247.5)
				{
					inputInterface.horizontal = -1;
				}
				// RIGHT
				if (angle <= 67.5 || angle >= 292.5)
				{
					inputInterface.horizontal = 1;
				}
				// DOWN
				if (angle > 202.5 && angle < 337.5)
				{
					inputInterface.vertical = -1;
				}
			} else
			{
				inputInterface.horizontal = 0;
				inputInterface.vertical = 0;
			}
			return true;
		}
		return false;
	}

	private Vector3 unprojStart;
	private Vector3 unprojCurrent;

	@Override
	public void draw(SubparMain main)
	{
		if (total.x != 0)
		{
			if (unprojStart == null)
			{
				unprojStart = new Vector3();
			}
			if (unprojCurrent == null)
			{
				unprojCurrent = new Vector3();
			}

			unprojStart.set(startX, startY, 0);
			main.camera.unproject(unprojStart);

			for (float i = (float)(Math.PI / 8); i < (float)(Math.PI); i += (float)(Math.PI / 4))
			{
				main.debugLine(
						unprojStart.x + 200 * (float)Math.cos(i), unprojStart.y + 200 * (float)Math.sin(i),
						unprojStart.x - 200 * (float)Math.cos(i), unprojStart.y - 200 * (float)Math.sin(i));
			}

			// TODO: Y position is off haha
			unprojCurrent.set(startX + total.x, startY - (main.screenHeight + total.y), 0);
			main.camera.unproject(unprojCurrent);
			main.debugLine(unprojStart.x, unprojStart.y, unprojCurrent.x, unprojCurrent.y);

			main.bitmapFont.draw(main.spriteBatch, total.x + " " + total.y + " " + total.len2(), unprojStart.x, unprojStart.y);
		}
	}
}
