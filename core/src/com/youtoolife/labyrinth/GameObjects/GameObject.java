package com.youtoolife.labyrinth.GameObjects;

import java.util.Vector;

import org.w3c.dom.Element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.player.Player;
import com.youtoolife.labyrinth.utils.Assets;

public class GameObject {

	public Color color = Color.WHITE;

	public enum BlockType {
		Wall, Floor
	}

	public Texture texture;

	private Vector<Sprite> additions;

	public BlockType type;

	public GameObject(BlockType type, Texture texture) {
		this.type = type;
		this.texture = texture;
		additions = new Vector<Sprite>();
	}

	public void draw(SpriteBatch batch, float x, float y) {
		// batch.setColor(color);
		batch.draw(texture, x, y, 50, 50);
		for (Sprite t : additions) {
			t.setPosition(x - 50, y + 50);
			t.draw(batch);
		}
	}

	public void update() {
	}

	public GameObject copy() {
		return new GameObject(type, texture);
	}

	public static GameObject getObject(Element block) {
		BlockType type = BlockType.valueOf(block.getAttribute("type"));
		Texture texture = Assets.getTexture(block.getAttribute("img"));
		return new GameObject(type, texture);
	}

	public void stepOnit(Chunk chunk , Player player, int dx, int dy){
		
	}
	
	public void addRandomBlood() {

		int num = MathUtils.random(9) + 1;
		additions.add(new Sprite(Assets.getTexture("blood/b-"
				+ String.valueOf(num))));
		additions.get(additions.size() - 1).setRotation(
				MathUtils.random(360f));
		additions.get(additions.size() - 1).setColor(1, 1, 1, 0.6f);
	}

	public void addRandomGap() {

		int num = MathUtils.random(9) + 1;
		additions.add(new Sprite(Assets.getTexture("gaps/g-"
				+ String.valueOf(num))));
	}

}
