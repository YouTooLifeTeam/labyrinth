package com.youtoolife.labyrinth.chunk;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.youtoolife.labyrinth.events.Event;

public class EventsResolver {

	public static Vector<Event> getEvents(Element events){
		Vector<Event> list = new Vector<Event>();
		NodeList ev = events.getElementsByTagName("Event");
		for (int i = 0; i < ev.getLength(); i++) {
			Event buf = Event.getEvent((Element) ev.item(i));
			if(buf!=null)
				list.add(buf);
		}
		return list;
	}
	
}
