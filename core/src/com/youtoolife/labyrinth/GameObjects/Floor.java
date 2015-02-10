package com.youtoolife.labyrinth.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.utils.Assets;

public class Floor extends GameObject {

	Texture normal;
	
	public Floor(Texture texture) {
		super(BlockType.Floor, texture,0);
		normal = Assets.getTexture("normal_map/floor_normal");
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		normal.bind(1);
		main_texture.bind(0);
		super.draw(batch, x, y);
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public GameObject copy() {
		return new Floor(this.main_texture);
	}

	public void addRandomBlood() {

		int num = MathUtils.random(9) + 1;
		additions.add(new Sprite(Assets.getTexture("blood/b-"
				+ String.valueOf(num))));
		additions.get(additions.size() - 1).setRotation(
				MathUtils.random(360f));
		additions.get(additions.size() - 1).setColor(1, 1, 1, 0.6f);
	}

	public void addRandomGap() {

		int num = MathUtils.random(9) + 1;
		additions.add(new Sprite(Assets.getTexture("gaps/g-"
				+ String.valueOf(num))));
	}

	@Override
	public void stepOnit(Chunk chunk, Unit player, int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canStep() {
		return here==null;
	}

}
