package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;

public class Wall extends GameObject {

	public Wall(Texture texture, int id) {
		super(BlockType.Wall, texture, id);
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
		return new Wall(this.main_texture, this.getId());
	}

}
