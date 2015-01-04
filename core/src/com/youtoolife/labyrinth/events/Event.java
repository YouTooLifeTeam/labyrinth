package com.youtoolife.labyrinth.events;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.player.Player;

public abstract class Event {

	public abstract void check(Chunk chunk);
	
	public abstract void invoke(Chunk chunk, Player invoker, int dx, int dy);
	
	public abstract Event copy();
}
