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

	public void draw(SpriteBatch batch) {

	}

	public void update() {

	}

}
