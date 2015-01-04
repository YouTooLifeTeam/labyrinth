package com.youtoolife.labyrinth.chunk;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.GameObjects.GameObject.BlockType;
import com.youtoolife.labyrinth.events.Event;

public class Chunk {

	public static final int SIZE = 12;

	private final float CHANCE = 0.05f;

	public enum Exits {
		SingleExit, DiNeighbour, DiOpposite, TriExit, QuadroExit, NoExit
	}

	public Exits type = null;
	String name;
	public GameObject[][] map = new GameObject[SIZE][SIZE];
	public int rotates = 0;

	Vector<Event> events;

	public Chunk(Exits type, String name, GameObject[][] map,
			Vector<Event> events) {
		this.name = name;
		this.type = type;
		this.map = map;
		this.events = events;
	}

	public void rotateClockwise(int times) {
		GameObject buf[][] = new GameObject[SIZE][SIZE];
		for (int time = 0; time < times; time++) {
			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
					buf[i][j] = map[SIZE - 1 - j][i];
			map = buf;
			buf = new GameObject[SIZE][SIZE];
		}
		rotates += times;
	}

	public Chunk copy() {
		GameObject[][] buf = new GameObject[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				buf[i][j] = map[i][j].copy();
		Vector<Event> bufev = new Vector<Event>();
		for (Event ev : events)
			bufev.add(ev.copy());
		Chunk chunk = new Chunk(this.type, this.name, buf, bufev);
		chunk.generateBlood();
		return chunk;
	}

	public void draw(SpriteBatch batch, float ChunkSubX, float ChunkSubY) {
		float XOffset = ChunkSubX * SIZE * 50 - 50 * SIZE / 2 + MainGame.w / 2;
		float YOffset = ChunkSubY * SIZE * 50 - 50 * SIZE / 2 + MainGame.h / 2;

		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (map[i][j].type != BlockType.Wall)
					map[i][j].draw(batch, j * 50 + XOffset, (SIZE - 1 - i) * 50
							+ YOffset);

	}

	public void generateBlood(){
		
		for(int i = 2; i < SIZE-1;i++)
			for(int j = 2; j < SIZE-1;j++){
				if(map[i][j].type==BlockType.Floor)
				if(MathUtils.random(1f)<=CHANCE)
					map[i][j].addRandomBlood();
				else
					if(MathUtils.random(1f)<=CHANCE)
						map[i][j].addRandomGap();
			}
		
	}

	public void renderShadow(SpriteBatch batch, float ChunkSubX, float ChunkSubY) {
		float XOffset = ChunkSubX * SIZE * 50 - 50 * SIZE / 2 + MainGame.w / 2;
		float YOffset = ChunkSubY * SIZE * 50 - 50 * SIZE / 2 + MainGame.h / 2;

		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (map[i][j].type == BlockType.Wall)
					map[i][j].draw(batch, j * 50 + XOffset, (SIZE - 1 - i) * 50
							+ YOffset);
	}

	public void update() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				map[i][j].update();
		
		for(Event e: events)
			e.check(this);
	}

}
