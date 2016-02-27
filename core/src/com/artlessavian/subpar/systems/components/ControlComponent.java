package com.artlessavian.subpar.systems.components;

import com.artlessavian.subpar.InputInterface;
import com.badlogic.ashley.core.Component;

public class ControlComponent implements Component
{
	public InputInterface inputInterface;

	public ControlComponent(InputInterface inputInterface)
	{
		this.inputInterface = inputInterface;
	}
}
