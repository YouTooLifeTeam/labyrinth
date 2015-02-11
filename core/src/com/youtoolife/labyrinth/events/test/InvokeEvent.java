package com.youtoolife.labyrinth.events.test;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class InvokeEvent {

	Element base;
	private int rotates = 0;

	Vector<ActionEvent> events = new Vector<ActionEvent>();

	public InvokeEvent(Element e){
		this.base = e;
		NodeList evs = e.getChildNodes();
		for(int i = 0;i<evs.getLength();i++)
			events.add(ActionEvent.getEvent((Element) (evs.item(i))));
	}

	public abstract void check();

	public abstract void invoke();

	public abstract void rotate();

	public abstract InvokeEvent copy();

}