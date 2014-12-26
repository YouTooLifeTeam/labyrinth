package com.youtoolife.labyrinth.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

import static com.youtoolife.labyrinth.MainGame.GAMEPLAYSTATE;

public class MainMenuState extends GameState {
	
	Sprite single_btn, multi_btn, 
	score_btn, about_btn, settings_btn,
	quit_btn, title, cursor;

	public MainMenuState(int StateId, MainGame game) {
		super(StateId, game);
		createMenu();
		
	}
	
	public void createMenu() {
		title = new Sprite(Assets.getTexture("title"));
		title.setSize(512, 128);
		title.setPosition(800/2-512/2, 600-128-30);
		single_btn = new Sprite(Assets.getTexture("single"));
		single_btn.setPosition(title.getX(), title.getY()-128-3);
		multi_btn = new Sprite(Assets.getTexture("multiplayer"));
		multi_btn.setPosition(title.getX()+254+8, title.getY()-128-3);
		settings_btn = new Sprite(Assets.getTexture("settings"));
		settings_btn.setPosition(title.getX(), title.getY()-128*2-3);
		score_btn = new Sprite(Assets.getTexture("score"));
		score_btn.setPosition(title.getX()+168+4, title.getY()-128*2-3);
		about_btn = new Sprite(Assets.getTexture("about"));
		about_btn.setPosition(title.getX()+168*2+4*2, title.getY()-128*2-3);
		quit_btn = new Sprite(Assets.getTexture("quit"));
		quit_btn.setPosition(800/2-254/2, title.getY()-128*3+3);
	}

	@Override
	public void draw(SpriteBatch batch) {
		title.draw(batch);
		single_btn.draw(batch);
		multi_btn.draw(batch);
		settings_btn.draw(batch);
		score_btn.draw(batch);
		about_btn.draw(batch);
		quit_btn.draw(batch);
	}

	@Override
	public void update(StateBasedGame game) {
		if (Gdx.input.justTouched()) {
		   
			float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), 
			dw = w/800, dh = h/600, 
			cX = Gdx.input.getX(), cY = Gdx.input.getY(),
			x = cX/dw, y = 600 - cY/dh;
			System.out.println(x+" - "+y);
			if (single_btn.getBoundingRectangle().contains(x,y)) {
				System.out.println("single_btn");
				game.enterState(GAMEPLAYSTATE);
			}
			if (multi_btn.getBoundingRectangle().contains(x,y)) {
				System.out.println("multi_btn");
			}
			if (settings_btn.getBoundingRectangle().contains(x,y)) {
				System.out.println("settings_btn");
			}
			if (score_btn.getBoundingRectangle().contains(x,y)) {
				System.out.println("score_btn");
			}
			if (about_btn.getBoundingRectangle().contains(x,y)) {
				System.out.println("about_btn");
			}
			if (quit_btn.getBoundingRectangle().contains(x,y)) {
				System.out.println("quit_btn");
				System.exit(0);
			}
		}
	}

	@Override
	public void init(StateBasedGame game) {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(StateBasedGame game) {
		((MainGame)game).camera.position.set(800/2, 600/2, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
