package com.youtoolife.labyrinth.chunk;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.youtoolife.labyrinth.events.test.InvokeEvent;
import com.youtoolife.labyrinth.events.test.InvokeResolver;

public class EventsResolver {

	public static Vector<InvokeEvent> getEvents(Element events){
		Vector<InvokeEvent> list = new Vector<InvokeEvent>();
		
		NodeList ev = events.getElementsByTagName("InvokeEvent");
		for (int i = 0; i < ev.getLength(); i++) {
			InvokeEvent buf = InvokeResolver.getEvent((Element) ev.item(i));
			if(buf!=null)
				list.add(buf);
		}
		
		return list;
	}
	
}