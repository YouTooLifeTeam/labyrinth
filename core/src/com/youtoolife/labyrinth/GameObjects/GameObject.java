package com.youtoolife.labyrinth.GameObjects;

import java.util.Vector;

import org.w3c.dom.Element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.utils.Assets;

public class GameObject {
	
	public Color color = Color.WHITE;
	
	public enum BlockType{
		Wall,Floor
	}
	
	public Texture texture;
	
	private Vector<Texture> additions;
	
	public BlockType type;
	
	public GameObject(BlockType type, Texture texture){
		this.type = type;
		this.texture = texture;
		additions = new Vector<Texture>();
	}
	
	public void draw(SpriteBatch batch, float x, float y){
		//batch.setColor(color);
		batch.draw(texture, x, y, 50, 50);
		for(Texture t: additions)
			batch.draw(t, x, y, 50, 50);
	}
	
	public void update(){}
	
	public GameObject copy(){
		return new GameObject(type, texture);
	}
	
	public static GameObject getObject(Element block){
		BlockType type = BlockType.valueOf(block.getAttribute("type"));
		Texture texture = Assets.getTexture(block.getAttribute("img"));
		return new GameObject(type, texture);
	}
	
	public void addRandomBlood(){
		
		int num = MathUtils.random(9)+1;
		additions.add(Assets.getTexture("blood/b-"+String.valueOf(num)));
		
	}
	
}
