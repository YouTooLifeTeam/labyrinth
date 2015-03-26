package com.youtoolife.labyrinth.events.actions;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.units.Unit;

public class ActivateObject extends ActionEvent {

	int id;

	public ActivateObject(Element e) {
		super(e);
		id = Integer.parseInt(e.getAttribute("id"));
	}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		for (GameObject[] buf : chunk.map)
			for (GameObject d : buf)
				if (d.getId() == this.id)
					d.setActive(true);
	}

	@Override
	public void rotate() {
	}

}