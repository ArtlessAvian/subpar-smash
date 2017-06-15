package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.common.Polygon;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PolygonComponent implements Component
{
	public Polygon p;

	public PolygonComponent()
	{
		p = new Polygon(new Vector2(-1200, -100));
//		p.addPoint(new Vector2(-300, -200));
//		p.addPoint(new Vector2(300, -100));

//		for (float i = -1000; i <= 1000; i += 1)
//		{
//			p.addPoint(new Vector2(i, (float)Math.sin(i / 10f) * i/10f - 100));
//		}

		p.addPoint(new Vector2(-720, -100));
		p.addPoint(new Vector2(-720, -200));
		p.addPoint(new Vector2(-240, -200));
		p.addPoint(new Vector2(-240, -100));
		p.addPoint(new Vector2(240, -100));
		p.addPoint(new Vector2(240, 0));
		p.addPoint(new Vector2(720, 0));
		p.addPoint(new Vector2(720, -100));


		p.addPoint(new Vector2(1200, -100));

		p.addPoint(new Vector2(1200, -500));
		p.addPoint(new Vector2(-1200, -500));
		p.loopMe();
	}
}
