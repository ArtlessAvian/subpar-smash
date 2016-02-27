package com.artlessavian.subpar.systems.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicsComponent implements Component
{
	public Vector2 position;
	public Vector2 lastPosition;
	public final Rectangle movementRect;

	public Vector2 velocity;
	public boolean grounded;
	public PlatformComponent lastPlatform;
	public Sprite sprite;
	public int facing; // Negative - Left, Positive - Right (Hopefully is one)

	public boolean debugYeehhhhhhhh;

	public PhysicsComponent(Boolean debugYeehhhhhhhh, PositionComponent posC, boolean left, Texture tex)
	{
		position = posC.position; // Look out; its immutable
		lastPosition = position.cpy();
		movementRect = new Rectangle(position.x, position.y, 0, 0);

		velocity = new Vector2(0, 0);
		grounded = false;
		facing = 1;
		if (left)
		{
			facing *= -1;
		}

		sprite = new Sprite(tex, 256, 256);
		sprite.setCenter(position.x, position.y + sprite.getHeight() / 2);
		sprite.setFlip(left, false);

		this.debugYeehhhhhhhh = debugYeehhhhhhhh;
	}

	public void setSprite(int x, int y)
	{
		sprite.setRegion(x * 256, y * 256, 256, 256);
	}
}
