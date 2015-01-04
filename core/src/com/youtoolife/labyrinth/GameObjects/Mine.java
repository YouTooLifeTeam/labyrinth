package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.Event;
import com.youtoolife.labyrinth.events.Explosion;
import com.youtoolife.labyrinth.player.Player;
import com.youtoolife.labyrinth.utils.Assets;

public class Mine extends GameObject {

	Texture mine;
	boolean isActive = false;
	float activating = 5f;

	Event explosion;

	public Mine(BlockType type, Texture texture, int x, int y) {
		super(type, texture);
		mine = Assets.getTexture("mine");
		explosion = new Explosion(x, y, 1);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		super.draw(batch, x, y);
		if (isActive)
			batch.setColor(1, 1, 1, 1);
		else
			batch.setColor(0.4f, 0.4f, 0.4f, 1f);
		batch.draw(mine, x + 10, y + 10, 30, 30);
		batch.setColor(1, 1, 1, 1);
	}

	@Override
	public void stepOnit(Chunk chunk, Player player, int dx, int dy) {
		if (isActive)
			explosion.invoke(chunk, player, dx, dy);
	}

	@Override
	public void update() {
		if (!isActive)
			activating -= Gdx.graphics.getDeltaTime();
		if (activating <= 0)
			isActive = true;
	}

}
