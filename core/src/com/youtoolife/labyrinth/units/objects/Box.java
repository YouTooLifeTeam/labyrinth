package com.youtoolife.labyrinth.units.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.units.Unit;

public class Box extends Unit{

	public Box(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y){
		
	}
	
	@Override
	public void update(Chunk chunk){
		
	}
	
	@Override
	public void useAbility() {}

}
