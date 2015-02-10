package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Door extends GameObject {

	boolean isOpen = true;
	
	public Door(Texture texture) {
		super(BlockType.Door, texture);

	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		if(isOpen)
			batch.setColor(0.1f, 0.7f, 0.1f, 1f);
		else
			batch.setColor(0.7f, 0.1f, 0.1f, 1f);
		super.draw(batch, x, y);
		batch.setColor(1, 1, 1, 1);
	}
	
	@Override
	public boolean canStep(){
		return isOpen&&here==null;
	}
	
	@Override
	public GameObject copy(){
		return new Door(this.texture);
	}
	
}
