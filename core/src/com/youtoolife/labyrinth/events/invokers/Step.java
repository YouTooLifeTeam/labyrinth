package com.youtoolife.labyrinth.events.invokers;

import org.w3c.dom.Element;

import com.badlogic.gdx.Gdx;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.events.ActionEvent;
import com.youtoolife.labyrinth.events.InvokeEvent;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.Player;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.units.mob.Mob;

public class Step extends InvokeEvent{

	int x, y;
	float COOLDOWN;
	float refresh_delay = 0;
	
	public Step(Element e) {
		super(e);
		x = Integer.valueOf(e.getAttribute("x"));
		y = Integer.valueOf(e.getAttribute("y"));
		COOLDOWN = Integer.valueOf(e.getAttribute("Cooldown"));
	}

	@Override
	public void check(Chunk chunk) {
		refresh_delay -= Gdx.graphics.getDeltaTime();
		if (rotates % 2 == 0) {
			Player p = GamePlayState.player1;
			if (p.x == x && (Chunk.SIZE - 1 - p.y) == y)
				invoke(chunk, p);

			p = GamePlayState.player2;
			if (p.x == x && (Chunk.SIZE - 1 - p.y) == y)
				invoke(chunk, p);

			for (int i = 0; i < chunk.mobs.size(); i++) {
				Mob m = chunk.mobs.get(i);
				if (m.x == x && (Chunk.SIZE - 1 - m.y) == y)
					invoke(chunk, m);
			}
		} else {
			Player p = GamePlayState.player1;
			if (Chunk.SIZE - 1 - p.x == x && p.y == y)
				invoke(chunk, p);
			p = GamePlayState.player2;
			if (Chunk.SIZE - 1 - p.x == x && p.y == y)
				invoke(chunk, p);

			for (int i = 0; i < chunk.mobs.size(); i++) {
				Mob m = chunk.mobs.get(i);
				if (Chunk.SIZE - 1 - m.x == x && m.y == y)
					invoke(chunk, m);
			}
		}
	}

	@Override
	public void invoke(Chunk chunk, Unit unit) {
		if(refresh_delay<=0){
			refresh_delay = COOLDOWN;
			for(ActionEvent e: events)
				e.invoke(chunk, unit);
		}
	}

	@Override
	public void rotate() {
		int by = Chunk.SIZE - 1 - x;
		int bx = y;
		y = by;
		x = bx;
		for(ActionEvent e: events)
			e.rotate();
		rotates++;
	}

	@Override
	public InvokeEvent copy() {
		Step step = new Step(this.base);
		for(int i = 0; i<rotates;i++)
			step.rotate();
		return step;
	}

}
