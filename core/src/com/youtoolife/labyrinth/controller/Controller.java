package com.youtoolife.labyrinth.controller;

import java.util.Vector;

public abstract class Controller {

	public enum Action{
		Left, Right, Up, Down, None
	}
	
	Vector<Action> queue = new Vector<Action>();
	
	public Action getAction(){
		if(queue.size()>0){
			Action buf = queue.get(0);
			queue.remove(0);
			return buf;
		}else
			return Action.None;
	}
	
	public abstract void update();
	
}
