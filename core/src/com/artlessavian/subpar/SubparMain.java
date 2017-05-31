package com.artlessavian.subpar;

import com.artlessavian.subpar.fight.FightScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public class SubparMain extends Game
{
	public float screenWidth; // Inches
	public float screenHeight; // Inches
	public float screenRatio; // Width / Height

	public OrthographicCamera screenSpace;

	public SpriteBatch spriteBatch;
	public BitmapFont bitmapFont;

	private Sprite debuggg;
	public AssetManager assetManager;

	@Override
	public void create()
	{
		// TODO: Check if redundant
		resume();

		setScreen(new LoadScreen(this, new TitleScreen(this)));
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		super.render();

		spriteBatch.setProjectionMatrix(screenSpace.combined);
		bitmapFont.draw(spriteBatch, this.screen.getClass().getSimpleName(), 6, 18);

		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
		screenRatio = (float)width / (float)height;

		screenSpace.viewportWidth = 720 * screenRatio;
		screenSpace.viewportHeight = 720;
		screenSpace.position.x = screenSpace.viewportWidth/2f;
		screenSpace.position.y = screenSpace.viewportHeight/2f;
		screenSpace.update();

		super.resize(width, height);
	}

	@Override
	public void resume()
	{
		super.resume();

		// Initializes null objects
		if (screenSpace == null)
		{
			screenSpace = new OrthographicCamera();
			screenSpace.update();
		}
		if (spriteBatch == null)
		{
			spriteBatch = new SpriteBatch();
		}
		if (bitmapFont == null)
		{
			bitmapFont = new BitmapFont();
//			bitmapFont.getData().setScale(2);
			bitmapFont.setColor(1, 1, 1, 0.5f);
			bitmapFont.setFixedWidthGlyphs("1234567890.");
		}
		if (debuggg == null)
		{
			debuggg = new Sprite(new Texture("icon.png"));
			debuggg.setAlpha(0.4f);
		}
		if (assetManager == null)
		{
			assetManager = new AssetManager();
			assetManager.load("icon.png", Texture.class);
			assetManager.load("Prototype/Fox.png", Texture.class);
			assetManager.load("Prototype/Falco.png", Texture.class);
			assetManager.load("Prototype/map2 - Copy.png", Texture.class);
		}

		// TODO: Check if redundant
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void dispose()
	{
		debuggg.getTexture().dispose();
		spriteBatch.dispose();
		bitmapFont.dispose();
		assetManager.dispose();
	}

	// Draws Convenient Shapes

	public void debugLine(float ax, float ay, float bx, float by)
	{
		debuggg.setCenterX(ax);
		debuggg.setCenterY(ay);
		debuggg.draw(spriteBatch);
		for (int i = 0; i < 7; i++)
		{
			debuggg.translateX((bx - ax) / 7);
			debuggg.translateY((by - ay) / 7);
			debuggg.draw(spriteBatch);
		}
	}

	public void debugCircle(float x, float y, float diameter)
	{
		spriteBatch.draw(debuggg, x - diameter / 2, y - diameter / 2, diameter, diameter);
	}

	public void debugRect(Rectangle r)
	{
		debugLine(r.x, r.y, r.x + r.width, r.y);
		debugLine(r.x + r.width, r.y, r.x + r.width, r.y + r.height);
		debugLine(r.x + r.width, r.y + r.height, r.x, r.y + r.height);
		debugLine(r.x, r.y + r.height, r.x, r.y);
	}

}
