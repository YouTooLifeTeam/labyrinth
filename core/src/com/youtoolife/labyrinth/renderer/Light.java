package com.youtoolife.labyrinth.renderer;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.youtoolife.labyrinth.units.Unit;

public class Light {

	public static final float DEFAULT_LIGHT_Z = 0.1f;
	public static final float LIGHT_INTENSITY = 1.5f;
	public Unit exclude;
	Vector3 position;
	Color color;

	public Light(float x, float y, Color color) {
		position = new Vector3(x,y,DEFAULT_LIGHT_Z);
		this.color = color;
	}

	public void setColor(Color color){
		this.color = color;
	}
	
	public void setPosition(Vector3 pos){
		this.position = pos.cpy();
		position.z = DEFAULT_LIGHT_Z;
	}
	
}