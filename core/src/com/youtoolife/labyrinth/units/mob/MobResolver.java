package com.youtoolife.labyrinth.units.mob;

import com.youtoolife.labyrinth.controller.IIController;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.units.objects.Box;

public class MobResolver {

	public static Unit getMob(String mob, int ChunkX, int ChunkY, int x, int y){
		Unit buf = null;
		
		if(mob.equals("Sad"))
			buf = new SadMob(ChunkX, ChunkY, new IIController(),x,y);
		if(mob.equals("Spike"))
			buf = new SpikeMob(ChunkX, ChunkY, new IIController(),x,y);
		if(mob.equals("Box"))
			buf = new Box(ChunkX, ChunkY,x,y);
		
		return buf;
	}
	
}
