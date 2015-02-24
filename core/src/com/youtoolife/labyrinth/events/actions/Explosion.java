package com.youtoolife.labyrinth.events.actions;

import org.w3c.dom.Element;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.units.Unit;

public class Explosion extends ActionEvent {

	int damage = 1;
	public Explosion(Element e) {
		super(e);
		damage = Integer.parseInt(e.getAttribute("damage"));
	}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		unit.hp-=damage;
	}

	@Override
	public void rotate() {}

}
