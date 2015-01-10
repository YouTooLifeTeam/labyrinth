package com.youtoolife.labyrinth.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.GameObjects.Mine;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Name1Player extends Player {

	public final float COOLDOWN = 6;
	public float mine_coolDown = COOLDOWN;

	public Name1Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
		sprite = new AnimatedSprite(0, 0, 50, 50, new Sprite(
				Assets.getTexture("player2")), 0);
		sprite.setPreferedDelta(50 / moveSpeed / 3);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		hp = 3;
		torch_color = new Color(0.8f, 0.7f, 0, 0.1f);
		GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = this;
	}

	@Override
	public void update() {
		super.update();
		mine_coolDown -= Gdx.graphics.getDeltaTime();
		if (mine_coolDown < 0)
			mine_coolDown = 0;
	}

	@Override
	public void useAbility() {
		if (mine_coolDown <= 0) {
			mine_coolDown = COOLDOWN;
			GameObject buf = GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE
					- y - 1][x];
			GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - y - 1][x] = new Mine(
					buf.type, buf.texture, x, y);
			GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - y - 1][x].here = this;
		}
	}

}
