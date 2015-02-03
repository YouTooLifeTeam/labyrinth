package com.youtoolife.labyrinth.gui;

public class ContainerGUI {
	
	public float x = 0, y = 0, top = 0;
	public float width = 0, height = 0;
	public String type = "";
	public float indent = 0;
	public float maxSize = 0;
	
	public ContainerGUI() {
		
	}
	
	public void addSprite(SpriteGUI sprite) {
		boolean firstW = (width == 0?true:false);
		width = (type.equalsIgnoreCase("left")?width+(!firstW?indent:0)+sprite.getWidth():
			sprite.getWidth()>width?sprite.getWidth():width);
		boolean firstH = (height == 0?true:false);
		height = (type.equalsIgnoreCase("top")?height+(!firstH?indent:0)+sprite.getHeight():
			sprite.getHeight()>height?sprite.getHeight():height);
	}
	public float getWidth() {
		return width;
	}
	public float getHeigth() {
		return height;
	}
}
