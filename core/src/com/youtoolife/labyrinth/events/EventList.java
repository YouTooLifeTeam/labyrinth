package com.youtoolife.labyrinth.events;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.chunk.EventsResolver;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.Mob;
import com.youtoolife.labyrinth.units.Player;
import com.youtoolife.labyrinth.units.Unit;

public class EventList extends Event {

	public enum Type {
		Step
	}

	Type type;
	Vector<Event> events;

	int x, y;

	float COOLDOWN = 5;
	float respawning = 0;

	int rotations = 0;

	public EventList(Element e) {
		x = Integer.valueOf(e.getAttribute("x"));
		y = Integer.valueOf(e.getAttribute("y"));

		type = Type.valueOf(e.getAttribute("type"));
		events = EventsResolver.getEvents(e);
		source = e;
	}

	@Override
	public void check(Chunk chunk) {
		respawning -= Gdx.graphics.getDeltaTime();
		if (respawning < 0)
			respawning = 0;

		if (type == Type.Step) {
			if (rotations % 2 == 0) {
				Player p = GamePlayState.player1;
				if (p.x == x && (Chunk.SIZE - 1 - p.y) == y)
					invoke(chunk, p, 0, 0);

				p = GamePlayState.player2;
				if (p.x == x && (Chunk.SIZE - 1 - p.y) == y)
					invoke(chunk, p, 0, 0);

				for (int i = 0; i < chunk.mobs.size(); i++) {
					Mob m = chunk.mobs.get(i);
					if (m.x == x && (Chunk.SIZE - 1 - m.y) == y)
						invoke(chunk, m, 0, 0);
				}
			} else {
				Player p = GamePlayState.player1;
				if (Chunk.SIZE - 1 - p.x == x && p.y == y)
					invoke(chunk, p, 0, 0);
				p = GamePlayState.player2;
				if (Chunk.SIZE - 1 - p.x == x && p.y == y)
					invoke(chunk, p, 0, 0);

				for (int i = 0; i < chunk.mobs.size(); i++) {
					Mob m = chunk.mobs.get(i);
					if (Chunk.SIZE - 1 - m.x == x && m.y == y)
						invoke(chunk, m, 0, 0);
				}
			}
		}
	}

	@Override
	public void invoke(Chunk chunk, Unit invoker, int dx, int dy) {
		if (respawning <= 0) {
			for (Event e : events)
				e.invoke(chunk, invoker, dx, dy);
			respawning = COOLDOWN;
		}
	}

	@Override
	public Event copy() {
		source.setAttribute("x", String.valueOf(x));
		source.setAttribute("y", String.valueOf(y));
		NodeList ev = source.getElementsByTagName("Event");
		for (int i = 0; i < ev.getLength(); i++)
			source.removeChild(ev.item(i));
		for (Event e : events)
			source.appendChild(e.copy().source);

		EventList a = new EventList(source);
		a.rotations = rotations;
		return a;
	}

	public void setRotations(int rotate) {
		this.rotations = rotate;
		for (Event e : events)
			e.rotations = rotate;
	}

	@Override
	public void rotateClockwise() {
		int by = Chunk.SIZE - 1 - x;
		int bx = y;
		y = by;
		x = bx;
		for (Event e : events)
			e.rotateClockwise();
		rotations++;
	}
}
