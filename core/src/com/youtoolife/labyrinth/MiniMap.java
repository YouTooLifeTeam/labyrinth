package com.youtoolife.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youtoolife.labyrinth.Chunk.Exits;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.Assets;

public class MiniMap {

	boolean[][] map;

	Texture[] room = new Texture[6];// 0 - wall;1-exit;2o - exit; 3 - 3exitl 4 -
									// 4 exit, 5 - 2b - exit

	public MiniMap(int SIZE) {
		map = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				map[i][j] = false;
		room[0] = Assets.getTexture("minimap/wall");
		room[1] = Assets.getTexture("minimap/1sectioned");
		room[2] = Assets.getTexture("minimap/2sectionedo");
		room[3] = Assets.getTexture("minimap/3sectioned");
		room[4] = Assets.getTexture("minimap/4sectioned");
		room[5] = Assets.getTexture("minimap/2sectionedb");
	}

	public void setViewed(int x, int y) {
		map[y][x] = true;
	}

	public void draw(SpriteBatch batch) {
		float chunk_size = 500 / GamePlayState.SIZE;
		for (int i = 0; i < GamePlayState.SIZE; i++)
			for (int j = 0; j < GamePlayState.SIZE; j++)
				if (map[i][j]) {
					TextureRegion texture = new TextureRegion(room[0]);
					if (GamePlayState.chunks[i][j].type == Exits.SingleExit)
						texture = new TextureRegion(room[1]); 
					if (GamePlayState.chunks[i][j].type == Exits.DiOpposite)
						texture = new TextureRegion(room[2]);
					if (GamePlayState.chunks[i][j].type == Exits.TriExit)
						texture = new TextureRegion(room[3]);
					if (GamePlayState.chunks[i][j].type == Exits.QuadroExit)
						texture = new TextureRegion(room[4]);
					if (GamePlayState.chunks[i][j].type == Exits.DiNeighbour)
						texture = new TextureRegion(room[5]);
					batch.draw(texture, j * chunk_size - GamePlayState.SIZE
							* chunk_size / 2, i * chunk_size  
							- GamePlayState.SIZE * chunk_size / 2,
							chunk_size / 2, chunk_size / 2, chunk_size,
							chunk_size, 1, 1, -90
									* GamePlayState.chunks[i][j].rotates);
				 }
	}

}
