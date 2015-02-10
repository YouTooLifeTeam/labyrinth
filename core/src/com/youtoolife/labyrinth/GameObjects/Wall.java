package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public class Wall extends GameObject {

	public Wall(Texture texture) {
		super(BlockType.Wall, texture, 0);
	}

	@Override
	public boolean canStep() {
		return false;
	}

	@Override
	public void update() {
	}

	@Override
	public GameObject copy() {
		return new Wall(this.main_texture);
	}

	@Override
	public void stepOnit(Chunk chunk, Unit player, int dx, int dy) {

	}

}
