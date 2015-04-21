package com.youtoolife.labyrinth.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.objects.Arrow;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Name2Player extends Player {

	public final static float COOLDOWN = 5;
	public float arrow_cooldown = COOLDOWN;

	public Name2Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
		sprite = new AnimatedSprite(0, 0, 50, 75, Assets.getTexture("player"),
				0);
		sprite.setSize(50, 50);
		sprite.setPreferedDelta(50 / moveSpeed / 4);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		x = 6;
		GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = this;
	}

	@Override
	public void update(Chunk chunk) {
		super.update(chunk);
		arrow_cooldown -= Gdx.graphics.getDeltaTime();
		if (arrow_cooldown < 0) {
			arrow_cooldown = 0;
			torch_color = new Color(1, 0.75f, 0, 0.1f);
		}

	}

	@Override
	public void useAbility() {

		if (arrow_cooldown <= 0) {
			arrow_cooldown = COOLDOWN;
			// torch_color = new Color(0.16f, 0.72f, 0.33f, 0.1f);

			int dx = 0, dy = 0;
			switch (sprite.getAnimStart()) {
			case 0:
				dy = -1;
				break;
			case 4:
				dx = -1;
				break;
			case 8:
				dx = 1;
				break;
			case 12:
				dy = 1;
				break;
			}
			GamePlayState.chunks[ChunkY][ChunkX].mobs
					.add(new Arrow(x, y, GamePlayState.chunks[ChunkY][ChunkX],
							new int[] { dx, dy }));
		}
	}

}
