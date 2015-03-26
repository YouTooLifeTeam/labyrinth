package com.youtoolife.labyrinth.states;

import static com.youtoolife.labyrinth.MainGame.MAINMENUSTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.Gui;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.MiniMap;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.KeyBoardController;
import com.youtoolife.labyrinth.renderer.GameRenderer;
import com.youtoolife.labyrinth.units.Name1Player;
import com.youtoolife.labyrinth.units.Name2Player;
import com.youtoolife.labyrinth.units.Player;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.MazeGenerator;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class GamePlayState extends GameState {

	public static int SIZE = 10;

	GameRenderer gameRenderer;

	public static Chunk[][] chunks;
	public int xChunk = 1;
	public int yChunk = 1;

	public static float XOffset = 0;
	public static float YOffset = 0;

	public static Player player1, player2;
	Controller control1, control2;

	public Gui gui;
	MiniMap minimap;
	boolean isMap = false;
	float map_delay = 0.2f;

	public GamePlayState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (!isMap) {
			batch.end();
			gameRenderer.render(this);
			batch.begin();
		} else
			minimap.draw(batch);
	}

	@Override
	public void update(StateBasedGame game) {

		if (isMap) {
			minimap.update();
		} else {
			chunks[yChunk][xChunk].update();
			control1.update();
			control2.update();
			player1.update(chunks[yChunk][xChunk]);
			player2.update(chunks[yChunk][xChunk]);
			if (xChunk != player1.ChunkX) {
				chunks[yChunk][xChunk].exit();
				if(XOffset==0)
					XOffset = player1.ChunkX - xChunk;
				else
					XOffset = (1-Math.abs(XOffset))*(-XOffset/Math.abs(XOffset));
				xChunk = player1.ChunkX;
				player2.ChunkX = player1.ChunkX;
				player2.x = player1.x;
				player2.xOffset = player1.xOffset;
				player2.y = Chunk.SIZE - player1.y - 1;
				chunks[player2.ChunkY][player2.ChunkX].map[Chunk.SIZE - 1
						- player2.y][player2.x].here = player2;
			}
			if (yChunk != player1.ChunkY) {
				chunks[yChunk][xChunk].exit();
				if(YOffset==0)
					YOffset = player1.ChunkY - yChunk;
				else
					YOffset = (1-Math.abs(YOffset))*(-YOffset/Math.abs(YOffset));
				yChunk = player1.ChunkY;
				player2.ChunkY = player1.ChunkY;
				player2.y = player1.y;
				player2.yOffset = player1.yOffset;
				player2.x = Chunk.SIZE - player1.x - 1;
				chunks[player2.ChunkY][player2.ChunkX].map[Chunk.SIZE - 1
						- player2.y][player2.x].here = player2;
			}

			if (xChunk != player2.ChunkX) {
				chunks[yChunk][xChunk].exit();
				if(XOffset==0)
					XOffset = player2.ChunkX - xChunk;
				else
					XOffset = (1-Math.abs(XOffset))*(-XOffset/Math.abs(XOffset));
				xChunk = player2.ChunkX;
				player1.ChunkX = player2.ChunkX;
				player1.x = player2.x;
				player1.xOffset = player2.xOffset;
				player1.y = Chunk.SIZE - player2.y - 1;
				chunks[player1.ChunkY][player1.ChunkX].map[Chunk.SIZE - 1
						- player1.y][player1.x].here = player1;
			}
			if (yChunk != player2.ChunkY) {
				chunks[yChunk][xChunk].exit();
				if(YOffset==0)
					YOffset = player2.ChunkY - yChunk;
				else
					YOffset = (1-Math.abs(YOffset))*(-YOffset/Math.abs(YOffset));
				yChunk = player2.ChunkY;
				player1.ChunkY = player2.ChunkY;
				player1.y = player2.y;
				player1.yOffset = player2.yOffset;
				player1.x = Chunk.SIZE - player2.x - 1;
				chunks[player1.ChunkY][player1.ChunkX].map[Chunk.SIZE - 1
						- player1.y][player1.x].here = player1;
			}
			minimap.setViewed(xChunk, yChunk);

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
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.enterState(MAINMENUSTATE);
		}

		map_delay -= Gdx.graphics.getDeltaTime();
		if (map_delay <= 0) {
			if (Gdx.input.isKeyPressed(Keys.M)) {
				isMap = !isMap;
				map_delay = 0.2f;
			}
			for (com.badlogic.gdx.controllers.Controller c : Controllers
					.getControllers())
				if (c.getButton(com.badlogic.gdx.controllers.mappings.Ouya.AXIS_RIGHT_Y)) {
					isMap = !isMap;
					map_delay = 0.2f;
				}
		}
	}

	@Override
	public void init(StateBasedGame game) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void enter(StateBasedGame game) {
		MainGame.camera.position.set(0, 0, 0);
		isMap = false;
		int[] positions = new int[4];
		chunks = MazeGenerator.getMaze(SIZE, positions);
		xChunk = positions[0];
		yChunk = positions[1];
		control1 = new KeyBoardController(KeyBoardController.ARROWS);
		//control2 = new GamePadController();
		control2 = new KeyBoardController(KeyBoardController.WASD);
		player1 = new Name1Player(xChunk, yChunk, control1);
		player2 = new Name2Player(xChunk, yChunk, control2);
		chunks[yChunk][xChunk].enter();
		minimap = new MiniMap(SIZE);
		minimap.setViewed(positions[0], positions[1]);
		minimap.setViewed(positions[2], positions[3]);
		gui = new Gui(player1, player2);
		gameRenderer = new GameRenderer();
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
