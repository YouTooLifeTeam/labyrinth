package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.utils.Assets;

public class Mine extends GameObject {

	Texture mine;
	boolean isActive = false;
	float activating = 5f;

	public Mine(BlockType type, Texture texture) {
		super(type, texture);
		mine = Assets.getTexture("mine");
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		super.draw(batch, x, y);
		if (isActive)
			batch.setColor(1, 1, 1, 1);
		else
			batch.setColor(0.4f, 0.4f, 0.4f, 1f);
		batch.draw(mine, x+10, y+10, 30, 30);
		batch.setColor(1, 1, 1, 1);
	}

	@Override
	public void update() {
		if (!isActive)
			activating -= Gdx.graphics.getDeltaTime();
		if(activating<=0)
			isActive = true;
	}

}
