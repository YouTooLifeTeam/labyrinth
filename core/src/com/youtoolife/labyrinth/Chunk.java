package com.youtoolife.labyrinth;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chunk {

	public enum Exits {
		SingleExit, DiNeighbour, DiOpposite, TriExit, QuadroExit, NoExit
	}

	public Exits type = null;
	String name;
	GameObject[][] map = new GameObject[10][10];

	public Chunk(Exits type, String name, GameObject[][] map) {
		this.name = name;
		this.type = type;
		this.map = map;
	}

	public void rotateClockwise(int times){
		GameObject buf[][] = new GameObject[10][10];
		for(int time = 0;time<times;time++)
			for(int i = 0; i<10;i++)
				for(int j = 0; j<10;j++)
					buf[i][j] = map[9-j][i]; 
		map = buf;
	}
	
	public Chunk copy(){
		return new Chunk(this.type,this.name,this.map);
	}
	
	public void draw(SpriteBatch batch) {

	}

	public void update() {

	}

}
