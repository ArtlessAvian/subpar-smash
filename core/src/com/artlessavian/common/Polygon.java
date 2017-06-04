package com.artlessavian.common;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Polygon
{
	public static class Segment
	{
		public float normal; // degrees defined clockwise
		public Vector2 previousPoint;
		public Vector2 nextPoint;

		Segment next = null;
		Segment previous = null;

		public Segment(Vector2 previousPoint, Vector2 nextPoint)
		{
			this.previousPoint = previousPoint;
			this.nextPoint = nextPoint;

			normal = nextPoint.angle(previousPoint);
		}
	}

	public Vector2 initial;
	public ArrayList<Segment> edges;

	public Polygon(Vector2 initial)
	{
		this.initial = initial;
		this.edges = new ArrayList<Segment>();
	}

	public Polygon addPoint(Vector2 nextPoint)
	{
		Segment s;
		if (nextPoint.epsilonEquals(initial, 1))
		{
			return loopMe();
		}
		else if (edges.size() == 0)
		{
			s = new Segment(initial, nextPoint);
		}
		else
		{
			s = new Segment(edges.get(edges.size()-1).nextPoint, nextPoint);
			s.previous = edges.get(edges.size()-1);
			s.previous.next = s;
		}
		edges.add(s);

		return this;
	}

	public Polygon loopMe()
	{
		Segment s = new Segment(edges.get(edges.size()-1).nextPoint, initial);
		s.previous = edges.get(edges.size()-1);
		s.previous.next = s;
		s.next = edges.get(0);
		edges.add(s);
		return this;
	}
}