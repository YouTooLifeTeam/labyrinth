package com.youtoolife.labyrinth.gui;

public class DividerGUI {
	public float x = 0, y = 0, top = 0;
	
	public float size = 0;
	
	public DividerGUI() {
		
	}
	
	public void addContainer(ContainerGUI cont) {
		size+= cont.size;
		//y-=cont.y;
	}
	public void addSprite(SpriteGUI sprite) {
		size+= sprite.getHeight();
	}
}
