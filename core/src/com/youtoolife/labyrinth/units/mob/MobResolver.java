package com.youtoolife.labyrinth.units.mob;

import com.youtoolife.labyrinth.controller.IIController;

public class MobResolver {

	public static Mob getMob(String mob, int ChunkX, int ChunkY, int x, int y){
		Mob buf = null;
		
		if(mob.equals("Sad"))
			buf = new SadMob(ChunkX, ChunkY, new IIController(),x,y);
		if(mob.equals("Spike"))
			buf = new SpikeMob(ChunkX, ChunkY, new IIController(),x,y);
		
		return buf;
	}
	
}
