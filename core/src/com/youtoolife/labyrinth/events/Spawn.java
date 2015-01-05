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

	int rotations = 0;

	Element source;

	public Spawn(Element mob) {
		x = Integer.valueOf(mob.getAttribute("x"));
		y = Integer.valueOf(mob.getAttribute("y"));
		destX = Integer.valueOf(mob.getAttribute("destY"));
		destY = Integer.valueOf(mob.getAttribute("destY"));

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
			if (p.x == x && (Chunk.SIZE - 1 - p.y) == y) {
				invoke(chunk, p, 0, 0);
				return;
			}
			p = GamePlayState.player2;
			if (p.x == x && (Chunk.SIZE - 1 - p.y) == y) {
				invoke(chunk, p, 0, 0);
			}
		}else{
			Player p = GamePlayState.player1;
			if (Chunk.SIZE - 1 - p.x == x && p.y == y) {
				invoke(chunk, p, 0, 0);
				return;
			}
			p = GamePlayState.player2;
			if (Chunk.SIZE - 1 - p.x == x && p.y == y) {
				invoke(chunk, p, 0, 0);
			}
		}
	}

	@Override
	public void invoke(Chunk chunk, Unit invoker, int dx, int dy) {
		if (respawning <= 0) {
			
			int ddx = destX;
			int ddy = destY;
			
			if (rotations % 2 == 0)
				ddy = Chunk.SIZE - 1 - ddy;
			else
				ddx = Chunk.SIZE - 1 - ddx;
				
			Mob buf = null;
			if (mob.equals("Sad"))
				buf = new SadMob(invoker.ChunkX, invoker.ChunkY,
						new IIController(), ddx, ddy);
			chunk.mobs.add(buf);
			respawning = COOLDOWN;
		}
	}

	@Override
	public void rotateClockwise() {
		int by = Chunk.SIZE - 1 - destX;
		int bx = destY;
		destY = by;
		destX = bx;

		by = Chunk.SIZE - 1 - x;
		bx = y;
		y = by;
		x = bx;

		rotations++;
	}

	@Override
	public Event copy() {
		source.setAttribute("x", String.valueOf(x));
		source.setAttribute("y", String.valueOf(y));
		source.setAttribute("destX", String.valueOf(destX));
		source.setAttribute("destY", String.valueOf(destY));
		Spawn a = new Spawn(source);
		a.rotations = rotations;
		return a;
	}

}
