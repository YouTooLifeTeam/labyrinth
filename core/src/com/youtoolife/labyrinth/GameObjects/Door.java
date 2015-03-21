package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public class Door extends GameObject {

	boolean isOpen = true;
	
	public Door(Texture texture, int id) {
		super(BlockType.Door, texture,id);
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
		return new Door(this.main_texture,this.getId());
	}

	public void setOpen(boolean isOpen){
		this.isOpen = isOpen;
	}
	
	@Override
	public void update() {
	}

	@Override
	public void stepOnit(Chunk chunk, Unit player, int dx, int dy) {
	}
	
}
