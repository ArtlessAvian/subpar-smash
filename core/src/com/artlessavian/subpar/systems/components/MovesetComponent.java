package com.artlessavian.subpar.systems.components;

import com.artlessavian.subpar.AttackHelper;
import com.artlessavian.subpar.SubparMain;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;

public class MovesetComponent implements Component
{
	public String name;
	public AttackHelper helper;

	public MovesetComponent(String name)
	{
		this.helper = ((SubparMain)Gdx.app.getApplicationListener()).attackHelper;
		helper.loadAttacksFor(name);

		this.name = name;
	}
}
