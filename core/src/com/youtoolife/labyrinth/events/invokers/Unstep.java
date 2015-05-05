package com.youtoolife.labyrinth.events.invokers;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.events.InvokeEvent;
import com.youtoolife.labyrinth.units.Unit;

public class Unstep extends InvokeEvent {

	int x, y;
	boolean lastValue = false;
	
	public Unstep(Element e) {
		super(e);
		x = Integer.parseInt(e.getAttribute("x"));
		y = Integer.parseInt(e.getAttribute("y"));
	}

	@Override
	public void check(Chunk chunk) {
		GameObject tile = null;
		if((tile.here==null)!=lastValue){
			if(tile.here==null)
				invoke(chunk, null);
			lastValue = tile.here==null;
		}

	}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		for (ActionEvent e : events)
			e.invoke(chunk, unit);
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
		Unstep step = new Unstep(this.base);
		for (int i = 0; i < rotates; i++)
			step.rotate();
		return step;
	}

}
