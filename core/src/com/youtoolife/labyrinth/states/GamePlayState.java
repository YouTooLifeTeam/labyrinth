package com.youtoolife.labyrinth.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.Chunk;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.MazeGenerator;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class GamePlayState extends GameState {

	Chunk[][] chunks;
	int xChunk = 1;
	int yChunk = 1;
	
	public GamePlayState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void draw(SpriteBatch batch) {
		for(int i = -1; i<=1;i++)
			for(int j = -1; j<=1;j++)
				if(i+yChunk>=0&&+yChunk+i<10&&xChunk+j>=0&&+xChunk+j<10)
					chunks[i+yChunk][j+xChunk].draw(batch, j, -i);
	}

	@Override
	public void update(StateBasedGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(StateBasedGame game) {
		int[] positions = new int[4];
		chunks = MazeGenerator.getMaze(10,positions);
		xChunk = positions[0];
		yChunk = positions[1];
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
