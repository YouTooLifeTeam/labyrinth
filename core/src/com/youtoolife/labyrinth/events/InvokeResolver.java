package com.youtoolife.labyrinth.events;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.events.invokers.EnterChunk;
import com.youtoolife.labyrinth.events.invokers.ExitChunk;
import com.youtoolife.labyrinth.events.invokers.Step;
import com.youtoolife.labyrinth.events.invokers.Unstep;

public class InvokeResolver {

	public static InvokeEvent getEvent(Element e){
		
		InvokeEvent buf = null;
		
		if(e.getAttribute("type").equals("Step"))
			buf = new Step(e);
		if(e.getAttribute("type").equals("Unstep"))
			buf = new Unstep(e);
		if(e.getAttribute("type").equals("ExitChunk"))
			buf = new ExitChunk(e);
		if(e.getAttribute("type").equals("EnterChunk"))
			buf = new EnterChunk(e);
		
		return buf;
		
	}
	
}
