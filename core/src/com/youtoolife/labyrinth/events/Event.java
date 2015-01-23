package com.youtoolife.labyrinth.events;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public abstract class Event {

	Element source;
	
	public int rotations;
	
	public abstract void check(Chunk chunk);

	public abstract void invoke(Chunk chunk, Unit invoker, int dx, int dy);

	public abstract Event copy();

	public static Event getEvent(Element e) {
		Event event = null;
		
		if(e.getAttribute("type").equals("Spawn"))
			event = new Spawn(e);
			
		return event;
	}

	public abstract void rotateClockwise();

}
