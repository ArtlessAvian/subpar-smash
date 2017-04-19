package com.artlessavian.subpar.systems.draw;

import com.artlessavian.subpar.SubparMain;
import com.artlessavian.subpar.systems.components.PercentComponent;
import com.artlessavian.subpar.systems.components.PhysicsComponent;
import com.artlessavian.subpar.systems.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GuiDrawSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	SubparMain main;

	public GuiDrawSystem(SubparMain subparMain)
	{
		main = subparMain;
	}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PercentComponent.class).get());
		setProcessing(false);
	}

	@Override
	public void update(float rollover)
	{
		main.spriteBatch.setProjectionMatrix(main.identity);
		main.bitmapFont.setColor(1,1,1,1);

		float x = 0;
		float y = 60;

		for (Entity e : entities)
		{
			x += main.screenWidth/(entities.size()+1f);

			PercentComponent pc = e.getComponent(PercentComponent.class);

			main.bitmapFont.draw(main.spriteBatch, (int)pc.percent + "", x, y);
		}

		main.spriteBatch.setProjectionMatrix(main.camera.combined);
	}
}
