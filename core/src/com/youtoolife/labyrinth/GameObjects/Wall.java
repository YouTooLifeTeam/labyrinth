package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;

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

}
