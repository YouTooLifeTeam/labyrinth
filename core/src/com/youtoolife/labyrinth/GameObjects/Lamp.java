package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public class Lamp extends GameObject {

	public Lamp(Texture texture) {
		super(BlockType.Lamp, texture, 0);
	}

	@Override
	public boolean canStep() {
		return false;
	}

	@Override
	public void update() {}

	@Override
	public GameObject copy() {
		return new Lamp(this.main_texture);
	}

	@Override
	public void stepOnit(Chunk chunk, Unit player, int dx, int dy) {}
}