package com.youtoolife.labyrinth.events;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public abstract class ActionEvent {

	public Element base;
	
	public ActionEvent(Element e){
		base = e;
	}
	public abstract void invoke(Chunk chunk, Unit unit);
	public abstract void rotate();
}