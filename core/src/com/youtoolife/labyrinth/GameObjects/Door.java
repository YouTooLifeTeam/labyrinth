package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Door extends GameObject {

	public Door(Texture texture, int id) {
		super(BlockType.Door, texture,id);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		if(isActive)
			batch.setColor(0.1f, 0.7f, 0.1f, 1f);
		else
			batch.setColor(0.7f, 0.1f, 0.1f, 1f);
		super.draw(batch, x, y);
		batch.setColor(1, 1, 1, 1);
	}
	
	@Override
	public boolean canStep(){
		return isActive&&here==null;
	}
	
	@Override
	public GameObject copy(){
		return new Door(this.main_texture,this.getId());
	}

	@Override
	public void update() {
	}

}
