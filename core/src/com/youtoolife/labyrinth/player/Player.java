package com.youtoolife.labyrinth.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.GameObjects.GameObject.BlockType;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.Controller.Action;
import com.youtoolife.labyrinth.mob.Unit;
import com.youtoolife.labyrinth.shaders.Light;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;

public abstract class Player implements Unit {

	float xOffset = 0;
	float yOffset = 0;

	public int hp = 4;

	public int ChunkX;
	public int ChunkY;
	public int x = 5, y = 5;

	final float moveSpeed = 200;

	Color torch_color = new Color(1, 0.75f, 0, 0.6f);

	AnimatedSprite sprite;
	Controller control;

	public Player(int ChunkX, int ChunkY, Controller control) {
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
							- (y + diry)][x + dirx].type == BlockType.Floor
							&& GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE
									- 1 - (y + diry)][x + dirx].here == null) {
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
					x = Chunk.SIZE - 1 - x;
					xOffset += -dirx * 50;
					ChunkY += diry;
					y = Chunk.SIZE - 1 - y;
					yOffset += -diry * 50;
					GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = this;
				}
			} else {
				if (action == Action.SpecAction)
					useAbility();
			}
		}
	}

	public Light getLight(float x, float y) {
		return new Light(this.x * 50 + xOffset + x - 50 * Chunk.SIZE / 2
				+ MainGame.w / 2 + 25, this.y * 50 + yOffset + y - 50
				* Chunk.SIZE / 2 + MainGame.h / 2 + 25, torch_color);
	}

	public abstract void useAbility();

}
