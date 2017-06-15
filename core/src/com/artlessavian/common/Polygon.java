package com.artlessavian.common;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Polygon
{
	public static class Segment
	{
		public float normal; // degrees defined clockwise
		public Vector2 previousPoint;
		public Vector2 nextPoint;

		public Segment next = null;
		public Segment previous = null;

		public Segment(Vector2 previousPoint, Vector2 nextPoint)
		{
			this.previousPoint = previousPoint;
			this.nextPoint = nextPoint;

			normal = (float)(Math.toDegrees(Math.atan2(nextPoint.y - previousPoint.y, nextPoint.x - previousPoint.x)) + 90);
		}

		public float intersectVertical(float x)
		{
			return (nextPoint.y - previousPoint.y)/(nextPoint.x - previousPoint.x) * (x - previousPoint.x) + previousPoint.y;
		}

		public float intersectHorizontal(float y)
		{
			return (nextPoint.x - previousPoint.x) / (nextPoint.y - previousPoint.y) * (y - previousPoint.y) + previousPoint.x;
		}

		public boolean pointOnSegment(Vector2 point)
		{
			return distance(point) < 0.01f;
		}

		public Vector2 intersect(Vector2 a, Vector2 b)
		{
			Vector2 v = new Vector2(0,0);
			if (Intersector.intersectSegments(previousPoint, nextPoint, a, b, v))
				return v;
			else
				return null;
		}

		public float getLength()
		{
			return previousPoint.dst(nextPoint);
		}

 		public float distance(Vector2 point)
		{
			return Intersector.distanceSegmentPoint(previousPoint, nextPoint, point);
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
		if (nextPoint.epsilonEquals(initial, 0.001f))
		{
			return loopMe();
		}
		else if (edges.size() == 0)
		{
			s = new Segment(initial, nextPoint);
		}
		else
		{
			s = new Segment(edges.get(edges.size() - 1).nextPoint, nextPoint);
			s.previous = edges.get(edges.size() - 1);
			s.previous.next = s;
		}
		edges.add(s);

		return this;
	}

	public Polygon loopMe()
	{
		Segment s = new Segment(edges.get(edges.size() - 1).nextPoint, initial);
		s.previous = edges.get(edges.size() - 1);
		s.previous.next = s;

		s.next = edges.get(0);
		s.next.previous = s;

		edges.add(s);
		return this;
	}

	public static void main(String[] args)
	{
		Segment s = new Segment(new Vector2(-1000, -100), new Vector2(1000, -200));
		System.out.println(s.intersect(new Vector2(0, 0), new Vector2(0, -200)));
	}
}