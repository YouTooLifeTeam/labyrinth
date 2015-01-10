package com.youtoolife.labyrinth.units;

import com.badlogic.gdx.graphics.Color;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.shaders.Light;

public abstract class Player extends Unit {

	public Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
	}

	final float moveSpeed = 200;

	Color torch_color = new Color(1, 0.75f, 0, 0.1f);

	public Light getLight(float x, float y) {
		return new Light(this.x * 50 + xOffset + x - 50 * Chunk.SIZE / 2
				+ MainGame.w / 2 + 25, this.y * 50 + yOffset + y - 50
				* Chunk.SIZE / 2 + MainGame.h / 2 + 25, torch_color);
	}

}
