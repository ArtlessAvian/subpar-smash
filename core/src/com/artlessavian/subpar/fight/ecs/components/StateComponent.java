package com.artlessavian.subpar.fight.ecs.components;

import com.artlessavian.subpar.StateMachine;
import com.badlogic.ashley.core.Component;

public class StateComponent implements Component
{
	public StateMachine machine = new StateMachine();
}
