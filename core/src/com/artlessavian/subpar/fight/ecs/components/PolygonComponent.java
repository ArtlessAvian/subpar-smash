package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.Polygon;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PolygonComponent implements Component
{
	public Polygon p;

	public PolygonComponent()
	{
		p = new Polygon(new Vector2(-1000, 0));
		for (int i = -10; i <= 10; i++)
		{
			p.addPoint(new Vector2(i * 100, i * i - 500));
		}
	}
}
