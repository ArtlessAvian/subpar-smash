package com.artlessavian.subpar;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Hitbox
{
	private float appearTime;
	private float existTime;
	private float appearTimerrrrrrrrrr;
	private float existTimerrrrrrrrrr;

	public LinkedList<Entity> gotRekt;

	public float damage = 11.5f;
	public float angle = 90;

	boolean exists;
	public Rectangle collision;

	public Hitbox(Rectangle collision, float damage, float angle, float appearTimeeeeee, float existTimeeeeee)
	{
		this.appearTime = appearTimeeeeee;
		this.existTime = existTimeeeeee;
		exists = false;

		gotRekt = new LinkedList<Entity>();

		this.collision = collision;
		this.damage = damage;
		this.angle = angle;
	}

	public void prepare()
	{
		appearTimerrrrrrrrrr = appearTime;
		existTimerrrrrrrrrr = existTime;
		exists = false;

		gotRekt.clear();
	}

	public void update(float delta, LinkedList<Hitbox> hitboxes)
	{
		appearTimerrrrrrrrrr -= delta;
		if (appearTimerrrrrrrrrr < 0)
		{
			existTimerrrrrrrrrr += appearTimerrrrrrrrrr;
			appearTimerrrrrrrrrr = 0;
		}
		if (!exists && appearTimerrrrrrrrrr == 0 && existTimerrrrrrrrrr > 0)
		{
			hitboxes.add(this);
		}
		if (exists && existTimerrrrrrrrrr <= 0)
		{
			hitboxes.remove(this);
		}

		exists = (appearTimerrrrrrrrrr == 0 && existTimerrrrrrrrrr > 0);
	}
}