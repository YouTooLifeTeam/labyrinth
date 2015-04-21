package com.youtoolife.labyrinth.units.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Box extends Unit {

	Texture normal_map;

	public Box(Chunk chunk ,int x, int y) {
		super(0,0, null);
		sprite = new AnimatedSprite(0, 0, 50, 50,
				Assets.getTexture("objects/Box"), 0);
		this.normal_map = Assets.getTexture("normal_map/box_map");
		hp = 2;
		chunk.map[Chunk.SIZE - 1 - y][x].here = this;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y) {
		normal_map.bind(1);
		this.sprite.getTexture().bind(0);
		super.draw(batch, x, y);
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
		}
	}

	@Override
	public void useAbility() {
	}

	public void step(Chunk chunk, Unit player, int dx, int dy) {
		this.moveSpeed = player.moveSpeed;
		if (x + dx >= 0 && x + dx <= Chunk.SIZE - 1 && y + dy >= 0
				&& y + dy <= Chunk.SIZE - 1) {
			chunk.map[Chunk.SIZE - 1 - (y + dy)][x + dx].stepOnit(chunk, this,
					dx, dy);
			if (chunk.map[Chunk.SIZE - 1 - (y + dy)][x + dx].canStep()) {
				chunk.map[Chunk.SIZE - 1 - y][x].here = null;
				x += dx;
				xOffset += -dx * 50;
				y += dy;
				yOffset += -dy * 50;
				chunk.map[Chunk.SIZE - 1 - y][x].here = this;
			}
		}
	}

}
