package com.youtoolife.labyrinth;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.GameObjects.GameObject;

public class Chunk {

	public static final int SIZE = 10;
	
	public enum Exits {
		SingleExit, DiNeighbour, DiOpposite, TriExit, QuadroExit, NoExit
	}

	public Exits type = null;
	String name;
	GameObject[][] map = new GameObject[SIZE][SIZE];

	public Chunk(Exits type, String name, GameObject[][] map) {
		this.name = name;
		this.type = type;
		this.map = map;
	}

	public void rotateClockwise(int times){
		GameObject buf[][] = new GameObject[SIZE][SIZE];
		for(int time = 0;time<times;time++){
			for(int i = 0; i<SIZE;i++)
				for(int j = 0; j<SIZE;j++)
					buf[i][j] = map[SIZE-1-j][i];
			map = buf;
			buf = new GameObject[SIZE][SIZE];
		}
	}
	
	public Chunk copy(){
		GameObject[][] buf = new GameObject[SIZE][SIZE];
		for(int i = 0; i<SIZE;i++)
			for(int j = 0; j<SIZE;j++)
				buf[i][j] = map[i][j].copy();
		return new Chunk(this.type,this.name,buf);
	}
	
	public void draw(SpriteBatch batch, float ChunkSubX, float ChunkSubY) {
		float XOffset = ChunkSubX*SIZE*50-50*SIZE/2;
		float YOffset = ChunkSubY*SIZE*50-50*SIZE/2;
		
		for(int i = 0; i<SIZE;i++)
			for(int j = 0; j<SIZE;j++)
				map[i][j].draw(batch, j*50+XOffset, (SIZE-1-i)*50+YOffset);
		
		}

	public void update() {

	}

}
