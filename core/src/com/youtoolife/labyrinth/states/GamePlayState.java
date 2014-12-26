package com.youtoolife.labyrinth.states;

import static com.youtoolife.labyrinth.MainGame.MAINMENUSTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.Chunk;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.Player;
import com.youtoolife.labyrinth.controller.KeyBoardController;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.MazeGenerator;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class GamePlayState extends GameState {

	public static int SIZE = 10;
	
	public static Chunk[][] chunks;
	int xChunk = 1;
	int yChunk = 1;
	
	float XOffset = 0;
	float YOffset = 0;
	
	public static Player player;
	KeyBoardController control;
	
	public GamePlayState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void draw(SpriteBatch batch) {
		for(int i = -2; i<=2;i++)
			for(int j = -2; j<=2;j++)
				if(i+yChunk>=0&&+yChunk+i<SIZE&&xChunk+j>=0&&+xChunk+j<SIZE)
					chunks[i+yChunk][j+xChunk].draw(batch, j+XOffset, i+YOffset);
		player.draw(batch, XOffset*50*Chunk.SIZE, YOffset*50*Chunk.SIZE);
	}

	@Override
	public void update(StateBasedGame game) {
		control.update();
		player.update();
		if(xChunk!=player.ChunkX){
			XOffset = player.ChunkX-xChunk;
			xChunk = player.ChunkX;
		}
		if(yChunk!=player.ChunkY){
			YOffset = player.ChunkY-yChunk;
			yChunk = player.ChunkY;
		}
		
		if ((XOffset != 0) || (YOffset != 0)) {
			int speedX = XOffset < 0 ? 1 : -1;
			speedX = XOffset == 0 ? 0 : speedX;
			int speedY = YOffset < 0 ? 1 : -1;
			speedY = YOffset == 0 ? 0 : speedY;

			XOffset += speedX * Gdx.graphics.getDeltaTime();
			YOffset += speedY * Gdx.graphics.getDeltaTime();

			if (XOffset * speedX > 0)
				XOffset = 0;
			if (YOffset * speedY > 0)
				YOffset = 0;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.enterState(MAINMENUSTATE);
		}
	}

	@Override
	public void init(StateBasedGame game) {
		int[] positions = new int[4];
		chunks = MazeGenerator.getMaze(SIZE,positions);
		xChunk = positions[0];
		yChunk = positions[1];
		control = new KeyBoardController();
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
