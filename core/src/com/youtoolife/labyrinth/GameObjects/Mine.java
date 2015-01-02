package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.utils.Assets;

public class Mine extends GameObject {

	Texture mine;
	
	public Mine(BlockType type, Texture texture) {
		super(type, texture);
		mine = Assets.getTexture("mine");
	}

	public void draw(SpriteBatch batch, float x, float y){
		super.draw(batch, x, y);
		batch.draw(mine, x, y, 50, 50);
	}
	
}
