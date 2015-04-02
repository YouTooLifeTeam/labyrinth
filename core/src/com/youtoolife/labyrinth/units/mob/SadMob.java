package com.youtoolife.labyrinth.units.mob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class SadMob extends Mob {

	Texture dead;

	public SadMob(int ChunkX, int ChunkY, Controller control, int x, int y) {
		super(ChunkX, ChunkY, control, x, y);
		sprite = new AnimatedSprite(0, 0, 50, 50, Assets.getTexture("mob/sad"),
				0);
		this.normal_map = Assets.getTexture("normal_map/sad_map");
		moveSpeed = moveSpeed * 0.6f;
		sprite.setPreferedDelta(50 / moveSpeed / 3);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		hp = 1;
		dead = Assets.getTexture("mob/dead/sad");
		GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = this;
	}

	@Override
	public void update(Chunk chunk) {
		if (hp > 0)
			super.update(chunk);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		if (hp > 0)
			super.draw(batch, x, y);
		else
			batch.draw(dead, this.x * 50 + x - 50 * Chunk.SIZE / 2 + MainGame.w
					/ 2, this.y * 50 + y - 50 * Chunk.SIZE / 2 + MainGame.h / 2);

	}

	@Override
	public void useAbility() {

	}

}
