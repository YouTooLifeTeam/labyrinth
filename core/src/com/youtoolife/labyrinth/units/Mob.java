package com.youtoolife.labyrinth.units;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.IIController;

public abstract class Mob extends Unit{

	IIController control;
	
	public Mob(int ChunkX, int ChunkY, Controller control, int x, int y) {
		super(ChunkX, ChunkY, control);
		this.control = (IIController) control;
		this.x = x;
		this.y = y;
	}
	
	public void update(Chunk chunk){
		super.update();
		control.update(chunk, this);
	}
	
}
