package com.youtoolife.labyrinth.renderer;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.youtoolife.labyrinth.units.Unit;

public class Light {

	public static final float DEFAULT_LIGHT_Z = 0.1f;
	public static final float LIGHT_INTENSITY = 1.5f;
	public Unit exclude;
	Vector3 position = new Vector3();
	Color color;
	int XTile = -1, YTile = -1;

	public Light(int XTile, int YTile, Color color) {
		this.XTile = XTile;
		this.YTile = YTile;
		this.color = color;
		position.z = DEFAULT_LIGHT_Z;
	}

	public Light(float x, float y, Color color) {
		position = new Vector3(x, y, DEFAULT_LIGHT_Z);
		this.color = color;
	}

	public void update(float xOffset, float yOffset) {
		if (XTile != -1) {
			position.x = XTile * 50 + xOffset;
			position.y = YTile * 50 + yOffset;
		}
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPosition(Vector3 pos) {
		this.position = pos.cpy();
		position.z = DEFAULT_LIGHT_Z;
	}

	public static Vector<Light> getLights(Element e) {
		Vector<Light> lights = new Vector<Light>();

		NodeList list = e.getElementsByTagName("Light");
		for (int i = 0; i < list.getLength(); i++) {
			Element light = (Element) list.item(i);
			lights.add(new Light(Integer.parseInt(light.getAttribute("x")),
					Integer.parseInt(light.getAttribute("y")), new Color(
							Float.parseFloat(light.getAttribute("r")), Float
									.parseFloat(light.getAttribute("g")), Float
									.parseFloat(light.getAttribute("b")), Float
									.parseFloat(light.getAttribute("a")))));
		}
		return lights;
	}

}