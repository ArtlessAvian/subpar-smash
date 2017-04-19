package com.artlessavian.subpar;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *  Gross, a singleton.
 *
 */

public class AttackHelper
{
	public enum AttackType
	{
		NORMAL, AIR, SMASH, SPECIAL,
		UP, DOWN, FORWARD, BACK, NEUTRAL
		// I don't think people will be able to mess this up
		// Still a possibility for run time error.
	}

	public static Move empty = new Move(0, new LinkedList<Hitbox>());

	// This holds Scripts and Details
	// Idk this could be more elegant

	HashMap<String, LinkedList<Move>> nameToMoveset;

	//          Up,      Down,    Forward, Back,    Neutral
	//  Normal  0        1        2        -        4
	//   Smash  5        6        7        8        -
	//     Air  10       11       12       13       14
	// Special  15       16       17       -        19


	public AttackHelper()
	{
		nameToMoveset = new HashMap<String, LinkedList<Move>>();
	}

	public void loadAttacksFor(String name)
	{
		if (nameToMoveset.containsKey(name))
		{
			return;
		}

		// TODO: do some files stuff
		LinkedList<Move> moveset = new LinkedList<Move>();

		LinkedList<Hitbox> utilt = new LinkedList<Hitbox>();
		utilt.add(new Hitbox(new Rectangle(-128, 192, 256, 64), 7, 90, 5, 10));
		moveset.add(new Move(20, utilt));

		LinkedList<Hitbox> dtilt = new LinkedList<Hitbox>();
		dtilt.add(new Hitbox(new Rectangle(0, 0, 256, 20), 5, 70, 3, 2));
		moveset.add(new Move(7, dtilt));

		LinkedList<Hitbox> ftilt = new LinkedList<Hitbox>();
		ftilt.add(new Hitbox(new Rectangle(0, 0, 256, 256), 8, 45, 5, 3));
		moveset.add(new Move(25, ftilt));

		LinkedList<Hitbox> jab = new LinkedList<Hitbox>();
		jab.add(new Hitbox(new Rectangle(0, 0, 128, 256), 3, 20, 2, 8));
		Move jabMove = new Move(10, jab);

		moveset.add(jabMove);

		moveset.add(jabMove);

		for (int i = 5; i < 10; i++)
		{
			// Cheeses Smashes
			moveset.add(jabMove);
		}

		LinkedList<Hitbox> upair = new LinkedList<Hitbox>();
		upair.add(new Hitbox(new Rectangle(-50, 128, 100, 256), 2, 90, 5, 5));
		upair.add(new Hitbox(new Rectangle(-10, 192, 20, 64), 12, 90, 15, 5));
		Move upairMove = new Move(20, upair);

		for (int i = 10; i < 15; i++)
		{
			// Cheeses Aerials
			moveset.add(upairMove);
		}

		LinkedList<Hitbox> phantasm = new LinkedList<Hitbox>();
		phantasm.add(new Hitbox(new Rectangle(-756, 64, 956, 128), 20, 270, 25, 3));
		phantasm.add(new Hitbox(new Rectangle(-200, 0, 400, 400), 9, 270, 28, 2));
		Move phantasmMove = new Move(30, phantasm);

		for (int i = 15; i < 20; i++)
		{
			// Cheeses Specials
			moveset.add(phantasmMove);
		}

		nameToMoveset.put(name, moveset);
	}

	public Move getMove(String name, AttackType type, AttackType dir)
	{
		if (!nameToMoveset.containsKey(name))
		{
			return empty;
		}

		int temp = 0;
		switch (type)
		{
			case NORMAL:
			{
				break;
			}
			case SMASH:
			{
				temp += 5;
				break;
			}
			case AIR:
			{
				temp += 10;
				break;
			}
			case SPECIAL:
			{
				temp += 15;
				break;
			}
			default:
			{
				// Fail silently :/
				System.err.print("Attack Component - Got unexpected type " + type);
			}
		}

		switch (dir)
		{
			case UP:
			{
				break;
			}
			case DOWN:
			{
				temp += 1;
				break;
			}
			case FORWARD:
			{
				temp += 2;
				break;
			}
			case BACK:
			{
				temp += 3;
				break;
			}
			case NEUTRAL:
			{
				temp += 4;
				break;
			}
			default:
			{
				// Fail silently :/
				System.err.print("Attack Component - Got unexpected direction " + type);
			}
		}

		return nameToMoveset.get(name).get(temp);
	}
}
