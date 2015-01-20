package com.youtoolife.labyrinth.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteGUI extends Sprite {
	
	public String id = "";
	public String action = "";
	public int div = 0;
	public int container = 0;
	
	public SpriteGUI(Texture texture, float x, float y, float width, float height) {
		super(texture, 0, 0, texture.getWidth(), texture.getHeight());
		setX(x);
		setY(y);
		setSize(width, height);
	}
}
