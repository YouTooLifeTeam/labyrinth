package com.youtoolife.labyrinth.controller;

import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Player;

public class IIController extends Controller {


	
	@Override
	public void update() {}

	public void update(Chunk chunk, Player player1, Player player2) {
		int a = MathUtils.random(3);
		Action e = Action.Up;
		if(a==0)
			e = Action.Down;
		if(a==1)
			e = Action.Left;
		if(a==2)
			e = Action.Right;
		this.queue.add(e);
	}

}
