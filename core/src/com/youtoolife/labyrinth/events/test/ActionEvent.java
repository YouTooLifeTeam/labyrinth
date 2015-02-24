package com.youtoolife.labyrinth.events.test;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public abstract class ActionEvent {

	Element base;
	
	public ActionEvent(Element e){
		base = e;
	}
	public abstract void invoke(Chunk chunk, Unit unit);
	public abstract void rotate();
}