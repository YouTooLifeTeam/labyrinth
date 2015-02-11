package com.youtoolife.labyrinth.events.test;

import org.w3c.dom.Element;

public abstract class ActionEvent {

	Element base;
	
	public ActionEvent(Element e){
		base = e;
	}
	public abstract void invoke();
	public abstract void rotate();
	
	public static ActionEvent getEvent(Element e){
		
		return null;
	}
	
}