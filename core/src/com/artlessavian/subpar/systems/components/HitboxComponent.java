package com.artlessavian.subpar.systems.components;

import com.artlessavian.subpar.Hitbox;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.css.Rect;

import java.util.LinkedList;

public class HitboxComponent implements Component
{
	public LinkedList<Hitbox> hitboxes;
	private float deltaX;
	private float deltaY;
	private boolean flipped;

	public HitboxComponent()
	{
		hitboxes = new LinkedList<Hitbox>();
	}

	public void moveToPosition(Vector2 position, boolean facingLeft)
	{
		for (Hitbox hbox : hitboxes)
		{
			Rectangle rectangle = hbox.collision;

			if (facingLeft)
			{
				rectangle.x = -rectangle.x - rectangle.width;
				hbox.angle = 180 - hbox.angle;
			}

			rectangle.x += position.x;
			rectangle.y += position.y;
		}

		deltaX += position.x;
		deltaY += position.y;
		flipped = facingLeft;
	}

	public void moveToOriginal()
	{
		for (Hitbox hbox : hitboxes)
		{
			Rectangle rectangle = hbox.collision;

			rectangle.x -= deltaX;
			rectangle.y -= deltaY;

			if (flipped)
			{
				rectangle.x = -rectangle.x - rectangle.width;
				hbox.angle = 180 - hbox.angle;
			}
		}

		deltaX = 0;
		deltaY = 0;
	}
}
