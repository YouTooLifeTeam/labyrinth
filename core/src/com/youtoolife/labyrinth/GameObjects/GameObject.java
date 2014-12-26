package com.youtoolife.labyrinth.GameObjects;

import org.w3c.dom.Element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.utils.Assets;

public class GameObject {
	
	public Color color = Color.WHITE;
	
	public enum BlockType{
		Wall,Floor
	}
	
	public Texture texture;
	
	public BlockType type;
	
	public GameObject(BlockType type, Texture texture){
		this.type = type;
		this.texture = texture;
	}
	
	public void draw(SpriteBatch batch, float x, float y){
		batch.setColor(color);
		batch.draw(texture, x, y, 50, 50);
	}
	
	public void update(){
		
	}
	
	public GameObject copy(){
		return new GameObject(type, texture);
	}
	
	public static GameObject getObject(Element block){
		BlockType type = BlockType.valueOf(block.getAttribute("type"));
		Texture texture;
		if(type==BlockType.Floor)
			texture = Assets.getTexture("floor");
		else
			texture = Assets.getTexture("wall");
		return new GameObject(type, texture);
	}
	
}
