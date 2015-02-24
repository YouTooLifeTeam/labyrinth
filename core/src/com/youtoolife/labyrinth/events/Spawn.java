package com.youtoolife.labyrinth.events;

import org.w3c.dom.Element;

import com.badlogic.gdx.Gdx;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.IIController;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.Mob;
import com.youtoolife.labyrinth.units.Player;
import com.youtoolife.labyrinth.units.SadMob;
import com.youtoolife.labyrinth.units.Unit;

public class Spawn extends Event {

	int x, y;
	int destX, destY;
	String mob;

	float COOLDOWN = 5;
	float respawning = 0;

	public Spawn(Element mob) {
		x = Integer.valueOf(mob.getAttribute("x"));
		y = Integer.valueOf(mob.getAttribute("y"));
		destX = Integer.valueOf(mob.getAttribute("destY"));
		destY = Integer.valueOf(mob.getAttribute("destY"));
		COOLDOWN = Integer.valueOf(mob.getAttribute("Cooldown"));

		this.mob = mob.getAttribute("mob");
		source = mob;
	}

	@Override
	public void check(Chunk chunk) {
		respawning -= Gdx.graphics.getDeltaTime();
		if (respawning < 0)
			respawning = 0;

		if (rotations % 2 == 0) {
			Player p = GamePlayState.player1;
			if (p.x == x && (Chunk.SIZE - 1 - p.y) == y)
				invoke(chunk, p, 0, 0);

			p = GamePlayState.player2;
			if (p.x == x && (Chunk.SIZE - 1 - p.y) == y)
				invoke(chunk, p, 0, 0);

			for (int i = 0; i < chunk.mobs.size(); i++) {
				Mob m = chunk.mobs.get(i);
				if (m.x == x && (Chunk.SIZE - 1 - m.y) == y)
					invoke(chunk, m, 0, 0);
			}
		} else {
			Player p = GamePlayState.player1;
			if (Chunk.SIZE - 1 - p.x == x && p.y == y)
				invoke(chunk, p, 0, 0);
			p = GamePlayState.player2;
			if (Chunk.SIZE - 1 - p.x == x && p.y == y)
				invoke(chunk, p, 0, 0);

			for (int i = 0; i < chunk.mobs.size(); i++) {
				Mob m = chunk.mobs.get(i);
				if (Chunk.SIZE - 1 - m.x == x && m.y == y)
					invoke(chunk, m, 0, 0);
			}
		}
	}

}
