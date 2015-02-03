package com.youtoolife.labyrinth.states.inputHandler;

import com.youtoolife.labyrinth.gui.GUI;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public abstract class InputHandler {
	
	public abstract void update(StateBasedGame game, GUI gui, float x, float y);

}
