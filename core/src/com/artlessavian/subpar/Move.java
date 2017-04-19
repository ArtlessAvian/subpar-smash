package com.artlessavian.subpar;

import com.artlessavian.subpar.states.EmptyState;
import com.artlessavian.subpar.states.State;
import javafx.util.Pair;

import java.util.LinkedList;

public class Move
{
	public int time;
	public LinkedList<Hitbox> data;

	public Move(int time, LinkedList<Hitbox> hitboxes)
	{
		this.time = time;
		this.data = hitboxes;

//		cancels = new LinkedList<Pair<String,Class<? extends State>>>();
//		for (int i = 0; i < cancelsInto.length; i += 2)
//		{
//			// This is literally terrible
//			try
//			{
//				Class state = Class.forName(cancelsInto[i+1]);
//				cancels.add(new Pair<String, Class<? extends State>>(cancelsInto[i], state)));
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//				cancels.add(new Pair<String, Class<? extends State>>(cancelsInto[i], EmptyState.class)));
//			}
//		}
	}
}
