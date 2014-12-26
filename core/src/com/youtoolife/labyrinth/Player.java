package com.youtoolife.labyrinth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.GameObjects.GameObject.BlockType;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.Controller.Action;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Player {

	float xOffset = 0;
	float yOffset = 0;

	public int ChunkX;
	public int ChunkY;
	int x = 4, y = 4;

	AnimatedSprite sprite;
	Controller control;

	public Player(int ChunkX, int ChunkY, Controller control) {
		sprite = new AnimatedSprite(0, 0, 50, 50, new Sprite(
				Assets.getTexture("player")), 0);
		sprite.setPreferedDelta(0.2f);
		this.ChunkX = ChunkX;
		this.ChunkY = ChunkY;
		this.control = control;
	}

	public void draw(SpriteBatch batch, float x, float y) {
		sprite.setPosition(this.x * 50 + xOffset + x - 50*Chunk.SIZE/2, this.y * 50
				+ yOffset + y - 50*Chunk.SIZE/2);
		sprite.draw(batch);
	}

	public void update() {

		if ((xOffset != 0) || (yOffset != 0)) {
			sprite.update(Gdx.graphics.getDeltaTime());
			int speedX = xOffset < 0 ? 1 : -1;
			speedX = xOffset == 0 ? 0 : speedX;
			int speedY = yOffset < 0 ? 1 : -1;
			speedY = yOffset == 0 ? 0 : speedY;

			xOffset += speedX * 100 * Gdx.graphics.getDeltaTime();
			yOffset += speedY * 100 * Gdx.graphics.getDeltaTime();

			if (xOffset * speedX > 0)
				xOffset = 0;
			if (yOffset * speedY > 0)
				yOffset = 0;
		}
		Action action = control.getAction();
		
		if (action != Action.None) {
			if (xOffset == 0) {
				int dir = 0;
				if (action == Action.Left)
					dir = -1;
				if (action == Action.Right)
					dir = 1;
				if (x + dir >= 0 && x + dir <= Chunk.SIZE - 1) {
					if (GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x + dir].type == BlockType.Floor) {
						x += dir;
						xOffset = -dir * 50;
					}
				} else {
					ChunkX += dir;
					x = Chunk.SIZE - 1 - x;
					xOffset = -dir * 50;
				}
			}
			if (yOffset == 0) {
				int dir = 0;
				if (action == Action.Up)
					dir = 1;
				if (action == Action.Down)
					dir = -1;
				if (y + dir >= 0 && y + dir <= Chunk.SIZE - 1) {
					if (GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - (y + dir)][x].type == BlockType.Floor) {
						y += dir;
						yOffset = -dir * 50;
					}
				} else {
					ChunkY += dir;
					y = Chunk.SIZE - 1 - y;
					yOffset = -dir * 50;
				}
			}
		}
	}

}
