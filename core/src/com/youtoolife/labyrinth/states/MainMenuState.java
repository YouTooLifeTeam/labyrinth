package com.youtoolife.labyrinth.states;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.gui.GUI;
import com.youtoolife.labyrinth.states.inputHandler.MainMenu;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class MainMenuState extends GameState {
	
	GUI mainMenu = new GUI(800, 600, new MainMenu());

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
		mainMenu.update(game);			
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
