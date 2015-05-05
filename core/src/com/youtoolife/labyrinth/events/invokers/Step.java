package com.youtoolife.labyrinth.events.invokers;

import org.w3c.dom.Element;

import com.badlogic.gdx.Gdx;
import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.events.InvokeEvent;
import com.youtoolife.labyrinth.units.Unit;

public class Step extends InvokeEvent {

	int x, y;
	float COOLDOWN;
	float refresh_delay = 0;

	public Step(Element e) {
		super(e);
		x = Integer.parseInt(e.getAttribute("x"));
		y = Integer.parseInt(e.getAttribute("y"));
		COOLDOWN = Float.parseFloat(e.getAttribute("Cooldown"));
	}

	@Override
	public void check(Chunk chunk) {
		refresh_delay -= Gdx.graphics.getDeltaTime();
		GameObject tile = chunk.map[y][x];
		// tile.addRandomBlood();
		if (tile.isActive() && tile.here != null)
			invoke(chunk, tile.here);

	}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		if (refresh_delay <= 0) {
			refresh_delay = COOLDOWN;
			for (ActionEvent e : events)
				e.invoke(chunk, unit);
		}
	}

	@Override
	public void rotate() {
		int by = Chunk.SIZE - 1 - x;
		int bx = y;
		y = by;
		x = bx;
		for (ActionEvent e : events)
			e.rotate();
		rotates++;
	}

	@Override
	public InvokeEvent copy() {
		Step step = new Step(this.base);
		for (int i = 0; i < rotates; i++)
			step.rotate();
		return step;
	}

}
