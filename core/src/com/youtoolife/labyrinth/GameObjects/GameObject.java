package com.youtoolife.labyrinth.GameObjects;

import java.util.Vector;

import org.w3c.dom.Element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.units.Unit;
import com.youtoolife.labyrinth.units.objects.Box;
import com.youtoolife.labyrinth.utils.Assets;

public abstract class GameObject {

	public Unit here = null;
	private int objectId = 0;

	public enum BlockType {
		Wall, Floor, Door, Mine, Lamp
	}

	public Texture main_texture;
	Texture normal_map;
	
	Vector<Sprite> additions;

	public BlockType type;
	boolean isActive = true;
	
	public GameObject(BlockType type, Texture texture, int objectId) {
		
		this.objectId = objectId;
		this.type = type;
		this.main_texture = texture;
		additions = new Vector<Sprite>();
		normal_map = Assets.getTexture("normal_map/floor_map");
	}

	public void draw(SpriteBatch batch, float x, float y) {
		normal_map.bind(1);
		main_texture.bind(0);
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
		
		int id = block.hasAttribute("id")?Integer.parseInt(block.getAttribute("id")):0;
		
		if(type==BlockType.Door)
			return new Door(texture, id);
		if(type==BlockType.Floor)
			return new Floor(texture,id);
		if(type==BlockType.Wall)
			return new Wall(texture,id);
		if(type==BlockType.Lamp)
			return new Lamp(texture,id);
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

	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void addRandomGap() {

		int num = MathUtils.random(9) + 1;
		additions.add(new Sprite(Assets.getTexture("gaps/g-"
				+ String.valueOf(num))));
	}
	
	public void stepOnit(Chunk chunk , Unit player, int dx, int dy){
		if(here instanceof Box)
			((Box)here).step(chunk , player, dx, dy);
	}
	
	public int getId(){
		return objectId;
	}
	
}
