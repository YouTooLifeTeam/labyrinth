package com.youtoolife.labyrinth.units.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Arrow extends Unit {

	Texture normal_map;
	int[] direction;
	final int damage = 2;

	public Arrow(int x, int y, Chunk chunk, int[] direction) {
		super(0, 0, null);
		sprite = new AnimatedSprite(0, 0, 50, 50,
				Assets.getTexture("objects/Arrow"), 0);
		this.normal_map = Assets.getTexture("normal_map/blank");
		hp = 10;
		chunk.map[Chunk.SIZE - 1 - y][x].here = this;
		this.direction = direction;
		this.moveSpeed = 600;
		this.x = x;
		this.y = y;
		if (direction[0] == -1)
			sprite.setRotation(90);
		if (direction[0] == 1)
			sprite.setRotation(-90);
		if (direction[1] == 1)
			sprite.setRotation(0);
		if (direction[1] == -1)
			sprite.setRotation(180);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		// normal_map.bind(1);
		// this.sprite.getTexture().bind(0);
		super.draw(batch, x, y+25);
	}

	@Override
	public void update(Chunk chunk) {
		if ((xOffset != 0) || (yOffset != 0)) {
			sprite.update(Gdx.graphics.getDeltaTime());
			int speedX = xOffset < 0 ? 1 : -1;
			speedX = xOffset == 0 ? 0 : speedX;
			int speedY = yOffset < 0 ? 1 : -1;
			speedY = yOffset == 0 ? 0 : speedY;

			xOffset += speedX * moveSpeed * Gdx.graphics.getDeltaTime();
			yOffset += speedY * moveSpeed * Gdx.graphics.getDeltaTime();

			if (xOffset * speedX > 0)
				xOffset = 0;
			if (yOffset * speedY > 0)
				yOffset = 0;
		} else

		if (x + direction[0] >= 0 && x + direction[0] <= Chunk.SIZE - 1
				&& y + direction[1] >= 0 && y + direction[1] <= Chunk.SIZE - 1) {
			chunk.map[Chunk.SIZE - 1 - (y + direction[1])][x + direction[0]]
					.stepOnit(chunk, this, direction[0], direction[1]);
			
			if (chunk.map[Chunk.SIZE - 1 - (y + direction[1])][x + direction[0]]
					.canStep()) {
				chunk.map[Chunk.SIZE - 1 - y][x].here = null;
				x += direction[0];
				xOffset -= direction[0] * 50;
				y += direction[1];
				yOffset -= direction[1] * 50;
				chunk.map[Chunk.SIZE - 1 - y][x].here = this;
			} else {
				if (chunk.map[Chunk.SIZE - 1 - (y + direction[1])][x
						+ direction[0]].here != null) {
					chunk.map[Chunk.SIZE - 1 - (y + direction[1])][x
							+ direction[0]].here.hp -= damage;
					chunk.map[Chunk.SIZE - 1 - (y + direction[1])][x
							+ direction[0]].addRandomBlood();
				}
				for (int i = 0; i < chunk.mobs.size(); i++)
					if (chunk.mobs.get(i) == this) {
						chunk.map[Chunk.SIZE - 1 - y][x].here = null;
						chunk.mobs.remove(i);
						break;
					}
			}
		}
	}

	@Override
	public void useAbility() {
	}

}
