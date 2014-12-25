package com.youtoolife.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.utils.Assets;

public class GameObject {
	
	public enum BlockType{
		Wall,Floor
	}
	
	Texture texture;
	
	public BlockType type;
	
	public GameObject(BlockType type){
		this.type = type;
		if(type==BlockType.Floor)
			texture = Assets.getTexture("floor");
		else
			texture = Assets.getTexture("wall");
	}
	
	public void draw(SpriteBatch batch, float x, float y){
		batch.draw(texture, x, y, 50, 50);
	}
	
}
