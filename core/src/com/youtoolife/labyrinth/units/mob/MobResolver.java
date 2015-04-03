package com.youtoolife.labyrinth.units.mob;

import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.controller.IIController;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.units.objects.Box;

public class MobResolver {

	public static Unit getMob(Chunk chunk, String mob, int x, int y){
		Unit buf = null;
		
		if(mob.equals("Sad"))
			buf = new SadMob(chunk, new IIController(),x,y);
		if(mob.equals("Spike"))
			buf = new SpikeMob(chunk, new IIController(),x,y);
		if(mob.equals("Box"))
			buf = new Box(chunk, x,y);
		
		return buf;
	}
	
}
