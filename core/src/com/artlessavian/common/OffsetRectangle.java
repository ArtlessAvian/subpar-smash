package com.artlessavian.common;

import com.badlogic.gdx.math.Rectangle;

public class OffsetRectangle extends Rectangle
{
	public float deltaX;
	public float deltaY;
	public boolean flipped;

	public OffsetRectangle(float deltaX, float deltaY)
	{
		super();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public OffsetRectangle(float deltaX, float deltaY, float width, float height)
	{
		super(0, 0, width, height);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public OffsetRectangle setPosition(float x, float y)
	{
		return (OffsetRectangle)super.setPosition(x + deltaX, y + deltaY);
	}

	public OffsetRectangle flip()
	{
		flipped = !flipped;
		float ax = x - deltaX;
		deltaX = -getWidth() - deltaX;
		setPosition(ax, y - deltaY);
		return this;
	}

	public OffsetRectangle setFlip(boolean flip)
	{
		if (flip != flipped)
		{
			flip();
		}
		return this;
	}

//	public OffsetRectangle setOffsets(float xOffset, float yOffset)
//	{
//		return setXOffset(xOffset).setYOffset(yOffset);
//	}
//
//	public OffsetRectangle setXOffset(float xOffset)
//	{
//		float deltaXOffset = xOffset - deltaX;
//		x += deltaXOffset;
//		deltaX = xOffset;
//		return this;
//	}
//
//	public OffsetRectangle setYOffset(float yOffset)
//	{
//		float deltaYOffset = yOffset - deltaY;
//		y += deltaYOffset;
//		deltaY = yOffset;
//		return this;
//	}
}
