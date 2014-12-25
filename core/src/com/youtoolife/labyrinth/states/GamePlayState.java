package com.youtoolife.labyrinth.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.Chunk;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.MazeGenerator;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class GamePlayState extends GameState {

	Chunk[][] chunks;
	
	public GamePlayState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(StateBasedGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(StateBasedGame game) {
		chunks = MazeGenerator.getMaze(10);
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
