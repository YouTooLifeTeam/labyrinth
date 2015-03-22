package com.youtoolife.labyrinth.units.mob;

import com.badlogic.gdx.Gdx;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.controller.Controller.Action;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.Player;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class SpikeMob extends Mob {

	final float coolDownTime = 1;
	final int damage = 2;
	private float coolDown = 0;

	public SpikeMob(int ChunkX, int ChunkY, Controller control, int x, int y) {
		super(ChunkX, ChunkY, control, x, y);
		sprite = new AnimatedSprite(0, 0, 50, 50,
				Assets.getTexture("mob/spike"), 0);
		this.normal_map = Assets.getTexture("normal_map/spike_map");
		moveSpeed = moveSpeed * 0.6f;
		sprite.setPreferedDelta(0.25f);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
		GamePlayState.chunks[ChunkY][ChunkX].map[Chunk.SIZE - 1 - y][x].here = this;
		this.hp = Integer.MAX_VALUE;
	}

	@Override
	public void useAbility() {

	}

	@Override
	public void update(Chunk chunk) {
		control.update(chunk, this);
		sprite.update(Gdx.graphics.getDeltaTime());

		if (coolDown > 0)
			coolDown -= Gdx.graphics.getDeltaTime();

		if ((xOffset != 0) || (yOffset != 0)) {

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
					isMove = true;
				}
				if (action == Action.Right) {
					dirx = 1;
					isMove = true;
				}
			}
			if (yOffset == 0) {
				if (action == Action.Up) {
					diry = 1;
					isMove = true;
				}
				if (action == Action.Down) {
					diry = -1;
					isMove = true;
				}
			}
			if (isMove) {
				if (x + dirx >= 0 && x + dirx <= Chunk.SIZE - 1
						&& y + diry >= 0 && y + diry <= Chunk.SIZE - 1) {
					chunk.map[Chunk.SIZE - 1 - (y + diry)][x + dirx].stepOnit(
							chunk, this, dirx, diry);

					if (coolDown <= 0) {
						if (chunk.map[Chunk.SIZE - 1 - (y + diry)][x + dirx].here instanceof Player) {
							chunk.map[Chunk.SIZE - 1 - (y + diry)][x + dirx].here.hp -= damage;
							coolDown = coolDownTime;
						}
					}

					if (chunk.map[Chunk.SIZE - 1 - (y + diry)][x + dirx]
							.canStep()) {
						chunk.map[Chunk.SIZE - 1 - y][x].here = null;
						x += dirx;
						xOffset += -dirx * 50;
						y += diry;
						yOffset += -diry * 50;
						chunk.map[Chunk.SIZE - 1 - y][x].here = this;
					}
				}
			}
		}
	}

}
