package com.youtoolife.labyrinth.events;

import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.player.Player;

public class Explosion extends Event {

	int x, y;
	int damage;

	public Explosion(int x, int y, int damage) {
		this.x = x;
		this.y = y;
		this.damage = damage;
	}

	@Override
	public void invoke(Chunk chunk, Player invoker, int dx, int dy) {
		invoker.hp -= damage;
		GameObject buf = chunk.map[Chunk.SIZE - 1 - invoker.y - dy][invoker.x+ dx]
				.copy();
		buf.addRandomBlood();
		chunk.map[Chunk.SIZE - 1 - invoker.y - dy][invoker.x+ dx] = buf;

	}

	@Override
	public Event copy() {
		return new Explosion(x, y, damage);
	}

	@Override
	public void check(Chunk chunk) {

	}

}
