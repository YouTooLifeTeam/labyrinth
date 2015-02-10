package com.youtoolife.labyrinth.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class SadMob extends Mob{

	public SadMob(int ChunkX, int ChunkY, Controller control, int x, int y) {
		super(ChunkX, ChunkY, control, x , y);
		sprite = new AnimatedSprite(0, 0, 50, 50, new Sprite(
				Assets.getTexture("mob/sad")), 0);
		moveSpeed = moveSpeed*0.6f;
		sprite.setPreferedDelta(50/moveSpeed/3);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		hp = 2;
		GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1
			         								- y][x].here = this;	
		}

	@Override
	public void useAbility() {
		
	}

}
