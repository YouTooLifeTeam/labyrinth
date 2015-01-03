package com.youtoolife.labyrinth.events;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.player.Player;

public abstract class Event {

	public abstract void invoke(Chunk chunk, Player invoker);
	
	public abstract Event copy();
}
