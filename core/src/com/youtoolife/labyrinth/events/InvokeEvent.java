package com.youtoolife.labyrinth.events;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;

public abstract class InvokeEvent {

	public Element base;
	public int rotates = 0;

	public Vector<ActionEvent> events = new Vector<ActionEvent>();

	public InvokeEvent(Element e){
		this.base = e;
		NodeList evs = e.getElementsByTagName("ActionEvent");
		for(int i = 0;i<evs.getLength();i++){
			events.add(ActionResolver.getEvent((Element) (evs.item(i))));
		}
	}

	public abstract void check(Chunk chunk);

	public abstract void invoke(Chunk chunk, Unit unit);

	public abstract void rotate();

	public abstract InvokeEvent copy();

}