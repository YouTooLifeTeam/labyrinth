package com.youtoolife.labyrinth.states.inputHandler;

import static com.youtoolife.labyrinth.MainGame.GAMEPLAYSTATE;

import com.youtoolife.labyrinth.gui.GUI;
import com.youtoolife.labyrinth.gui.SpriteGUI;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class MainMenu extends InputHandler {

	@Override
	public void update(StateBasedGame game, GUI mainMenu, float x, float y) {
		
			
			for (SpriteGUI sprite:mainMenu.sprites) {
				if (sprite.getBoundingRectangle().contains(x, y)
						&&sprite.action.equalsIgnoreCase("single")) {
					System.out.println("single_btn");
					game.enterState(GAMEPLAYSTATE);
				}
				if (sprite.getBoundingRectangle().contains(x, y)
						&&sprite.action.equalsIgnoreCase("multiplayer")) {
					System.out.println("multiplayer");
				}
				if (sprite.getBoundingRectangle().contains(x, y)
						&&sprite.action.equalsIgnoreCase("settings")) {
					System.out.println("settings");
				}
				if (sprite.getBoundingRectangle().contains(x, y)
						&&sprite.action.equalsIgnoreCase("score")) {
					System.out.println("score");
				}
				if (sprite.getBoundingRectangle().contains(x, y)
						&&sprite.action.equalsIgnoreCase("about")) {
					System.out.println("about");
				}
				if (sprite.getBoundingRectangle().contains(x, y)
						&&sprite.action.equalsIgnoreCase("quit")) {
					System.out.println("quit");
					System.exit(0);
				}
					
			}
		}

}
