package com.youtoolife.labyrinth.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.Chunk;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.Player;
import com.youtoolife.labyrinth.controller.KeyBoardController;
import com.youtoolife.labyrinth.controller.adapter.KeyBoardAdapter;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.MazeGenerator;
import com.youtoolife.labyrinth.utils.StateBasedGame;

import static com.youtoolife.labyrinth.MainGame.MAINMENUSTATE;

public class GamePlayState extends GameState {

	public static int SIZE = 10;
	
	public static Chunk[][] chunks;
	int xChunk = 1;
	int yChunk = 1;
	
	public static Player player;
	KeyBoardController control;
	KeyBoardAdapter keyadapter; 
	
	public GamePlayState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void draw(SpriteBatch batch) {
		for(int i = -1; i<=1;i++)
			for(int j = -1; j<=1;j++)
				if(i+yChunk>=0&&+yChunk+i<SIZE&&xChunk+j>=0&&+xChunk+j<SIZE)
					chunks[i+yChunk][j+xChunk].draw(batch, j, i);
		player.draw(batch, 0, 0);
	}

	@Override
	public void update(StateBasedGame game) {
		control.update();
		player.update();
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.enterState(MAINMENUSTATE);
		}
	}

	@Override
	public void init(StateBasedGame game) {
		keyadapter = new KeyBoardAdapter();
		Gdx.input.setInputProcessor(keyadapter);
		int[] positions = new int[4];
		chunks = MazeGenerator.getMaze(SIZE,positions);
		xChunk = positions[0];
		yChunk = positions[1];
		control = new KeyBoardController();
		keyadapter.addProc(control);
		player =new Player(xChunk,yChunk,control);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(StateBasedGame game) {
		((MainGame)game).camera.position.set(0, 0, 0);
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
