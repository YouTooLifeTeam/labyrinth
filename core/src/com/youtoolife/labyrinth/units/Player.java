package com.youtoolife.labyrinth.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.renderer.Light;
import com.youtoolife.labyrinth.states.GamePlayState;

public abstract class Player extends Unit {

	public Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
		torch = new Light(0f,0f, torch_color);
		torch.exclude = this;
	}

	final float moveSpeed = 200;

	Color torch_color = new Color(1, 0.75f, 0, 0.1f);
	Light torch;
	
	
	public Light getLight(float x, float y) {
		torch.setPosition(new Vector3(this.x * 50 + xOffset + x - 50 * Chunk.SIZE / 2
				+ MainGame.w / 2 + 25, this.y * 50 + yOffset + y - 50
				* Chunk.SIZE / 2 + MainGame.h / 2 + 25,0));
		return torch;
	}

	@Override
	public void update(Chunk chunk){
		super.update(chunk);
		Vector3 position = new Vector3();
		
		position.x = this.x * 50 + xOffset + GamePlayState.XOffset * 50 * Chunk.SIZE - 50 * Chunk.SIZE / 2
				+ MainGame.w / 2 + 25;
		position.y = this.y * 50 + yOffset + GamePlayState.YOffset * 50 * Chunk.SIZE - 50
				* Chunk.SIZE / 2 + MainGame.h / 2 + 25;
		position.z = 0;
		torch.setColor(torch_color);
		torch.setPosition(position);
	}
	
}
