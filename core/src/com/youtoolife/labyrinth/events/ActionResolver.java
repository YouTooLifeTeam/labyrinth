package com.youtoolife.labyrinth.events;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.events.actions.DeactivateObject;
import com.youtoolife.labyrinth.events.actions.ActivateObject;
import com.youtoolife.labyrinth.events.actions.Spawn;

public class ActionResolver {

	public static ActionEvent getEvent(Element e){
		ActionEvent buf = null;
		
		if(e.getAttribute("type").equals("Spawn"))
			buf = new Spawn(e);
		if(e.getAttribute("type").equals("ActivateObject"))
			buf = new ActivateObject(e);
		if(e.getAttribute("type").equals("DeactivateObject"))
			buf = new DeactivateObject(e);
		
		return buf;
	}
	
}
