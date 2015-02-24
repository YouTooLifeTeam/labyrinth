package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.utils.Assets;

public class Floor extends GameObject {

	Texture normal;
	
	public Floor(Texture texture) {
		super(BlockType.Floor, texture,0);
		normal = Assets.getTexture("normal_map/floor_normal");
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		normal.bind(1);
		main_texture.bind(0);
		super.draw(batch, x, y);
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public GameObject copy() {
		return new Floor(this.main_texture);
	}

	@Override
	public void stepOnit(Chunk chunk, Unit player, int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canStep() {
		return here==null;
	}

}
