package com.youtoolife.labyrinth.GameObjects;

import java.util.Vector;

import org.w3c.dom.Element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.utils.Assets;

public abstract class GameObject {

	public Unit here = null;
	private int objectId = 0;
	
	public enum BlockType {
		Wall, Floor, Door, Mine
	}

	public Texture main_texture;

	Vector<Sprite> additions;

	public BlockType type;

	public GameObject(BlockType type, Texture texture, int objectId) {
		this.objectId = objectId;
		this.type = type;
		this.main_texture = texture;
		additions = new Vector<Sprite>();
	}

	public void draw(SpriteBatch batch, float x, float y) {
		batch.draw(main_texture, x, y, 50, 50);
		for (Sprite t : additions) {
			t.setPosition(x - 50, y + 50);
			t.draw(batch);
		}
	}

	public abstract boolean canStep();
	
	public abstract void update();

	public abstract GameObject copy();

	public static GameObject getObject(Element block) {
		BlockType type = BlockType.valueOf(block.getAttribute("type"));
		Texture texture = Assets.getTexture(block.getAttribute("img"));
		if(type==BlockType.Door)
			return new Door(texture, Integer.parseInt(block.getAttribute("id")));
		if(type==BlockType.Floor)
			return new Floor(texture);
		if(type==BlockType.Wall)
			return new Wall(texture);
		return null;
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
	
	public abstract void stepOnit(Chunk chunk , Unit player, int dx, int dy);
	
	public int getId(){
		return objectId;
	}
	
}
