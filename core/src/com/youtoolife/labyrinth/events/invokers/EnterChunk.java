package com.youtoolife.labyrinth.events.invokers;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.events.InvokeEvent;
import com.youtoolife.labyrinth.units.Unit;

public class EnterChunk extends InvokeEvent {

	public EnterChunk(Element e) {
		super(e);
	}

	@Override
	public void check(Chunk chunk) {}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		for(ActionEvent e: events)
			e.invoke(chunk, unit);
	}

	@Override
	public void rotate() {
		for(ActionEvent e: events)
			e.rotate();
		rotates++;
	}

	@Override
	public InvokeEvent copy() {
		EnterChunk enter = new EnterChunk(this.base);
		for(int i = 0; i<rotates;i++)
			enter.rotate();
		return enter;
	}

}
