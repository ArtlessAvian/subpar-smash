package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.subpar.InputContainer;
import com.artlessavian.subpar.KeyboardInput;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;

public class InputComponent implements Component
{
	public InputContainer inputs;

	public InputComponent()
	{
		inputs = new InputContainer();
		Gdx.input.setInputProcessor(new KeyboardInput(inputs));
	}
}
