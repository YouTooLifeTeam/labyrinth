package com.youtoolife.labyrinth.chunk;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.GameObjects.Floor;
import com.youtoolife.labyrinth.GameObjects.GameObject;
import com.youtoolife.labyrinth.GameObjects.GameObject.BlockType;
import com.youtoolife.labyrinth.events.InvokeEvent;
import com.youtoolife.labyrinth.units.mob.Mob;

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

	public Vector<Mob> mobs;
	public Vector<InvokeEvent> events;

	public Chunk(Exits type, String name, GameObject[][] map,
			Vector<InvokeEvent> events) {
		this.name = name;
		this.type = type;
		this.map = map;
		this.events = events;
		mobs = new Vector<Mob>();
	}

	public void rotateClockwise(int times) {
		GameObject buf[][] = new GameObject[SIZE][SIZE];
		for (int time = 0; time < times; time++) {
			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
					buf[i][j] = map[SIZE - 1 - j][i];
			map = buf;
			buf = new GameObject[SIZE][SIZE];
			for (InvokeEvent e : events)
				e.rotate();
		}
		rotates += times;
	}

	public Chunk copy() {
		GameObject[][] buf = new GameObject[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				buf[i][j] = map[i][j].copy();
		Vector<InvokeEvent> bufev = new Vector<InvokeEvent>();
		for (InvokeEvent ev : events)
			bufev.add(ev.copy());
		Chunk chunk = new Chunk(this.type, this.name, buf, bufev);
		chunk.generateBlood();
		return chunk;
	}

	public void renderAll(SpriteBatch batch, float ChunkSubX, float ChunkSubY) {
		float XOffset = ChunkSubX * SIZE * 50 - 50 * SIZE / 2 + MainGame.w / 2;
		float YOffset = ChunkSubY * SIZE * 50 - 50 * SIZE / 2 + MainGame.h / 2;

		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (map[i][j].type != BlockType.Wall) {
					map[i][j].draw(batch, j * 50 + XOffset, (SIZE - 1 - i) * 50
							+ YOffset);
				}

		for (Mob m : mobs)
			m.draw(batch, ChunkSubX * 50 * SIZE, ChunkSubY * 50 * SIZE);
	}

	public void renderWalls(SpriteBatch batch, float ChunkSubX, float ChunkSubY) {
		float XOffset = ChunkSubX * SIZE * 50 - 50 * SIZE / 2 + MainGame.w / 2;
		float YOffset = ChunkSubY * SIZE * 50 - 50 * SIZE / 2 + MainGame.h / 2;

		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (map[i][j].type == BlockType.Wall)
					map[i][j].draw(batch, j * 50 + XOffset, (SIZE - 1 - i) * 50
							+ YOffset);
	}

	public void generateBlood() {
		for (int i = 2; i < SIZE - 1; i++)
			for (int j = 2; j < SIZE - 1; j++) {
				if (map[i][j].type == BlockType.Floor)
					if (MathUtils.random(1f) <= CHANCE)
						((Floor) map[i][j]).addRandomBlood();
					else if (MathUtils.random(1f) <= CHANCE)
						((Floor) map[i][j]).addRandomGap();
			}
	}

	public void exit() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				map[i][j].here = null;
		mobs = new Vector<Mob>();
	}

	public void update() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				map[i][j].update();

		for (InvokeEvent e : events)
			e.check(this);

		for (Mob m : mobs)
			m.update(this);
	}

	public void drawMobs(SpriteBatch batch, float ChunkSubX, float ChunkSubY) {
		for (Mob m : mobs)
			m.draw(batch, ChunkSubX * 50 * SIZE, ChunkSubY * 50 * SIZE);
	}

}
