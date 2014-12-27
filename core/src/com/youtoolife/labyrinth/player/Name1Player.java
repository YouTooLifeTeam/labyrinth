package com.youtoolife.labyrinth.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtoolife.labyrinth.controller.Controller;
import com.youtoolife.labyrinth.utils.AnimatedSprite;
import com.youtoolife.labyrinth.utils.Assets;

public class Name1Player extends Player {

	public Name1Player(int ChunkX, int ChunkY, Controller control) {
		super(ChunkX, ChunkY, control);
		sprite = new AnimatedSprite(0, 0, 50, 50, new Sprite(
				Assets.getTexture("player2")), 0);
		sprite.setPreferedDelta(0.15f);
		sprite.setAnimStart(0);
		sprite.setAnimStop(3);
	}

}
