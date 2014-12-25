package com.youtoolife.labyrinth;

public class GameObject {
	
	public enum BlockType{
		Wall,Floor
	}
	
	public BlockType type;
	
	public GameObject(BlockType type){
		this.type = type;
	}
	
}
