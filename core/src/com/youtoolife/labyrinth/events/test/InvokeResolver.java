package com.youtoolife.labyrinth.events.test;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.events.test.invokers.Step;

public class InvokeResolver {

	public static InvokeEvent getEvent(Element e){
		
		InvokeEvent buf = null;
		
		if(e.getAttribute("type").equals("Step"))
			buf = new Step(e);
		
		return buf;
		
	}
	
}
