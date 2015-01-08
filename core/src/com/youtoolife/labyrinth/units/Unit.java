package com.youtoolife.labyrinth.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.Controller.Action;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;

public abstract class Unit {

	public float xOffset = 0;
	public float yOffset = 0;

	public int hp = 4;

	public int ChunkX;
	public int ChunkY;
	public int x = 5, y = 5;

	float moveSpeed = 200;

	AnimatedSprite sprite;
	Controller control;

	public Unit(int ChunkX, int ChunkY, Controller control) {
		this.ChunkX = ChunkX;
		this.ChunkY = ChunkY;
		this.control = control;
	}

	public void draw(SpriteBatch batch, float x, float y) {
		sprite.setPosition(this.x * 50 + xOffset + x - 50 * Chunk.SIZE / 2
				+ MainGame.w / 2, this.y * 50 + yOffset + y - 50 * Chunk.SIZE
				/ 2 + MainGame.h / 2);
		sprite.draw(batch);
	}

	public void update() {

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
		Action action = control.getAction();

		if (action != Action.None) {

			int dirx = 0;
			int diry = 0;
			boolean isMove = false;
			if (xOffset == 0) {
				if (action == Action.Left) {
					dirx = -1;
					sprite.setAnimStart(4);
					sprite.setAnimStop(7);
					isMove = true;
				}
				if (action == Action.Right) {
					dirx = 1;
					sprite.setAnimStart(8);
					sprite.setAnimStop(11);
					isMove = true;
				}
			}
			if (yOffset == 0) {
				if (action == Action.Up) {
					diry = 1;
					sprite.setAnimStart(12);
					sprite.setAnimStop(15);
					isMove = true;
				}
				if (action == Action.Down) {
					diry = -1;
					sprite.setAnimStart(0);
					sprite.setAnimStop(3);
					isMove = true;
				}
			}
			if (isMove) {
				if (x + dirx >= 0 && x + dirx <= Chunk.SIZE - 1
						&& y + diry >= 0 && y + diry <= Chunk.SIZE - 1)
					GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1
							- (y + diry)][x + dirx].stepOnit(
							GamePlayState.chunks[ChunkY][ChunkX], this, dirx,
							diry);

				if (x + dirx >= 0 && x + dirx <= Chunk.SIZE - 1
						&& y + diry >= 0 && y + diry <= Chunk.SIZE - 1) {
					if (GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1
							- (y + diry)][x + dirx].canStep()) {
						GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1
								- y][x].here = null;
						x += dirx;
						xOffset += -dirx * 50;
						y += diry;
						yOffset += -diry * 50;
						GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1
								- y][x].here = this;
					}
				} else {
					GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = null;
					ChunkX += dirx;
					x = dirx==0?x:Chunk.SIZE - 1 - x;
					xOffset += -dirx * 50;
					ChunkY += diry;
					y = diry==0?y:Chunk.SIZE - 1 - y;
					yOffset += -diry * 50;
					GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = this;
				}
			} else {
				if (action == Action.SpecAction)
					useAbility();
			}
		}
	}

	public abstract void useAbility();

}
