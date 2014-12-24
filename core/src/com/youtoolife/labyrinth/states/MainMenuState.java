package com.youtoolife.labyrinth.states;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class MainMenuState extends GameState {
	
	Sprite play, score, about, quit, title, cursor;

	public MainMenuState(int StateId, MainGame game) {
		super(StateId, game);
		// TODO Auto-generated constructor stub
		createMenu();
	}
	
	public void createMenu() {
		title = new Sprite(Assets.getTexture("title"));
		title.setSize(210, 124);
		//title.setPosition(800/2-210/2, 600/2+124/2);
		/*play = new Sprite(Assets.playBtn, new Vector2(640/2-210/2,title.getPosition().y-40-50), 210, 50);
		score = new Sprite(Assets.scoreBtn, new Vector2(play.getPosition().x,play.getPosition().y-53), play.getWidth(), play.getHeight());
		about = new Sprite(Assets.aboutBtn, new Vector2(score.getPosition().x,score.getPosition().y-53), score.getWidth(), score.getHeight());
		quit = new Sprite(Assets.quitBtn, new Vector2(score.getPosition().x,about.getPosition().y-65), score.getWidth(), score.getHeight());
		cursor = new Sprite(Assets.curReg, new Vector2(play.getPosition().x, play.getPosition().y+play.getHeight()/2-15), 42, 30);
	*/
	}

	@Override
	public void draw(SpriteBatch batch) {
		title.draw(batch);
	}

	@Override
	public void update(StateBasedGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(StateBasedGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(StateBasedGame game) {
		// TODO Auto-generated method stub

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
