package com.youtoolife.labyrinth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youtoolife.labyrinth.chunk.Chunk.Exits;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.Assets;

public class MiniMap {

	boolean[][] map;
	TextureRegion[][] map_tex;

	TextureRegion[] room = new TextureRegion[6];// 0 - wall;1-exit;2o - exit; 3
												// - 3exitl 4 -
	// 4 exit, 5 - 2b - exit

	float dx = 0, dy = 0;

	public MiniMap(int SIZE) {
		map = new boolean[SIZE][SIZE];
		map_tex = new TextureRegion[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				map[i][j] = false;
		room[0] = new TextureRegion(Assets.getTexture("minimap/wall"));
		room[1] = new TextureRegion(Assets.getTexture("minimap/1sectioned"));
		room[2] = new TextureRegion(Assets.getTexture("minimap/2sectionedo"));
		room[3] = new TextureRegion(Assets.getTexture("minimap/3sectioned"));
		room[4] = new TextureRegion(Assets.getTexture("minimap/4sectioned"));
		room[5] = new TextureRegion(Assets.getTexture("minimap/2sectionedb"));

		for (int i = 0; i < GamePlayState.SIZE; i++)
			for (int j = 0; j < GamePlayState.SIZE; j++) {
				TextureRegion texture = new TextureRegion(room[0]);
				if (GamePlayState.chunks[i][j].type == Exits.SingleExit)
					texture = room[1];
				if (GamePlayState.chunks[i][j].type == Exits.DiOpposite)
					texture = room[2];
				if (GamePlayState.chunks[i][j].type == Exits.TriExit)
					texture = room[3];
				if (GamePlayState.chunks[i][j].type == Exits.QuadroExit)
					texture = room[4];
				if (GamePlayState.chunks[i][j].type == Exits.DiNeighbour)
					texture = room[5];
				map_tex[i][j] = texture;
			}
	}

	public void setViewed(int x, int y) {
		map[y][x] = true;
	}

	public void draw(SpriteBatch batch) {
		for (int i = 0; i < GamePlayState.SIZE; i++)
			for (int j = 0; j < GamePlayState.SIZE; j++)
				if (map[i][j]) {
					batch.draw(map_tex[i][j], j * 50 - GamePlayState.SIZE * 50
							+ dx, i * 50 - GamePlayState.SIZE * 50 / 2 + dy,
							50 / 2, 50 / 2, 50, 50, 1, 1, -90
									* GamePlayState.chunks[i][j].rotates);
				}
	}

	public void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			dy -= Gdx.graphics.getDeltaTime() * 500;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			dy += Gdx.graphics.getDeltaTime() * 500;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			dx -= Gdx.graphics.getDeltaTime() * 500;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			dx += Gdx.graphics.getDeltaTime() * 500;

		for (com.badlogic.gdx.controllers.Controller c : Controllers
				.getControllers()) {
			if (c.getPov(0) == PovDirection.north)
				dy -= Gdx.graphics.getDeltaTime() * 500;
			if (c.getPov(0) == PovDirection.south)
				dy += Gdx.graphics.getDeltaTime() * 500;
			if (c.getPov(0) == PovDirection.east)
				dx -= Gdx.graphics.getDeltaTime() * 500;
			if (c.getPov(0) == PovDirection.west)
				dx += Gdx.graphics.getDeltaTime() * 500;

			if (c.getPov(0) == PovDirection.northEast) {
				dy -= Gdx.graphics.getDeltaTime() * 500;
				dx -= Gdx.graphics.getDeltaTime() * 500;
			}
			if (c.getPov(0) == PovDirection.southEast) {
				dy += Gdx.graphics.getDeltaTime() * 500;
				dx -= Gdx.graphics.getDeltaTime() * 500;
			}
			if (c.getPov(0) == PovDirection.northWest) {
				dy -= Gdx.graphics.getDeltaTime() * 500;
				dx += Gdx.graphics.getDeltaTime() * 500;
			}
			if (c.getPov(0) == PovDirection.southWest) {
				dy += Gdx.graphics.getDeltaTime() * 500;
				dx += Gdx.graphics.getDeltaTime() * 500;
			}

		}
	}

}
