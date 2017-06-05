package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.Polygon;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PolygonComponent implements Component
{
	public Polygon p;

	public PolygonComponent()
	{
		p = new Polygon(new Vector2(500, 0));
		p.addPoint(new Vector2(500, 200));
	}
}
