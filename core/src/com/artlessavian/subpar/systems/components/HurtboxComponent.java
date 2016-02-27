package com.artlessavian.subpar.systems.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class HurtboxComponent implements Component
{
	public LinkedList<Rectangle> hurtboxes;
	private float deltaX;
	private float deltaY;
	private boolean flipped;
	
	public HurtboxComponent()
	{
		hurtboxes = new LinkedList<Rectangle>();

		hurtboxes.add(new Rectangle(-128, 0, 256, 256));
	}
	
	public void moveToPosition(Vector2 position, boolean facingLeft)
	{
		for (Rectangle rectangle : hurtboxes)
		{
			if (facingLeft)
			{
				rectangle.x = -rectangle.x - rectangle.width;
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
		for (Rectangle rectangle : hurtboxes)
		{
			rectangle.x -= deltaX;
			rectangle.y -= deltaY;
			
			if (flipped)
			{
				rectangle.x = -rectangle.x - rectangle.width;
			}
		}
		
		deltaX = 0;
		deltaY = 0;
	}
}
