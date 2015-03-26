package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Floor extends GameObject {

	public Floor(Texture texture, int id) {
		super(BlockType.Floor, texture, id);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		super.draw(batch, x, y);
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public GameObject copy() {
		return new Floor(this.main_texture, this.getId());
	}

	@Override
	public boolean canStep() {
		return here==null;
	}

}
