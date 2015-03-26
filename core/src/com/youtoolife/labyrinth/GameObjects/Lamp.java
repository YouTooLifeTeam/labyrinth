package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;

public class Lamp extends GameObject {

	public Lamp(Texture texture, int id) {
		super(BlockType.Lamp, texture, id);
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
		return new Lamp(this.main_texture, this.getId());
	}
}