package com.youtoolife.labyrinth.events.actions;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.units.mob.MobResolver;

public class Spawn extends ActionEvent {

	int x,y;
	String mob;
	
	public Spawn(Element e) {
		super(e);
		x = Integer.valueOf(e.getAttribute("x"));
		y = Integer.valueOf(e.getAttribute("y"));
		mob = e.getAttribute("mob");
	}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		int dx = x;
		int dy = y;

		if (chunk.rotates % 2 == 0)
			dy = Chunk.SIZE - 1 - dy;
		else
			dx = Chunk.SIZE - 1 - dx;

		Unit buf = MobResolver.getMob(chunk, mob, dx, dy);
		chunk.mobs.add(buf);
	}

	@Override
	public void rotate() {
		int by = Chunk.SIZE - 1 - x;
		int bx = y;
		y = by;
		x = bx;
	}

}
