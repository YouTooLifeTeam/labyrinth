package com.youtoolife.labyrinth.units.mob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.IIController;
import com.youtoolife.labyrinth.units.Unit;

public abstract class Mob extends Unit{

	IIController control;
	
	Texture normal_map;
	
	public Mob(Chunk chunk, Controller control, int x, int y) {
		super(0,0, control);
		this.control = (IIController) control;
		this.x = x;
		this.y = y;
		chunk.map[Chunk.SIZE - 1 - y][x].here = this;
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		normal_map.bind(1);
		this.sprite.getTexture().bind(0);
		super.draw(batch, x, y);
	}
	
	@Override
	public void update(Chunk chunk){
		super.update(chunk);
		control.update(chunk, this);
	}
	
}
