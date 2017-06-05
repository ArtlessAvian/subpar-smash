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

		public Segment next = null;
		public Segment previous = null;

		public Segment(Vector2 previousPoint, Vector2 nextPoint)
		{
			this.previousPoint = previousPoint;
			this.nextPoint = nextPoint;

			normal = (float)(Math.toDegrees(Math.atan2(nextPoint.y - previousPoint.y, nextPoint.x - previousPoint.x)) + 90);
			System.out.println(normal);
		}

		public boolean projectionOnSegment(Vector2 point)
		{
			float vx = point.x - previousPoint.x;
			float vy = point.y - previousPoint.y;
			float sx = nextPoint.x - previousPoint.x;
			float sy = nextPoint.y - previousPoint.y;
			float numerator = (vx * sx) + (vy * sy);
			float denominator = (sx * sx) + (sy * sy);
			float rx = numerator/denominator * sx;
			float ry = numerator/denominator * sy;

			if (sx != Float.NaN && rx/sx > 1) {return false;}
			if (sy != Float.NaN && ry/sy > 1) {return false;}
			return true;
		}

		public Vector2 projection(Vector2 point)
		{
			float vx = point.x - previousPoint.x;
			float vy = point.y - previousPoint.y;
			float sx = nextPoint.x - previousPoint.x;
			float sy = nextPoint.y - previousPoint.y;
			float numerator = (vx * sx) + (vy * sy);
			float denominator = (sx * sx) + (sy * sy);
			float rx = numerator/denominator * sx + previousPoint.x;
			float ry = numerator/denominator * sy + previousPoint.y;
			return new Vector2(rx, ry);
		}

		public float distance(Vector2 point)
		{
			if (previousPoint.x != nextPoint.x)
			{
				float dY = nextPoint.y - previousPoint.y;
				float dX = nextPoint.x - previousPoint.x;
				float dx = point.x - previousPoint.x;
				float dy = dx * dY/dX;
				float shearY = point.y - dy - previousPoint.y;
				return (float)(shearY * Math.sin(Math.toRadians(180 - normal)));
			}
			else
			{
				if (nextPoint.y > previousPoint.y)
				{
					return -point.x + previousPoint.x;
				}
				else
				{
					return point.x - previousPoint.x;
				}
			}
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
		edges.add(s);
		return this;
	}

	public static void main(String[] args)
	{
		Segment s = new Segment(new Vector2(0, 0), new Vector2(3, 3));
		s.projectionOnSegment()
	}
}