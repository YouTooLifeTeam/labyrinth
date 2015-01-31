package com.youtoolife.labyrinth.states;

import static com.youtoolife.labyrinth.MainGame.GAMEPLAYSTATE;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.gui.GUI;
import com.youtoolife.labyrinth.gui.SpriteGUI;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class MainMenuState extends GameState {
	
	GUI mainMenu = new GUI(800, 600);

	public MainMenuState(int StateId, MainGame game) {
		super(StateId, game);
		createMenu();
		
	}
	
	public void createMenu() {
		try {
			mainMenu.loadGui("bin/gui/MainMenu.gui");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		mainMenu.draw(batch);
	}

	@Override
	public void update(StateBasedGame game) {
		if (Gdx.input.justTouched()) {
		   
			float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), 
			dw = w/800, dh = h/600, 
			cX = Gdx.input.getX(), cY = Gdx.input.getY(),
			x = cX/dw, y = 600 - cY/dh;
			System.out.println(x+" - "+y);
			
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
				}
					
			}
		}
			
	}

	@Override
	public void init(StateBasedGame game) {	}

	@Override
	public void dispose() {	}

	@Override
	public void enter(StateBasedGame game) {
		((MainGame)game).camera.position.set(800/2, 600/2, 0);
	}

	@Override
	public void pause() {	}

	@Override
	public void resume() {	}

}
