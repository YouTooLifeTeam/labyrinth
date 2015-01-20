package com.youtoolife.labyrinth.gui;

public class ContainerGUI {
	
	public float x = 0, y = 0, top = 0;
	public float size, sizeY;
	public String type = "";
	public float indent_top = 0, indent_left = 0;
	public float maxSize = 0;
	
	public ContainerGUI() {
		
	}
	
	public void addSprite(SpriteGUI sprite) {
		size+= type.equalsIgnoreCase("left")?sprite.getWidth():sprite.getHeight();
		if (sprite.getHeight()>maxSize) maxSize = sprite.getHeight();
		sizeY+=sprite.getHeight();
		//y-=sprite.getHeight();
	}
}
