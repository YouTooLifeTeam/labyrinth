package com.youtoolife.labyrinth.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Name2Player extends Player {

	public final float COOLDOWN = 5;
	public float arrow_cooldown = COOLDOWN;
	
	public Name2Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
		sprite = new AnimatedSprite(0, 0, 50, 75, new Sprite(
				Assets.getTexture("player")), 0);
		sprite.setSize(50, 50);
		sprite.setPreferedDelta(50/moveSpeed/4);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		x = 6;
		GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1
			         								- y][x].here = this;
	}



	@Override
	public void update() {
		super.update();
		arrow_cooldown -= Gdx.graphics.getDeltaTime();
		if(arrow_cooldown<0){
			arrow_cooldown = 0;
			torch_color = new Color(1,0.75f,0,0.6f);
		}
			
	}
	
	@Override
	public void useAbility() {
		if(arrow_cooldown<=0){
			arrow_cooldown = COOLDOWN;
			torch_color = new Color(0.16f,0.72f,0.33f,0.6f);
		}
	}

}
